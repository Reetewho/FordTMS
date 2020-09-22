package co.th.ford.tms.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import co.th.ford.tms.model.Employee;
import co.th.ford.tms.model.PaymentReport1;
import co.th.ford.tms.service.CarrierService;

@Controller
@RequestMapping("/")
public class TestRestAPIController {
	@Autowired
	CarrierService cservice;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	Environment environment;
	
	private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8081/mctapi/api/v1/employees";
	
	private static final String LOGIN_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/user/login";
    private static final String SUMMARY_LIST_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/shipment/summary/list";
	
	private static RestTemplate restTemplate = new RestTemplate();
	 
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/testRestAPI" }, method = RequestMethod.GET)
	public String paymentreport(HttpSession session,ModelMap model) {
		if(!checkAuthorization(session))return "redirect:/login";
		return "testRestAPI";
	}
	
	//public String  updateDataPaymentLogin(HttpSession session, HttpServletRequest request, @RequestParam("txtFindEmpNo") String getInfoFindEmpNo) {
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/testrestapi-login" }, method = RequestMethod.POST)
	public String testRestAPILogin(HttpSession session, HttpServletRequest requestServer) {
		//if(!checkAuthorization(session))return "redirect:/login";
		System.out.println("----------> ! Test REST-API ++++++++++++  ! <----------" + "Test");
		
	
		Map map = new HashMap<String, String>();
		map.put("username", "marcotest");
		map.put("password", "nostra1234");

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
		

		return "testRestAPI";
	}
	

	@RequestMapping(value = {"/testrestapi-summary-list" }, method = RequestMethod.POST)
	public String testRestAPISummaryList(HttpSession session, HttpServletRequest requestServer) throws InterruptedException {
		//if(!checkAuthorization(session))return "redirect:/login";
		System.out.println("----------> ! Test testRestAPI-SummaryList ++++++++++++  ! <----------" + "Test");
		
		
		HttpSession sessionAPI = requestServer.getSession();
		
		Thread.sleep(5000);
		// create headers
    	HttpHeaders headersSummary = new HttpHeaders();
    	String strCookie="";
    	strCookie = (String) sessionAPI.getAttribute("KeyCookie");
    	
    	// set `content-type` header
    	headersSummary.setContentType(MediaType.APPLICATION_JSON);
    	headersSummary.add("GISC-CompanyId", "40");
    	headersSummary.add("Cookie", strCookie);
    	
    
        String requestBody = "{\"shipmentFromDate\": \"2020-08-01T00:00:00.8403289+07:00\", \"shipmentToDate\":\"2020-09-03T10:23:39.8403289+07:00\"}"; 
    	
        // Data attached to the request.
        //HttpEntity<Map> requestBody = new HttpEntity<>(mapPost, headersSummary);
    	RestTemplate respSummartList = new RestTemplate();
    	
    	HttpEntity<String> request = new HttpEntity<String>(requestBody, headersSummary);
    	
    	System.out.println("Show request : " + request);
    	  ResponseEntity<String> entityPOST = respSummartList.postForEntity(SUMMARY_LIST_NOSTRA_URL,request,String.class);
    	  System.out.println(entityPOST.getBody());

		return "testRestAPI";
	}
		
	
	
	public boolean checkAuthorization(HttpSession session) {
		if(session.getAttribute("S_FordUser") == null){			
			return false;
		}else {
			return true;
		}
	}
}
