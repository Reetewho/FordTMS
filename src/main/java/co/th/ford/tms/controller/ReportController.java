package co.th.ford.tms.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.th.ford.tms.model.JobWaypoint;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.Summary;
import co.th.ford.tms.model.Yard;
import co.th.ford.tms.service.CarrierService;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.LoadStopService;
import co.th.ford.tms.service.SummaryService;
import co.th.ford.tms.service.YardService;


@Controller
@RequestMapping("/")
public class ReportController {

	
	
	@Autowired
	CarrierService cservice;
	
	@Autowired
	SummaryService smervice;
	
	@Autowired
	YardService ydervice;
	
	@Autowired
	LoadService lservice;
	
	@Autowired
	LoadStopService lsservice;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	Environment environment;
	
//	private static final String CREATE_SHIPMENT_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/shipment/Create";	
//	private static final String LOGIN_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/user/login";
//    private static final String SUMMARY_LIST_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/shipment/summary/list";
    
    private static final String CREATE_SHIPMENT_NOSTRA_URL = "https://l2apitracking.nostralogistics.com/api/shipment/Create";	
	private static final String LOGIN_NOSTRA_URL = "https://l2apitracking.nostralogistics.com/api/user/login";
    private static final String SUMMARY_LIST_NOSTRA_URL = "https://l2apitracking.nostralogistics.com/api/shipment/summary/list";
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	
		
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/report" }, method = RequestMethod.GET)
	public String login(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    LocalDate yesterday = today.minusDays(1);
		//List<Employee> employees = service.findAllEmployees();
		//model.addAttribute("employees", employees);
	    List<Report1> report = cservice.findAll(getThaiDate(today),getThaiDate(yesterday));

        System.out.println("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
	    model.addAttribute("startDate",  yesterday);
		model.addAttribute("endDate", today);
		return "report1";
	}
	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/report" }, method = RequestMethod.POST)
	public String login(HttpSession session,@RequestParam String startDate,@RequestParam String endDate, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="report1";

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
					List<Report1> report = cservice.findAll(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					//List<Report1> report = cservice.findAll(startlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")));
					model.addAttribute("report", report);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.endDate", new String[]{endDate}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	/* ----------------------------------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = {"/AdminReport" }, method = RequestMethod.GET)
	public String logins(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    LocalDate yesterday = today.minusDays(1);
		//List<Employee> employees = service.findAllEmployees();
		//model.addAttribute("employees", employees);
	    List<Report1> report = cservice.findAll(getThaiDate(today),getThaiDate(yesterday));

        System.out.println("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
	    model.addAttribute("startDate",  yesterday);
		model.addAttribute("endDate", today);
		return "AdminReport";
	}
	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/AdminReport" }, method = RequestMethod.POST)
	public String logins(HttpSession session,@RequestParam String startDate,@RequestParam String endDate, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="AdminReport";

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
					List<Report1> report = cservice.findAll(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					//List<Report1> report = cservice.findAll(startlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")));
					model.addAttribute("report", report);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.endDate", new String[]{endDate}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	
	/* ---------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
/* ----------------------------------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = {"/Nostra" }, method = RequestMethod.GET)
	public String logins1(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		LocalDate today = LocalDate.now();	   
	    LocalDate yesterday = today.minusDays(1);
		//List<Employee> employees = service.findAllEmployees();
		//model.addAttribute("employees", employees);
	    List<Report1> report = cservice.findAllLoadId(getThaiDate(today),getThaiDate(yesterday));

        System.out.println("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
	    model.addAttribute("startDate",  yesterday);
		model.addAttribute("endDate", today);
		return "Nostra";
	}
	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/Nostra" }, method = RequestMethod.POST)
	public String logins1(HttpSession session,@RequestParam String startDate,@RequestParam String endDate,HttpServletRequest requestServer, ModelMap model){
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="Nostra";

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
					
					Map map = new HashMap<String, String>();
					map.put("username", "marcotechnology");
					map.put("password", "Z)2opyqj");
					
//					map.put("username", "marcotest");
//					map.put("password", "nostra1234");

					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<String> resp = restTemplate.postForEntity(LOGIN_NOSTRA_URL, map, String.class);
					HttpHeaders headers = resp.getHeaders();

					String set_cookie = headers.getFirst(headers.SET_COOKIE);
					
					List<String> headerValues = headers.get("Set-Cookie");
					
					 
					System.out.println("Response: " + resp.toString() + "\n");
					System.out.println("Set-Cookie: " + set_cookie + "\n");
					System.out.println("********* FINISH *******");
					
					System.out.println(resp.getStatusCode());
					System.out.println("Show body : " + resp.getBody());
					
					
					HttpSession sessionAPI = requestServer.getSession();
					sessionAPI.setAttribute("KeyCookie",  headerValues.get(1));	
					
					
					LocalDate endlocalDate = LocalDate.parse(endDate);						
					List<Report1> report = cservice.findAllLoadId(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					//List<Report1> report = cservice.findAll(startlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString( DateTimeFormat.forPattern("yyyy-MM-dd")));
					List<Yard> Yards = ydervice.findAllYard();

					model.addAttribute("Yards", Yards);
					model.addAttribute("report", report);
					System.out.println("----------> ! Test Yards  In Report  ! <----------" + report);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.endDate", new String[]{endDate}, Locale.getDefault()));
				}
			}
		}
			
		return nextPage;
	}
	
	@SuppressWarnings("unchecked")
	@ RequestMapping(value = {"/updateNostraAPI"}, method = RequestMethod.POST)
	public String updateDataPayment(HttpSession session,HttpServletRequest requestServer,String startDate,@RequestParam String endDate,
			@RequestParam("console-select-rows") String getSelectItemData,ModelMap model)throws InterruptedException, JsonProcessingException {
		if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="Nostra";
		
	    String json = getSelectItemData;	   
	    JSONObject obj = new JSONObject(json);
	    JSONArray arr = obj.getJSONArray("theGroupData");
        List<LoadStop> allListLoadStop = new ArrayList<LoadStop>();
    	
	    for (int i = 0; i< arr.length(); i++){
	    	if(arr.getJSONObject(i).getString("SystemID") != null ) {
	    		
	    		int intLoadID = 0;
				intLoadID = Integer.parseInt(arr.getJSONObject(i).getString("LoadID")); 
	    		
	       int intsystemid = Integer.parseInt(arr.getJSONObject(i).getString("SystemID"));  
	        LoadStop loadStop=lsservice.findLoadStopByID(intsystemid);
	        
	        Load loads=lservice.findLoadByID(intLoadID);
	        
	        int intsystemlaod = Integer.parseInt(arr.getJSONObject(i).getString("SystemLoadID")); 
			  System.out.println("----------> !Test! <----------" + intsystemlaod);		    

			  System.out.println("----------> ! Test testRestAPI-Create-Shipment ++++++++++++  ! <----------" + "Test");		    
				HttpSession sessionAPI = requestServer.getSession();		
				//Thread.sleep(5000);
				// create headers
		    	HttpHeaders headersSummary = new HttpHeaders();
		    	String strCookie="";
		    	strCookie = (String) sessionAPI.getAttribute("KeyCookie");
		    	
		    	// set `content-type` header
		    	headersSummary.setContentType(MediaType.APPLICATION_JSON);
		    	headersSummary.add("GISC-CompanyId", "170");
		    	//headersSummary.add("GISC-CompanyId", "40");
		    	headersSummary.add("Cookie", strCookie);

		    	String jobId = Integer.toString(loadStop.getSystemLoads());
		    	String TruckNumberId = loadStop.getTruckNumber();
		    	
		    	LocalDateTime localDateTime1 = loads.getLoadStartDateTime();
				String dateFormat1 = "yyyy/MM/dd HH:mm:ss";
				String date1 = localDateTime1.toString(dateFormat1);
				
				LocalDateTime localDateTime2 = loads.getLoadEndDateTime();
				String dateFormat2 = "yyyy/MM/dd HH:mm:ss";
				String date2 = localDateTime2.toString(dateFormat2);
				if(jobId != "0") {
		    	Map map = new HashMap<>();
		        
		        //Map map = new HashMap<String, String>();
				map.put("jobCode",jobId);
				map.put("jobName",jobId);
				map.put("waitingTime", "60");
				map.put("vehiclelicenseNo",TruckNumberId);
				map.put("planStartDateTime",date1);
				map.put("planEndDateTime",date2);
				map.put("startShipment", "D");
				map.put("closeShipment", "D");
				map.put("jobAutoFinish", "Manual");
				map.put("lateShipmentRole", "W");
				map.put("lastWaypointIsTerminal", "false");
				map.put("waypointFindBestSequent", "false");

				
				
				Load loadID1 =  lservice.findLoadByID(Integer.parseInt(arr.getJSONObject(i).getString("LoadID")));
				System.out.println("Test-Start-Load-ID "+loadID1);
       	
					loadID1.setDriverid(arr.getJSONObject(i).getString("Driverids"));
		        	lservice.updateLoad(loadID1);

				JobWaypoint jobWaypoint;
				List<JobWaypoint> lstJobWaypoint =  new ArrayList<>(); 
				
				List<LoadStop> loadStops = lsservice.findLoadStopByLoadID(intLoadID);
				
				
				for (LoadStop loadStop2 : loadStops) {
					
					String JobWaypointNames = loadStop2.getStopShippingLocationName();
					System.out.println("Test-Load-ID "+loadStop2);
					
					loadStop2.setSystemLoads(intsystemlaod);	
					loadStop2.setTruckNumber(arr.getJSONObject(i).getString("TruckNumber"));
					loadStop2.setWaybillNumber(arr.getJSONObject(i).getString("WaybillNumber"));
					//loadStop2.setLoadstopYardCode(arr.getJSONObject(i).getString("Yard"));
					lsservice.updateLoadStop(loadStop2);
															
					jobWaypoint = new JobWaypoint();
					jobWaypoint.setAssignOrder(loadStop2.getStopSequence());
					jobWaypoint.setJobWaypointCode(loadStop2.getStopShippingLocation());
					jobWaypoint.setJobWaypointName(JobWaypointNames);
					jobWaypoint.setDeliveryType("Both");
					jobWaypoint.setRadius(500);
					jobWaypoint.setShiptoLat(loadStop2.getLatitude());
					jobWaypoint.setShiptoLon(loadStop2.getLongitude());
					jobWaypoint.setWaybillNumber(loadStop2.getWaybillNumber());

					lstJobWaypoint.add(jobWaypoint);
										
				}
			
				map.put("jobWaypoint", lstJobWaypoint);
								
		    	System.out.println("Test-All-Pass"+map);		    	
		    			
		    	Gson gson = new Gson(); 
		    	String jsons = gson.toJson(map); 
		    			    
		    	System.out.println("Show-request : " + jsons);

		    	 RestTemplate respCreatetList = new RestTemplate();    	
			    	HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);   	
			    	System.out.println("Show request : " + request);
			    	ResponseEntity<String> entityPOST = respCreatetList.postForEntity(CREATE_SHIPMENT_NOSTRA_URL,request,String.class);
			    	System.out.println(entityPOST.getBody());

			model.addAttribute("Success", "Successfully" );
			
	    	}else {
			    model.addAttribute("Warning", "Unsuccessfully");

	    	}
			
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
					List<Report1> report = cservice.findAllLoadId(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					List<Yard> Yards = ydervice.findAllYard();					
					model.addAttribute("Yards", Yards);
					model.addAttribute("report", report);
					System.out.println("----------> ! Test Yards  In Report  ! <----------" + report);
				}catch(Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg",  messageSource.getMessage("InvalidFormatDate.report.endDate", new String[]{endDate}, Locale.getDefault()));
				}
			}
		}
	    	}
	    }	
		return nextPage;
	}
	
	
	@ RequestMapping(value = {"/Nostra-Detail/{loadID}/{systemLoadID}"}, method = RequestMethod.GET)
	public String NostraDetails(HttpSession session, HttpServletRequest requestServer,@PathVariable int loadID,@PathVariable int systemLoadID,ModelMap model) {
		//if(!checkAuthorization(session))return "redirect:/login";
		String nextPage="NostraDetail";
		
		List<Report1> ReportbySystemLoadId = cservice.findSystembyLoadId(loadID);
		
		for(Report1 loadStops : ReportbySystemLoadId) {
	    	System.out.println("---------------Test-Load-By-LoadStop------------------>"+loadStops+"<-------------------------------------------");
		}				
				
		
		
		HttpSession sessionAPI = requestServer.getSession();
				
    	HttpHeaders headersSummary = new HttpHeaders();
    	String strCookie="";
    	strCookie = (String) sessionAPI.getAttribute("KeyCookie");
    	
    	// set `content-type` header
    	headersSummary.setContentType(MediaType.APPLICATION_JSON);
    	headersSummary.add("GISC-CompanyId", "170");
    	//headersSummary.add("GISC-CompanyId", "40");
    	headersSummary.add("Cookie", strCookie);
    	
    	
    	Map map = new HashMap<>();
        
        //Map map = new HashMap<String, String>();
		map.put("shipmentCode",systemLoadID);

		Gson gson = new Gson(); 
    	String jsons = gson.toJson(map); 
    			    
    	System.out.println("Show-request : " + jsons);

    	 RestTemplate respCreatetList = new RestTemplate();    	
	    	HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);   	
	    	System.out.println("Show request : " + request);
	    	ResponseEntity<String> entityPOST = respCreatetList.postForEntity(SUMMARY_LIST_NOSTRA_URL,request,String.class);
	    	System.out.println(entityPOST.getBody());
	    	
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	
	    	JsonNode rootJson;
			try {
				
				rootJson = objectMapper.readTree(entityPOST.getBody());
//				System.out.println("Show body [timestamp] : " + rootJson.path("timestamp").path("actualStartDate").textValue());
//				
//				System.out.println("Show body [timestamp] : " + rootJson.path("timestamp").asText("actualStartDate"));
//				System.out.println("Show body [timestamp] : " + rootJson.path("timestamp").asText("actualEndDate"));
			
				//LocalDateTime StartdateTime = LocalDateTime.parse(rootJson.path("actualStartDate").asText());
				//LocalDateTime EnddateTime = LocalDateTime.parse(rootJson.path("actualEndDate").asText());
				
				//Summary FindSum = smervice.findSummaryLoadByID(loadID);
				//FindSum.setLoadIDSummary(rootJson.path("shipmentCode").asText());	
				//FindSum.setSummaryStatus(rootJson.path("shipmentStatusId").asText());
				//FindSum.setStopDate(StartdateTime);
				//FindSum.setStopDate(EnddateTime);
					
				//smervice.saveSummarylist(FindSum);
								
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	
	 	    
	    	

		model.addAttribute("lsLoadID",  ReportbySystemLoadId);		
		return nextPage;
	}
	
	/* ---------------------------------------------------------------------------------------------------------------------------------------------------*/
	
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
