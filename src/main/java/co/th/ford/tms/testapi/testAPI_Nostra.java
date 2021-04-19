package co.th.ford.tms.testapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testAPI_Nostra {

    private static final String LOGIN_NOSTRA_URL = "https://l2apitracking.nostralogistics.com/api/user/login";
    private static final String CREATE_NOSTRA_URL = "https://l2stguatapitracking.nostralogistics.com/api/shipment/Create";
    private static RestTemplate restTemplate = new RestTemplate();
    
    public static void main(String[] args) {
    	
    	testAPI_Nostra springRestClient = new testAPI_Nostra();

    	springRestClient.postLoginNostra();
    	//springRestClient.postCreateNostra();
	}

    private final ObjectMapper objectMapper = new ObjectMapper();

    
    @SuppressWarnings({ "unchecked", "unused" })
	private void postLoginNostra() {

		Map map = new HashMap<String, String>();
		map.put("username", "marcotechnology");
		map.put("password", "Z)2opyqj");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.postForEntity(LOGIN_NOSTRA_URL, map, String.class);

		System.out.println(resp.getStatusCode());
		System.out.println("Show body : " + resp.getBody());

		JsonNode root;
		try {
			root = objectMapper.readTree(resp.getBody());
			System.out.println("Show body [companyId] : " + root.path("companyId").asText());
			System.out.println("Show body [token] : " + root.path("token").asText());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    
    @SuppressWarnings({ "unchecked", "unused" })
  	private void postCreateNostra() {
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Content-Type", "application/json");
        headers.set("GISC-CompanyId", "40");
     
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        System.out.println("Show Test : " + headers);

  		Map map = new HashMap<String, String>();
  		map.put("jobCode", "1504129");
  		map.put("jobName", "1504129");
  		map.put("waitingTime", "60");
  		map.put("vehiclelicenseNo", "");
  		map.put("planStartDateTime", "2020-09-03 14:00");
  		map.put("planEndDateTime", "2020-09-03 16:25");
  		map.put("startShipment", "D");
  		map.put("closeShipment", "D");
  		map.put("jobAutoFinish", "Manual");
  		map.put("lateShipmentRole", "W");
  		map.put("lastWaypointIsTerminal", "false");
  		map.put("waypointFindBestSequent", "false");
  		map.put("jobWaypoint", "");
  		
  		System.out.println("Show body : " + map);

  		RestTemplate restTemplate = new RestTemplate();
  		ResponseEntity<String> resp = restTemplate.postForEntity(CREATE_NOSTRA_URL, map, String.class);

  		System.out.println(resp.getStatusCode());
  		System.out.println("Show body : " + resp.getBody());
 	
  		JsonNode root;
		try {
			root = objectMapper.readTree(resp.getBody());
			System.out.println("Show body [companyId] : " + root.path("Message").asText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
         
      }
    
}
