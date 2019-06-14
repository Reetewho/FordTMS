package co.th.ford.tms.controller;



import java.util.List;

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
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.SetStopETA;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.CarrierService;
import co.th.ford.tms.service.CityService;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.LoadStopService;
import co.th.ford.tms.service.SetStopETAService;
import co.th.ford.tms.webservice.FindEntities;
import co.th.ford.tms.webservice.ProcessLoadRetrieve;
import co.th.ford.tms.webservice.ProcessLoadStatusUpdate;
import co.th.ford.tms.webservice.ProcessSetStopETA;


@Controller
@RequestMapping("/")
public class CarrierController {

	
	
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
	public String loadListWithDate(HttpSession session,@PathVariable String date,ModelMap model) {
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
			carrier = cservice.findCarrierByDate(date);	
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
						if(loadStop.getArriveTime().equals("")) {
							load.setGatein("-");
							lservice.updateLoad(load);
						}else {
						String numSequence = String.valueOf(loadStop.getArriveTime());
						load.setGatein(numSequence);
						lservice.updateLoad(load);
						}
						
					}
					loadStop.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
					loadStop.setLastUpdateDate(LocalDateTime.now());
					lsservice.saveLoadStop(loadStop);
					
					if(roundLoadStop==numLoadStop) {						
						if(loadStop.getDepartureTime().equals("")) {
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
				load.setLastUpdateDate(LocalDateTime.now());
				lservice.updateLoad(load);
			}else {
				model.addAttribute("Error", "Load Retrieve  :"+carrier.getCarrierCode()+" , Load ID : "+load.getSystemLoadID()+" Error : "+load.getErrorMessage());	
				
			}
			
		}
		List<LoadStop> loadStops= lsservice.findLoadStopByLoadID(loadID);
		model.addAttribute("loadDate", date);
		model.addAttribute("load", load);
		model.addAttribute("loadStops", loadStops);
		return "loadStop-list";
	}
	
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
		if(!checkAuthorization(session))return "redirect:/login";
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());	
		load.setCarrier(carrier);		
		loadStop.setLoad(load);
		setStopETA.setLoadStop(loadStop);
		
	/*test*/	//ProcessSetStopETA pSetStopETA=new ProcessSetStopETA();
	/*test*/	//pSetStopETA.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), setStopETA);
		setStopETA.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
		setStopETA.setLastUpdateDate(LocalDateTime.now());
		
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
		model.addAttribute("loadDate", date);
		model.addAttribute("loadStop", loadStop);	
		model.addAttribute("setStopETA", setStopETA);
		return "set-stop-ETA";
	}
	
	
	@RequestMapping(value = { "/loadStatusUpdate/{date}/{systemLoadID}-{shippingLocation}-{loadStopID}" }, method = RequestMethod.GET)
	public String loadStatusUpdate(HttpSession session,@PathVariable String date,@PathVariable int loadStopID,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LoadStop loadStop=lsservice.findLoadStopByID(loadStopID);
		Load load =lservice.findLoadByID(loadStop.getLoadID());
		Carrier carrier = cservice.findCarrierByID(load.getCarrierID());	
		load.setCarrier(carrier);
		
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
		load.setCarrier(carrier);		
		loadStop.setLoad(load);
		
		loadStop.setTruckNumber(loadStop1.getTruckNumber());
		loadStop.setArriveTime(loadStop1.getArriveTime());
		loadStop.setDepartureTime(loadStop1.getDepartureTime());
		//----------------------------------------------------------
		loadStop.setShipingOrder(loadStop1.getShipingOrder());
		loadStop.setWaybillNumber(loadStop1.getWaybillNumber());
		loadStop.setManifest(loadStop1.getManifest());
		//----------------------------------------------------------
		loadStop.setLastUpdateUser(((User)session.getAttribute("S_FordUser")).getUsername());
		loadStop.setLastUpdateDate(LocalDateTime.now());
		//ปิดเพื่อไม่ให้อัพเดท
		/*test*/	//ProcessLoadStatusUpdate pLoadStatusUpdate=new ProcessLoadStatusUpdate();
		/*test*/	//pLoadStatusUpdate.submit(environment.getRequiredProperty("webservice.Authorization"), environment.getRequiredProperty("webservice.SOAPAction"), loadStop);
		
		//การปิดเพื่อนให้ ProcessLoadStatusUpdate ส่งไปไม่ได้เพื่อนเป็นการทดสอบระบบ
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
	}
	
	public String getThaiDate(LocalDate date) {
		int plus543=0;
		if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;
		return (date.getYear()+plus543)+"-"+date.getMonthOfYear()+"-"+date.getDayOfMonth();
	}
}
