package co.th.ford.tms.testapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class testAPI_Nostra_Prod {

	private static Logger log = Logger.getLogger(testAPI_Nostra_Prod.class);

	private static String loginNostraUrl = "https://l2apitracking.nostralogistics.com/api/user/login";
	private static String summaryListNostraUrl = "https://l2apitracking.nostralogistics.com/api/shipment/summary/list";
	private static String wayPointListNostraUrl = "https://l2apitracking.nostralogistics.com/api/shipment/summary/waypointList";

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		System.out.println("show loginNostraUrl : " + loginNostraUrl);



		Map map = new HashMap<String, String>();

		map.put("username", "marcotechnology");
		map.put("password", "Z)2opyqj");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
		HttpHeaders headers = resp.getHeaders();

		String set_cookie = headers.getFirst(headers.SET_COOKIE);

		List<String> headerValues = headers.get("Set-Cookie");

		String token = headerValues.get(1);
		System.out.println(" New Token " + token);
		
		JsonNode root;
		try {
			root = objectMapper.readTree(resp.getBody());
			System.out.println("Show body [token] : " + root.path("token").asText());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// Request Headers
		HttpHeaders headersSummary = new HttpHeaders();

		// set `content-type` header
		headersSummary.setContentType(MediaType.APPLICATION_JSON);
		headersSummary.add("GISC-CompanyId", "170");
		headersSummary.add("Cookie", token);

		Map<String, String> mapSummary = new HashMap<String, String>();

		// Map map = new HashMap<String, String>();
		mapSummary.put("shipmentCode", "1624200");

		// Transform to JSON
		Gson gson = new Gson();
		String jsons = gson.toJson(mapSummary);

		// WebService Request
		RestTemplate respSummarytList = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);
		log.debug("Show request : " + request);
		ResponseEntity<String> entityPOST = respSummarytList.postForEntity(summaryListNostraUrl, request, String.class);
		// log.debug(entityPOST.getBody());

		JSONObject jsonResp = new JSONObject(entityPOST.getBody());

		JSONArray items = jsonResp.getJSONArray("items");

		String idWayPoint = "";
		if (null != items && items.length() > 0) { // No Complete

			JSONObject item = (JSONObject) items.get(0);
			System.out.println(" shipmentStatusId = " + item.getInt("shipmentStatusId"));
			System.out.println(" id = " + item.getInt("id"));
			idWayPoint = String.valueOf(item.getInt("id"));

		}
		
		
		/*
		 * ============================================================================================================================
		 * API : WaypointList
		 * ============================================================================================================================
		*/
		
		HttpHeaders headersWaypoint = new HttpHeaders();

		headersWaypoint.setContentType(MediaType.APPLICATION_JSON);
		headersWaypoint.add("GISC-CompanyId", "170");
		headersWaypoint.add("Cookie", token);

		// WebService Request
		RestTemplate respWayPointList = new RestTemplate();
		HttpEntity<String> requestWayPoint = new HttpEntity<String>(headersWaypoint);
		log.debug("Show request : " + requestWayPoint);
		
		String wayPointListNostraUrlById = wayPointListNostraUrl + "/" + idWayPoint;
		ResponseEntity<String> entityGET = respWayPointList.exchange(wayPointListNostraUrlById, HttpMethod.GET, requestWayPoint, String.class);
		// log.debug(entityPOST.getBody());

		JSONObject jsonRespWayPoint = new JSONObject(entityPOST.getBody());

		JSONArray itemsWayPoints = new JSONArray(entityGET.getBody()); 
		//JSONArray itemsWayPoints = jsonRespWayPoint.getJSONArray(jsonRespWayPoint.toJSONArray(""));
		//JSONArray itemsWayPoints = new JSONArray(jsonRespWayPoint);
		

		if (null != itemsWayPoints && itemsWayPoints.length() > 0) { // No Complete

			for (int i = 0; i < itemsWayPoints.length(); i++) {
				JSONObject itemWayPoint = (JSONObject) itemsWayPoints.get(i);
				System.out.println(" shipmentCode = " + itemWayPoint.getInt("shipmentCode"));
				System.out.println(" infoStatus = " + itemWayPoint.getInt("infoStatus"));
				System.out.println(" jobWaypointStatusId = " + itemWayPoint.getInt("jobWaypointStatusId"));
			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
