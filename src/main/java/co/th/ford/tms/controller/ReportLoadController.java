package co.th.ford.tms.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.ReportSystemLoadData;
import co.th.ford.tms.model.SetStopETA;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.LoadStopService;
import co.th.ford.tms.service.SetStopETAService;

@Controller
@RequestMapping("/")
public class ReportLoadController {
	
	private static final Logger log = Logger.getLogger(ReportLoadController.class);
	
	@Autowired
	LoadService lservice;
	
	@Autowired
	LoadStopService lsservice;
	
	@Autowired
	SetStopETAService setStopETAService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	Environment environment;
	
	/*
	 * Method : Check duplicate systemload by systemloadID.
	 */
	@RequestMapping(value = { "/check-dup-systemload" }, method = RequestMethod.GET)
	public String gotoSearchDuplicateBySystemLoadID(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		return "check-dup-by-loadid";
	}
	
	@RequestMapping(value = { "/check-dup-systemload" }, method = RequestMethod.POST)
	public String searchDuplicateBySystemLoadID(HttpSession session,@RequestParam("systemLoadID") String systemLoadID, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		
		log.info("POST Request : earchDuplicateBySystemLoadID");
		
		String nextPage="check-dup-by-loadid";
		model.addAttribute("systemLoadID", systemLoadID);
		if(systemLoadID.trim().equals("") ) {			
		    model.addAttribute("errorMsg",  messageSource.getMessage("not.empty.by.systemload", new Object[]{"Test"}, Locale.getDefault()));
		}else {
			if(model.get("errorMsg") ==null) {
				try {								        
					List<ReportSystemLoadData> reportLoadDatas = lservice.findLoadGroupBySystemLoad(Integer.valueOf(systemLoadID));
					model.addAttribute("loadDatas", reportLoadDatas);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("fail.syntax.error.query.by.systemload", new Object[]{systemLoadID}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	
	
	@RequestMapping(value = { "/stop-load-data/{systemLoadID}-{loadID}" }, method = RequestMethod.GET)
	public String loadStopList(HttpSession session, @PathVariable("systemLoadID") String systemLoadID, @PathVariable("loadID") int loadID
							  , ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		
		log.info("Get request delete SystemLoadID : " + systemLoadID + " | LoadID : " + loadID);
		
		
		Load load = lservice.findLoadByID(loadID);
		
		log.info("Find Load data for delete => LoadID : " + load.getLoadID() + " | SystemLoadID : " + load.getSystemLoadID() + " | CarrierID : " + load.getCarrierID());
		
		
		List<LoadStop> loadStopDatas =  lsservice.findLoadStopByLoadID(load.getLoadID());
		
		if(loadStopDatas.size() > 0) {
			for (LoadStop loadStop : loadStopDatas) {
				log.info("Find LoadStop data for delete => ID : " + loadStop.getId() + " | SystemLoads : " + loadStop.getSystemLoads());
				SetStopETA setStopETA_Data = setStopETAService.findSetStopETAByLoadStopID(loadStop.getId());
				if(setStopETA_Data != null) {
					log.info("Find SetStopETA data for delete => ID : " + setStopETA_Data.getId() + " | LoadStopID : " + setStopETA_Data.getLoadStopID());
					setStopETAService.deleteSetStopETAByID(setStopETA_Data.getId());
				}	
			}	
			lsservice.deleteLoadStopByLoadID(load.getLoadID());
		}
		
		lservice.deleteLoadByLoadID(load.getLoadID());
		
		List<ReportSystemLoadData> reportLoadDatas = lservice.findLoadGroupBySystemLoad(Integer.valueOf(systemLoadID));
		model.addAttribute("loadDatas", reportLoadDatas);
		
		model.addAttribute("Success", "Successfully ,delete SystemLoadID : " + systemLoadID + " | LoadID : " + loadID);
		
	
		return "check-dup-by-loadid";
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
