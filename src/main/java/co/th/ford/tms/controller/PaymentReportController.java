package co.th.ford.tms.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.LoadListReport;
import co.th.ford.tms.model.PaymentReport1;
import co.th.ford.tms.model.SetStopETA;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.CarrierService;


@Controller
@RequestMapping("/")
public class PaymentReportController {

	@Autowired
	CarrierService cservice;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	Environment environment;
	
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/paymentreport" }, method = RequestMethod.GET)
	public String paymentreport(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    LocalDate yesterday = today.minusDays(1);
		//List<Employee> employees = service.findAllEmployees();
		//model.addAttribute("employees", employees);
	    List<PaymentReport1> subreport = cservice.findPaymentByCompleted(getThaiDate(yesterday),getThaiDate(yesterday));
	    model.addAttribute("startDate",  yesterday);
		model.addAttribute("endDate", today);
		return "paymentreport1";
	}
	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/paymentreport" }, method = RequestMethod.POST)
	public String paymentreport(HttpSession session,@RequestParam String startDate,@RequestParam String endDate, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="paymentreport1";
		model.addAttribute("startDate",  startDate);
		model.addAttribute("endDate", endDate);
		if(startDate.trim().equals("") ) {			
		    model.addAttribute("errorMsg",  messageSource.getMessage("NotEmpty.report.startDate", new String[]{startDate}, Locale.getDefault()));
		}else if(endDate.trim().equals("")) {			
		    model.addAttribute("errorMsg",  messageSource.getMessage("NotEmpty.report.endDate", new String[]{endDate}, Locale.getDefault()));
		}else {
			LocalDate startlocalDate =LocalDate.now();
			try {
				
				startlocalDate = LocalDate.parse(startDate);				
			}catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.startDate", new String[]{startDate}, Locale.getDefault()));
			}
			if(model.get("errorMsg") ==null) {
				try {
					
					LocalDate endlocalDate = LocalDate.parse(endDate);						
					List<PaymentReport1> paymentreport = cservice.findPaymentByCompleted(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					//List<Report1> report = cservice.findAll(startlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")));
					System.out.println(paymentreport);
					model.addAttribute("paymentreport", paymentreport);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.endDate", new String[]{endDate}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	
	/*
	 * Method : Search by Load ID.
	 */
	@RequestMapping(value = {"/searchby-loadid"}, method = RequestMethod.GET)
	public String searchbyloadid(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
//	    model.addAttribute("LoadID",  "test");
		return "search-by-loadid";
	}
	
	@RequestMapping(value = {"/searchby-loadid"}, method = RequestMethod.POST)
	public String searchbyloadid(HttpSession session,@RequestParam String loadid, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="search-by-loadid";
		model.addAttribute("loadid", loadid);
		if(loadid.trim().equals("") ) {			
		    model.addAttribute("errorMsg",  messageSource.getMessage("not.empty.by.systemload", new Object[]{""}, Locale.getDefault()));
		}else {
			if(model.get("errorMsg") ==null) {
				try {								        
					List<LoadListReport> loadlistreport = cservice.findbySystemsLoadID(loadid);
					System.out.println("----------> ! Start Data Output Item List ! <---------- ||" + loadlistreport); 
					model.addAttribute("dataloadlistreport", loadlistreport);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("fail.syntax.error.query.by.systemload", new Object[]{loadid}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	
	
	@RequestMapping(value = {"/searchby-systemloadid/{systemLoadID}"}, method = RequestMethod.GET)
	public String searchBySystemLoadId(HttpSession session,@PathVariable("systemLoadID") String systemLoadID, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="search-by-loadid";
		model.addAttribute("loadid", systemLoadID);
		if(systemLoadID.trim().equals("") ) {			
		    model.addAttribute("errorMsg",  messageSource.getMessage("not.empty.by.systemload", new Object[]{systemLoadID}, Locale.getDefault()));
		}else {
			if(model.get("errorMsg") ==null) {
				try {								        
					List<LoadListReport> loadlistreport = cservice.findbySystemsLoadID(systemLoadID);
					System.out.println("----------> ! Start Data Output Item List ! <---------- ||" + loadlistreport); 
					model.addAttribute("dataloadlistreport", loadlistreport);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("fail.syntax.error.query.by.systemload", new Object[]{systemLoadID}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}

	
	
	
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
