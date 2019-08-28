package co.th.ford.tms.controller;






import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.th.ford.tms.model.Carrier;
import co.th.ford.tms.service.CarrierService;


@Controller
@RequestMapping("/")
public class CalendarController {

	
	
	
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CarrierService cservice;	
	
	@Autowired
	Environment environment;
	
	
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/calendar" }, method = RequestMethod.GET)
	public String calendar(HttpSession session,ModelMap model) {		 
		 if(!checkAuthorization(session))return "redirect:/login";
		 LocalDate initial = LocalDate.now();
		 LocalDate start = initial.withDayOfMonth(1);
		 LocalDate end = initial.dayOfMonth().withMaximumValue();
		 String startDate=start.toString("yyyy-MM-dd");
		 String endDate=end.toString("yyyy-MM-dd");
		 model.addAttribute("_date", LocalDate.parse(startDate, dtf).getDayOfMonth());
		 model.addAttribute("_month", LocalDate.parse(startDate, dtf).getMonthOfYear());
		 model.addAttribute("_year", LocalDate.parse(startDate, dtf).getYear());	 
		 //List<Carrier> c= cservice.findListCarriersByDate(startDate, endDate);
		 List<Carrier> c= cservice.findListCarriersByDate(getThaiDate(start),getThaiDate( end));
		 model.addAttribute("carriers", c);
		 
		  List<String> monthList = getMonthList();
		  List<Integer> yearList = getYearList();
		  model.addAttribute("monthLst", monthList);
		  model.addAttribute("yearLst", yearList);
		  String[] strStartDate = startDate.split("-");
		  model.addAttribute("chooseYear", strStartDate[0]);
		  Integer monthIndexStartDate = Integer.valueOf(strStartDate[1]) - 1;
		  model.addAttribute("chooseMonth", monthList.get(monthIndexStartDate));
		 		 
		return "calendar";
	}
	
	
	
	private List<String> getMonthList() {

		  List<String> monthList = new ArrayList<String>();
		  monthList.add("January");
		  monthList.add("February");
		  monthList.add("March");
		  monthList.add("April");
		  monthList.add("May");
		  monthList.add("June");
		  monthList.add("July");
		  monthList.add("August");
		  monthList.add("September");
		  monthList.add("October");
		  monthList.add("November");
		  monthList.add("December");
		  return monthList;

		 }
		 
		 private List<Integer> getYearList() {
		  Date getDate;
		  Calendar calendar = Calendar.getInstance();  
		     getDate = calendar.getTime(); 
		     DateFormat sdf_Year_Calendar = new SimpleDateFormat("yyyy");
		     Integer intCurrentYear= Integer.valueOf(sdf_Year_Calendar.format(getDate));
		     
		  List<Integer> yearList = new ArrayList<Integer>();
		  
		  int plus543=0;
		  int numYearsEng = 0;
		  if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;	
		  numYearsEng = intCurrentYear - plus543;
		  
		  yearList.add(numYearsEng);
		  
		  for (int i = 0; i < 4; i++) {
		   intCurrentYear = intCurrentYear - 1;
		   	   
		   int plusa543=0;
		   int numYearEng = 0;
		   if(environment.getRequiredProperty("local.datetime").equals("th"))plusa543=543;	
		   numYearEng = intCurrentYear - plusa543;
		   yearList.add(numYearEng);
		  }

		  
		  return yearList;

		 }
	
	
	@RequestMapping(value = {"/calendar/{loadDate}" }, method = RequestMethod.GET)
	public String calendar(HttpSession session,@PathVariable String loadDate,ModelMap model) {		 
		 if(!checkAuthorization(session))return "redirect:/login";
		 LocalDate initial = LocalDate.parse(loadDate);
		 LocalDate start = initial.withDayOfMonth(1);
		 LocalDate end = initial.dayOfMonth().withMaximumValue();
		 String startDate=start.toString("yyyy-MM-dd");
		 String endDate=end.toString("yyyy-MM-dd");
		 model.addAttribute("_date", LocalDate.parse(startDate, dtf).getDayOfMonth());
		 model.addAttribute("_month", LocalDate.parse(startDate, dtf).getMonthOfYear());
		 model.addAttribute("_year", LocalDate.parse(startDate, dtf).getYear());
		 //List<Carrier> c= cservice.findListCarriersByDate(startDate, endDate);
		 List<Carrier> c= cservice.findListCarriersByDate(getThaiDate(start),getThaiDate( end));
		 model.addAttribute("carriers", c);
		return "calendar";
	}
	
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@RequestMapping(value = { "/calendar/{startDate}/{endDate}" }, method = RequestMethod.GET)
	public String calendar(HttpSession session,@PathVariable String startDate,@PathVariable String endDate,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		model.addAttribute("_date", LocalDate.parse(startDate, dtf).getDayOfMonth());
		model.addAttribute("_month", LocalDate.parse(startDate, dtf).getMonthOfYear());
		model.addAttribute("_year", LocalDate.parse(startDate, dtf).getYear());
		//List<Carrier> c= cservice.findListCarriersByDate(startDate, endDate);
		List<Carrier> c= cservice.findListCarriersByDate(getThaiDate(LocalDate.parse(startDate, dtf)), getThaiDate(LocalDate.parse(endDate, dtf)));
		model.addAttribute("carriers", c);
		
		  List<String> monthList = getMonthList();
		  List<Integer> yearList = getYearList();
		  model.addAttribute("monthLst", monthList);
		  model.addAttribute("yearLst", yearList);
		  String[] strStartDate = startDate.split("-");
		  model.addAttribute("chooseYear", strStartDate[0]);
		  Integer monthIndexStartDate = Integer.valueOf(strStartDate[1]) - 1;
		  model.addAttribute("chooseMonth", monthList.get(monthIndexStartDate));
		
		return "calendar";
	}
	
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
	
	public String getThaiDate(LocalDate date) {
		int plus543=0;
		if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;
		return (date.getYear()+plus543)+"-"+date.getMonthOfYear()+"-"+date.getDayOfMonth();
	}
	
	
	
}
