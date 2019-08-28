package co.th.ford.tms.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.Carrier;
import co.th.ford.tms.model.City;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.PermissionMenu;
import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.SetStopETA;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.CarrierService;
import co.th.ford.tms.service.CityService;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.LoadStopService;
import co.th.ford.tms.service.SetStopETAService;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.webservice.FindEntities;
import co.th.ford.tms.webservice.ProcessLoadRetrieve;
import co.th.ford.tms.webservice.ProcessLoadStatusUpdate;
import co.th.ford.tms.webservice.ProcessSetStopETA;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.DepartmentService;


@Controller
@RequestMapping("/")
public class CarrierController {
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	RolesService rolesService;

	@Autowired
	UserService uservice;
	
	@Autowired
	CarrierService cservice;
	
	@Autowired
	LoadService lservice;
	
	@Autowired
	LoadStopService lsservice;
	
	@Autowired
	SetStopETAService sseservice;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	@Autowired
	Environment environment;
	
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	/*
	 * This method will check that user have already login .
	 */
	public boolean checkAuthorization(HttpSession session) {
		if(session.getAttribute("S_FordUser") == null){			
			return false;
		}else {
			return true;
		}
	}
	
	
	@RequestMapping(value = { "/load-list/{date}" }, method = RequestMethod.GET)
	public String loadListWithDate(HttpSession session,@PathVariable String date, ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		//Carrier carrier = cservice.findCarrierByDate(date);	
		Carrier carrier = cservice.findCarrierByDate(getThaiDate(LocalDate.parse(date, DateTimeFormat.forPattern("yyyy-MM-dd"))));	
		if(carrier==null) {
			carrier = new Carrier();
			carrier.setStatus("N/A");			
			carrier.setCarrierCode("APTH");
			carrier.setLoadDate(LocalDateTime.parse(date+" 00:00:00", dtf));
			carrier.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
//			carrier.setStatusFlag(1);
			carrier.setLastUpdateDate(LocalDateTime.now());
			cservice.saveCarrier(carrier);
			carrier = cservice.findCarrierByDate(getThaiDate(LocalDate.parse(date, DateTimeFormat.forPattern("yyyy-MM-dd"))));
			//carrier = cservice.findCarrierByDate(date);	
		}			
		if(!carrier.getStatus().equalsIgnoreCase("true")) {			
			FindEntities fin=new FindEntities();
			List<Load> loadList=fin.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), carrier).getLoadList();
			if(carrier.getStatus().equalsIgnoreCase("true")) {				
				for(Load load : loadList){
					load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					load.setLastUpdateDate(LocalDateTime.now());
					lservice.saveLoad(load);
				
				}
				if(loadList.size() >0) {
					carrier.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					carrier.setLastUpdateDate(LocalDateTime.now());
					cservice.updateCarrier(carrier);
				}
			}else {
				model.addAttribute("Error", "Find Entities  :"+carrier.getCarrierCode()+" Error : "+carrier.getErrorMessage());					
			}
		}
		List<Load> loads= lservice.findLoadByCarrierID(carrier.getCarrierID());
		model.addAttribute("loadDate", carrier.getLoadDate().toString( DateTimeFormat.forPattern("yyyy-MM-dd") ));
		model.addAttribute("loads", loads);
		
		
		List<String> listTest = new ArrayList<String>();
		listTest.add("List A");
		model.addAttribute("loadListTest", listTest);
		
		
		if(session.getAttribute("deleteLoadIDSuccess") != null ) {
			model.addAttribute("setAlertDeleteLoadSuccess", "Successfully, delete Load ID : " + session.getAttribute("deleteLoadIDSuccess").toString());
			session.removeAttribute("deleteLoadIDSuccess");
		}
		
		return "load-list";
	}
	
	@RequestMapping(value = { "/loadStop-list/{date}/{systemLoadID}-{loadID}" }, method = RequestMethod.GET)
	public String loadStopList(HttpSession session,@PathVariable String date,@PathVariable int loadID,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		Load load =lservice.findLoadByID(loadID);
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());	
		load.setCarrier(carrier);		
		if(load.getStatus().equalsIgnoreCase("N/A")) {			
			ProcessLoadRetrieve loadRetrieve=new ProcessLoadRetrieve(); 
			List<LoadStop> loadStopList=loadRetrieve.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), load).getLoadStopList();
			if(load.getStatus().equalsIgnoreCase("true")) {
				int numLoadStop = loadStopList.size();
				int roundLoadStop = 1;
				for(LoadStop loadStop : loadStopList){
	
					if(loadStop.getStopSequence()==1) {	
						if(loadStop.getArriveTime()==null) {
							
							load.setGatein("-");
							lservice.updateLoad(load);
						}else {
							System.out.println(loadStop.getDepartureTime()+"Test value DepartureTime");
						String numSequence = String.valueOf(loadStop.getArriveTime());
						load.setGatein(numSequence);
						lservice.updateLoad(load);
						}
						
					}
					loadStop.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					loadStop.setLastUpdateDate(LocalDateTime.now());
					
					lsservice.saveLoadStop(loadStop);
					
					if(roundLoadStop==numLoadStop) {						
						if(loadStop.getDepartureTime()==null) {
							load.setGateout("-");
							lservice.updateLoad(load);
						}else {
						String numSequences = String.valueOf(loadStop.getDepartureTime());
						load.setGateout(numSequences);	
						lservice.updateLoad(load);
						}
					}
					roundLoadStop=roundLoadStop+1;
				}				
				load.setStatus("Load");								
				load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
//				load.setStatusFlag(2);
				load.setDateaccept(LocalDateTime.now());
				load.setLastUpdateDate(LocalDateTime.now());
				lservice.updateLoad(load);
			}else {
				model.addAttribute("Error", "Load Retrieve  :"+carrier.getCarrierCode()+" , Load ID : "+load.getSystemLoadID()+" Error : "+load.getErrorMessage());	
				model.addAttribute("setAlertMsgDeleteLoad","true");
				
			}
			
		}
		
		
		List<LoadStop> loadStops= lsservice.findLoadStopByLoadID(loadID);
		model.addAttribute("loadDate", date);
		model.addAttribute("load", load);
		model.addAttribute("loadStops", loadStops);
		return "loadStop-list";
	}
	
	
	/*
	 * Start modify script by : Bunyong 2019-08-23
	 */
	@RequestMapping(value = { "/loadStop-list/{date}/{systemLoadID}-{loadID}" }, method = RequestMethod.POST)
	public String processDeleteLoad(HttpSession session, ModelMap model,@PathVariable String date, 
			@PathVariable int systemLoadID, @PathVariable int loadID, 
			@RequestParam int hiddenDelloadID) {
		
		/*
		 * System.out.println("Process delete systemLoadID : " + hiddenDelloadID); //
		 * System.out.println("Process delete date : " + date); //
		 * System.out.println("Process delete loadID : " + loadID); //
		 * System.out.println("Process delete systemLoadID : " + systemLoadID);
		 */
		session.removeAttribute("deleteLoadIDSuccess");
		Load delLoadData =lservice.deleteLoadByLoadID(hiddenDelloadID);
		if(delLoadData == null) {	
			session.setAttribute("deleteLoadIDSuccess", hiddenDelloadID);
		}
		return "redirect:/load-list/" + date;
	}
	
	@RequestMapping(value = {"/manual-add-load" }, method = RequestMethod.GET)
	public String showManualAddLoad(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    model.addAttribute("loadDate",  today);
		return "manual-add-load";
	}
	
	@RequestMapping(value = {"/manual-add-load" }, method = RequestMethod.POST)
	public String saveManualAddLoad(HttpSession session, ModelMap model, 
									@RequestParam("loadID") String loadID, 
									@RequestParam("loadDate") String loadDate) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    model.addAttribute("loadDate",  today);
	    
	    
	    Carrier carrier = cservice.findCarrierByDate(getThaiDate(LocalDate.parse(loadDate, DateTimeFormat.forPattern("yyyy-MM-dd"))));	
	    
	    if(carrier == null) {
	    	model.addAttribute("Error", " Unsuccessfully, not found carrier. Please check date.");
	    	
	    }else {
	    	Load loadDataExist = lservice.findLoadByCarrierID_SystemLoadID(carrier.getCarrierID(), Integer.parseInt(loadID));
	        if(loadDataExist == null) {    
		    	Load loadData = new Load();
		    	loadData.setCarrierID(carrier.getCarrierID());
		    	loadData.setCarrier(carrier);
		    	loadData.setSystemLoadID(Integer.parseInt(loadID));
		    	loadData.setAlertTypeCode("LOAD_TENDER_NEW");
		    	loadData.setStatus("N/A");
		    	loadData.setLastUpdateDate(LocalDateTime.now());
		    	loadData.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());	
				lservice.saveLoad(loadData);
				
				model.addAttribute("Success", "Successfully, manual add LoadID : " + loadID + ".");
	        }else {
	            model.addAttribute("Error", "Unsuccessfully, Load ID : " + loadID + " is exist in LoadData.");
	        }
	        
	    }
	    
		return "manual-add-load";
	}
	
	/*.
	 * End modify script by : Bunyong 2019-08-23
	 */
	
	
	
	@RequestMapping(value = { "/setStopETA/{date}/{systemLoadID}-{shippingLocation}-{loadStopID}" }, method = RequestMethod.GET)
	public String setStopETA(HttpSession session,@PathVariable String date,@PathVariable int loadStopID,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());	
		load.setCarrier(carrier);		
		loadStop.setLoad(load);
		if(loadStop.getStatus().equalsIgnoreCase("update")) {			
			return "redirect:/loadStop-list/"+carrier.getCarrierCode()+"-"+load.getSystemLoadID()+"-"+loadStopID;
		}
		SetStopETA setStopETA= sseservice.findSetStopETAByLoadStopID(loadStopID);
		if(setStopETA==null) {
			setStopETA =new SetStopETA();
			setStopETA.setLoadStopID(loadStopID);
			setStopETA.setEstimatedDateTime(loadStop.getArriveTime());
			setStopETA.setMovementDateTime(loadStop.getArriveTime());
			setStopETA.setStatus("N/A");
		}
		model.addAttribute("loadDate", date);
		model.addAttribute("loadStop", loadStop);	
		model.addAttribute("setStopETA", setStopETA);
		
		List<City> ListCity = cityService.findAllCity();
		model.addAttribute("ListCitys", ListCity);
		
		
		
		return "set-stop-ETA";
	}
	
	@RequestMapping(value = { "/setStopETA/{date}/{systemLoadID}-{shippingLocation}-{loadStopID}" }, method = RequestMethod.POST)	
	public String processSetStopETA(HttpSession session,@PathVariable String date,@Valid SetStopETA setStopETA, @RequestParam String lstcity, BindingResult result,
			ModelMap model, @PathVariable int loadStopID) {
		
        System.out.println("Test Session role of user : " + setStopETA.getMovementDateTime()); 


		if(!checkAuthorization(session))return "redirect:/login";				
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());
		User sessionUser = (User)session.getAttribute("S_FordUser");
		
		if(sessionUser.getRole() == 2 || sessionUser.getRole() == 1 ) {
			
			//setStopETA.setMovementDateTime(loadStop.getArriveTime());
			load.setCarrier(carrier);		
			loadStop.setLoad(load);
			setStopETA.setStatusSetStop("Inactive");
			setStopETA.setLoadStop(loadStop);
			
	/*test*/	//ProcessSetStopETA pSetStopETA=new ProcessSetStopETA();
	/*test*/	//pSetStopETA.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), setStopETA);
			
				
				setStopETA.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
				setStopETA.setLastUpdateDate(LocalDateTime.now());
				setStopETA.setStatusSetStop("Inactive");
				setStopETA.setCity(lstcity);		
				
				//การปิดเพื่อนให้ ProcessSetStopETA ส่งไปไม่ได้เพื่อนเป็นการทดสอบระบบ
				setStopETA.setStatus("true");
				//
				
				if(setStopETA.getId()>0)sseservice.updateSetStopETA(setStopETA);
				else sseservice.saveSetStopETA(setStopETA);
				
				
				if(setStopETA.getStatus().equals("true")) {
					setStopETA.setErrorMessage("");
					loadStop.setStatus("setStop");
					lsservice.updateLoadStop(loadStop);
					model.addAttribute("Success", "Set Stop ETA of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+"  processed successfully");
					
				}else {
					model.addAttribute("Error", "Process Set Stop ETA of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+" Error :"+setStopETA.getErrorMessage());
					
				}
				
				User sessionUsera = (User)session.getAttribute("S_FordUser");

		        System.out.println("----------> ! Test Date Times session  ! <----------" + sessionUsera.getRole());

				
				List<City> ListCity = cityService.findAllCity();
				model.addAttribute("ListCitys", ListCity);
				model.addAttribute("loadDate", date);
				model.addAttribute("loadStop", loadStop);	
				model.addAttribute("setStopETA", setStopETA);
				return "set-stop-ETA";							
			
	}else {
		
        System.out.println("Test Session role 3 " + sessionUser ); 

	load.setCarrier(carrier);		
	loadStop.setLoad(load);
	setStopETA.setStatusSetStop("Inactive");
	setStopETA.setLoadStop(loadStop);
		
		setStopETA.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
		setStopETA.setLastUpdateDate(LocalDateTime.now());
		setStopETA.setStatusSetStop("Inactive");
		setStopETA.setCity(lstcity);		
		
		setStopETA.setStatus("true");
		
		
		 System.out.println("Test MovementDateTime : " + setStopETA.getMovementDateTime() ); 
		if(setStopETA.getId()>0)sseservice.updateSetStopETA(setStopETA);
		else sseservice.saveSetStopETA(setStopETA);
				
		if(setStopETA.getStatus().equals("true")) {
			setStopETA.setErrorMessage("");
			loadStop.setStatus("setStop");
			lsservice.updateLoadStop(loadStop);
			model.addAttribute("Success", "Set Stop ETA of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+"  processed successfully");
			
		}else {
			model.addAttribute("Error", "Process Set Stop ETA of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+" Error :"+setStopETA.getErrorMessage());
			
		}
		List<City> ListCity = cityService.findAllCity();
		model.addAttribute("ListCitys", ListCity);
		model.addAttribute("loadDate", date);
		model.addAttribute("loadStop", loadStop);	
		model.addAttribute("setStopETA", setStopETA);
		return "set-stop-ETA";							
	}
		
        
		
}
	
	
	@RequestMapping(value = { "/loadStatusUpdate/{date}/{systemLoadID}-{shippingLocation}-{loadStopID}" }, method = RequestMethod.GET)
	public String loadStatusUpdate(HttpSession session,@PathVariable String date,@PathVariable int loadStopID,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());	
		load.setCarrier(carrier);
		
		loadStop.setLoadstopremark("Active");
		lsservice.updateLoadStop(loadStop);
		model.addAttribute("loadDate", date);
		model.addAttribute("load", load);	
		model.addAttribute("loadStop", loadStop);
		return "load-status-update";
	}
	
	@RequestMapping(value = { "/loadStatusUpdate/{date}/{systemLoadID}-{shippingLocation}-{loadStopID}" }, method = RequestMethod.POST)	
	public String processLoadStatusUpdate(HttpSession session,@PathVariable String date,@Valid LoadStop loadStop1, BindingResult result,
			ModelMap model, @PathVariable int loadStopID) {
		if(!checkAuthorization(session))return "redirect:/login";
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);		
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());
		User sessionUser = (User)session.getAttribute("S_FordUser");
		
		if(sessionUser.getRole() == 2 || sessionUser.getRole() == 1 ) {
			
		load.setCarrier(carrier);		
		loadStop.setLoad(load);
		
		loadStop.setStatusLoad("Inactive");
		loadStop.setTruckNumber(loadStop1.getTruckNumber());
		loadStop.setArriveTime(loadStop1.getArriveTime());
		loadStop.setDepartureTime(loadStop1.getDepartureTime());
		//----------------------------------------------------------
		loadStop.setShipingOrder(loadStop1.getShipingOrder());
		loadStop.setWaybillNumber(loadStop1.getWaybillNumber());
		loadStop.setManifest(loadStop1.getManifest());
		loadStop.setLoadstopremark(loadStop1.getLoadstopremark());
		//----------------------------------------------------------
		loadStop.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
		loadStop.setLastUpdateDate(LocalDateTime.now());
		//ปิดเพื่อไม่ให้อัพเดท
		/*test*/	//ProcessLoadStatusUpdate pLoadStatusUpdate=new ProcessLoadStatusUpdate();
		/*test*/	//pLoadStatusUpdate.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), loadStop);
		
		//การปิดเพื่อนให้ ProcessLoadStatusUpdate ส่งไปไม่ได้เพื่อเป็นการทดสอบระบบ
		loadStop.setStatus("true");
				
		
		if(loadStop.getStatus().equals("true")) {
			loadStop.setErrorMessage("");
			loadStop.setStatus("update");
//			loadStop.setStatusFlag(3);
			lsservice.updateLoadStop(loadStop);			
			List<LoadStop> ls=lsservice.findNotCompletedStatusByLoadID(load.getLoadID());
			
			if(ls !=null && ls.size() ==0) {
				load.setStatus("Completed");
//				load.setStatusFlag(4);
				load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
				load.setLastUpdateDate(LocalDateTime.now());
				lservice.updateLoad(load);
			}else if(!load.getStatus().equalsIgnoreCase("In transit")) {
				load.setStatus("In transit");
//				load.setStatusFlag(3);
				load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
				load.setLastUpdateDate(LocalDateTime.now());
				lservice.updateLoad(load);
			}			
			model.addAttribute("Success", "Load Status Update of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+"  processed successfully");
			
		}else {
			model.addAttribute("Error", "Process Load Status Update of  Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+" Error :"+loadStop.getErrorMessage());
			
		}
			model.addAttribute("loadDate", date);
			model.addAttribute("load", load);	
			model.addAttribute("loadStop", loadStop);
			return "load-status-update";
			
			
			
			
		}else {
			
			load.setCarrier(carrier);		
			loadStop.setLoad(load);
			
			loadStop.setStatusLoad("Inactive");
			loadStop.setTruckNumber(loadStop1.getTruckNumber());
			loadStop.setArriveTime(loadStop1.getArriveTime());
			loadStop.setDepartureTime(loadStop1.getDepartureTime());
			//----------------------------------------------------------
			loadStop.setShipingOrder(loadStop1.getShipingOrder());
			loadStop.setWaybillNumber(loadStop1.getWaybillNumber());
			loadStop.setManifest(loadStop1.getManifest());
			loadStop.setLoadstopremark(loadStop1.getLoadstopremark());

			//----------------------------------------------------------
			loadStop.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
			loadStop.setLastUpdateDate(LocalDateTime.now());
			
			//การปิดเพื่อนให้ ProcessLoadStatusUpdate ส่งไปไม่ได้เพื่อเป็นการทดสอบระบบ
			loadStop.setStatus("true");
					
			
			if(loadStop.getStatus().equals("true")) {
				loadStop.setErrorMessage("");
				loadStop.setStatus("update");
//				loadStop.setStatusFlag(3);
				lsservice.updateLoadStop(loadStop);			
				List<LoadStop> ls=lsservice.findNotCompletedStatusByLoadID(load.getLoadID());
				
				if(ls !=null && ls.size() ==0) {
					load.setStatus("Completed");
//					load.setStatusFlag(4);
					load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					load.setLastUpdateDate(LocalDateTime.now());
					lservice.updateLoad(load);
				}else if(!load.getStatus().equalsIgnoreCase("In transit")) {
					load.setStatus("In transit");
//					load.setStatusFlag(3);
					load.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					load.setLastUpdateDate(LocalDateTime.now());
					lservice.updateLoad(load);
				}			
				model.addAttribute("Success", "Load Status Update of Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+"  processed successfully");
				
			}else {
				model.addAttribute("Error", "Process Load Status Update of  Load ID : "+load.getSystemLoadID()+" ,  Shipping Location :"+loadStop.getStopShippingLocation()+" Error :"+loadStop.getErrorMessage());
				
			}
				model.addAttribute("loadDate", date);
				model.addAttribute("load", load);	
				model.addAttribute("loadStop", loadStop);
				return "load-status-update";
		}
	}
	
	public String getThaiDate(LocalDate date) {
		int plus543=0;
		if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;
		return (date.getYear()+plus543)+"-"+date.getMonthOfYear()+"-"+date.getDayOfMonth();
	}
	
/*---------------------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = {"/drivers"}, method = RequestMethod.POST) 
    public String showResultSelectMultipleCheck(@RequestParam("console-select-rows") String getSelectItemData,@RequestParam("loaddates") String loaddates,HttpSession session,
    		ModelMap model, HttpServletRequest request) {
		if (!checkAuthorization(session))return "redirect:/login";

		
		System.out.println("---------> Start Request[POST] <--------- " +
				"get Result SelectMultipleCheck : " + getSelectItemData +
				"---------> End Request[POST] <---------");
			
		
		String[] arrOfSelectItem = getSelectItemData.split(",");
		int nItem = 1;
        for (String arrItem : arrOfSelectItem) {
        	
            System.out.println("Result " + nItem + " | Item : " + arrItem); 
            nItem++;
            
            
        }
       
        session.removeAttribute("sessionSelectItem");
        session.setAttribute("sessionSelectItem", arrOfSelectItem);
        
        
        String[] getSessionSelectItem = (String[])session.getAttribute("sessionSelectItem");
        System.out.println("----------> ! Start Loop Session Select Item ! <----------"); 
        int nSessionItem = 1;
        for (String arrSessionItem : getSessionSelectItem) {
         
            System.out.println("Result " + nSessionItem + " | Item : " + arrSessionItem); 
            nSessionItem++;
            
        }			 
    		
        model.addAttribute("loaddates", loaddates);
        
        List<User> dataRole = uservice.findByRole(Integer.valueOf("3"));  
      	model.addAttribute("users", dataRole);
        	
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<Department> ListDepartment = departmentService.findAllDepartment();
		model.addAttribute("ListDepartments", ListDepartment);
	
	   return "drivers";
	}
	
	
	@RequestMapping(value = { "/show-driver-detail/{showusername}/{loaddates}" }, method = RequestMethod.GET)
	public String showResultSelectMultipleCheck(HttpSession session,  ModelMap model,@PathVariable String showusername,@PathVariable String loaddates) {	 					
		if (!checkAuthorization(session))return "redirect:/login";
		
		List<Load> user_ld = lservice.findLoadByusername(showusername);
		
		
		String[] getSessionSelectItem = (String[])session.getAttribute("sessionSelectItem");
        System.out.println("----------> ! Start Loop Session Select Item ! <----------"); 
        int nSessionItem = 1;
        List<Load> allListLoad = new ArrayList<Load>();
        for (String arrSessionItem : getSessionSelectItem) {
         
            System.out.println("Result " + nSessionItem + " | Item : " + arrSessionItem); 
            nSessionItem++;
            Load loadDetail = lservice.findLoadByID(Integer.valueOf(arrSessionItem));
            allListLoad.add(loadDetail);
         
            
        }	
      	
        int totalallListLoads = allListLoad.size();         	       		
        System.out.println("Result totalallListLoads : " + totalallListLoads + " | Item totalallListLoads : " + totalallListLoads); 
        for(Load user_lds : user_ld){
        	
        	
        	
        if(totalallListLoads >= 2 || user_lds.getDateassign()==(LocalDateTime.now())); {
			model.addAttribute("Error", " You Assign Maximum In To Day  :"+totalallListLoads+" Warning Date : "+user_lds.getDateassign());					


        	}
        }
		
		    model.addAttribute("totalallListLoads", totalallListLoads);
		               
        	model.addAttribute("loaddates", loaddates);	
        
        	User user_r = uservice.findUserByusername(showusername);
	      	model.addAttribute("user_r", user_r);
	      	         	      	
	      	model.addAttribute("allListLoads", allListLoad);
	      		        	
			List<Roles> ListRoles = rolesService.findAllRoles();
			model.addAttribute("ListRolest", ListRoles);

			List<Department> ListDepartment = departmentService.findAllDepartment();
			model.addAttribute("ListDepartments", ListDepartment);
	
		return "show-driver-detail";
	}
	
	@RequestMapping(value = { "/driverconf/{showusernames}/{loaddates}" }, method = RequestMethod.GET)
	public String showResultSelectMultipleChecks(HttpSession session,  ModelMap model,@PathVariable String showusernames,@PathVariable String loaddates) {	 					
		if (!checkAuthorization(session))return "redirect:/login";
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");	
		model.addAttribute("loaddates", loaddates);	
        LocalDateTime formatDateTime = LocalDateTime.parse(loaddates, dtf);
        LocalDateTime datesession = LocalDateTime.now();
        
		String[] getSessionSelectItem = (String[])session.getAttribute("sessionSelectItem");
        System.out.println("----------> ! Start Loop Session Select Item ! <----------"); 
        int nSessionItem = 1;
        List<Load> allListLoad = new ArrayList<Load>();
        for (String arrSessionItem : getSessionSelectItem) {
         
            System.out.println("Result " + nSessionItem + " | Item : " + arrSessionItem); 
            nSessionItem++;
            Load loadDetail = lservice.findLoadByID(Integer.valueOf(arrSessionItem));
            allListLoad.add(loadDetail);
                                        
            
            loadDetail.setDriverid(showusernames);
            
            loadDetail.setDateassign(datesession);
            
            loadDetail.setAssign(formatDateTime);                    
            
            lservice.updateLoad(loadDetail);
            
            System.out.println("Result " + datesession + " | Item : " + loadDetail); 
            
            model.addAttribute("Success", "Assign To Driver Success : " + showusernames + ".");
            
        }	             
        model.addAttribute("loaddates", loaddates);	
        
		User user_r = uservice.findUserByusername(showusernames);
      	model.addAttribute("user_r", user_r);
      	         	      	
      	model.addAttribute("allListLoads", allListLoad);
      		        	
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<Department> ListDepartment = departmentService.findAllDepartment();
		model.addAttribute("ListDepartments", ListDepartment);        		
		
        session.removeAttribute("sessionSelectItem");
		
        return "show-detail-submit";
		}
	
	
	@RequestMapping(value = { "/load-list-drivers/{username}"}, method = RequestMethod.GET)
	public String showResultDrivers(HttpSession session,  ModelMap model,@PathVariable String username) {	 					
		if (!checkAuthorization(session))return "redirect:/login";
		

		List<Load> Loadlistd = lservice.findLoadByusername(username);
		model.addAttribute("Loadlistd", Loadlistd);
	
		return "load-list-drivers";
	}
	
}
