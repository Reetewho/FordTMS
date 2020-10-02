package co.th.ford.tms.controller;

//import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckType;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.GpsType;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.TruckService;
import co.th.ford.tms.service.TruckTypeService;
import co.th.ford.tms.service.DepartmentService;
import co.th.ford.tms.service.GpsTypeService;
//import co.th.ford.tms.aesencrypt.AESCrypt;
/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Controller
@RequestMapping("/")
public class TruckController {
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	UserService uservice;
	
	@Autowired
	GpsTypeService tgservice;
	
	@Autowired
	TruckService tkervice;

	@Autowired
	MessageSource messageSource;

	@Autowired
	TruckTypeService ttervice;
		
	@Autowired
	RolesService rolesService;

	@Autowired
	DepartmentService departmentService;

	public boolean checkAuthorization(HttpSession session) {
		if (session.getAttribute("S_FordUser") == null) {
			return false;
		} else {
			return true;
		}
	}


	@RequestMapping(value = { "/trucklist" }, method = RequestMethod.GET)
	public String TruckList(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		List<Truck> trucks = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", trucks);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);

		System.out.println("Test Tuck Type"+TruckGps);

		return "truck-list";
	}
	
	
	@RequestMapping(value = { "/addtruck" }, method = RequestMethod.GET)
	public String adduser(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		List<Truck> trucks = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", trucks);
		
		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		return "addtruck";
	}

	@RequestMapping(value = { "/addtruck" }, method = RequestMethod.POST)
	public String addtruck(HttpSession session, @RequestParam String TruckNumber,@RequestParam int TruckType,@RequestParam int GPSTRUCK,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		List<Truck> trucks = tkervice.findAllTruckNumber();
		for(Truck findtrucks : trucks){
			
			if(findtrucks.getTRUCK_NUMBER().equalsIgnoreCase(TruckNumber)) {
				model.addAttribute("Error", "Duplicate name : " + TruckNumber + " Please Try Again.");
				
				List<Truck> listtrucks = tkervice.findAllTruckNumber();
				model.addAttribute("trucks", listtrucks);
				
				List<TruckType> ListTypes = ttervice.findAllTruckType();
				model.addAttribute("ListType", ListTypes);
				
				List<GpsType> TruckGps = tgservice.findAllGps();
				model.addAttribute("TruckGps", TruckGps);

				
				return "addtruck";
		
			}
			
		}			
		  Truck  Newuser= new Truck();
		  
		  DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String strDateNow = DateTime.now().toString(dtf);
			
		  Newuser.setTRUCK_NUMBER(TruckNumber); 
		  Newuser.setTRUCK_TYPE(TruckType);
		  Newuser.setSTATUS("1");		 
		  Newuser.setGPS_TRUCK(GPSTRUCK);
		  Newuser.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
		  Newuser.setUPDATE_DATE(LocalDateTime.now());		  
		  if(Newuser.getCREATE_BY() == null || Newuser.getCREATE_DATE() == null) {
			  Newuser.setCREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
			  Newuser.setCREATE_DATE(LocalDateTime.now());  
	        }

		  tkervice.saveTrucknumber(Newuser);
		  
		  List<Truck> truck = tkervice.findAllTruckNumber();
			model.addAttribute("trucks", truck);

			List<TruckType> ListTypes = ttervice.findAllTruckType();
			model.addAttribute("ListType", ListTypes);
			
			List<GpsType> TruckGps = tgservice.findAllGps();
			model.addAttribute("TruckGps", TruckGps);

		  return "truck-list";
	}
	
	@RequestMapping(value = { "/TruckName/{TRUCK_NUMBER}"}, method = RequestMethod.GET)
	public String TruckStatus(@PathVariable("TRUCK_NUMBER") String TruckName,ModelMap model) {
		
		Truck Trucktatus = tkervice.findByTrucknumber(TruckName);

		if (Trucktatus.getSTATUS().equalsIgnoreCase("1")) {
			Trucktatus.setSTATUS("0");
			tkervice.updateTrucknumber(Trucktatus);
		} else {
			Trucktatus.setSTATUS("1");
			tkervice.updateTrucknumber(Trucktatus);
		}

		model.addAttribute("trucks", Trucktatus);
		model.addAttribute("edit", true);
		
		List<Truck> truck = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", truck);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		return "redirect:/trucklist";
	}
	
	
	
	@RequestMapping(value = { "/edit-truck/{TRUCK_NUMBER}" }, method = RequestMethod.GET)
	public String edittruckEmployee(HttpSession session,@PathVariable("TRUCK_NUMBER") String TruckName, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		Truck Trucktatus = tkervice.findByTrucknumber(TruckName);
		model.addAttribute("trucks", Trucktatus);
		model.addAttribute("edit", true);

	
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		return "edit-truck";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-truck/{TRUCK_NUMBER}" }, method = RequestMethod.POST)
	public String edittruck(HttpSession session, @Valid Truck truck, BindingResult result,@RequestParam int GPSTRUCK,
			@RequestParam int TruckType, ModelMap model) {
		if (!checkAuthorization(session))return "redirect:/login";
					
		truck.setTRUCK_TYPE(TruckType);
		truck.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
		truck.setUPDATE_DATE(LocalDateTime.now());
		truck.setGPS_TRUCK(GPSTRUCK);
		
		tkervice.updateTrucknumber(truck);
	
		return "redirect:/trucklist";
	}
	
	
	
	
	
	
	

}
