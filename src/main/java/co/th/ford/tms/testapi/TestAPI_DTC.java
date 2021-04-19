package co.th.ford.tms.testapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.th.ford.tms.model.JobWaypoint;

public class TestAPI_DTC {

	private static String getCreateCurrentUrl = "http://202.149.98.140:7789/shipment/create";
	private static String getSummaryGetCurrentUrl = "http://202.149.98.140:7789/shipment/summary/getCurrent";
	private static String getSummaryAllStopListUrl = "http://202.149.98.140:7789/shipment/summary/allStopList";

	public static void main(String[] args) {

		TestAPI_DTC testAPI_DTC = new TestAPI_DTC();
		testAPI_DTC.executeSQL();
		testAPI_DTC.createShipment();
		testAPI_DTC.getSummaryGetCurrent();
		testAPI_DTC.getSummaryAllStopList();

	}

	public void executeSQL() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tms_uat", "root","P@ssw0rd");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return;
		}
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name_th FROM tb_province");
			
			int resultSize = 0;
			if (rs != null) {
				rs.last();
				resultSize = rs.getRow();
				rs.beforeFirst();
			}
			
			
			if (resultSize > 0) {
				System.out.println("Size : " + resultSize);
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "  " + rs.getString("name_th"));
					
					//Integer intProvince = rs.getInt("id");
					String strProvinceName = rs.getString("name_th");
					System.out.println("Province name_th : " + strProvinceName);
					
					/*
					String query1 = "UPDATE tb_truck_profile SET PROVINCE='" 
									+ strProvinceName + "'"
									+ " WHERE PROVINCE LIKE '%" + strProvinceName + "%'";
			        stmt.executeUpdate(query1);
			        */
			        
				}
			}
			
				
				//System.out.println(rs.toString());
				
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	// summary-getCurrent
	public void createShipment() {
			System.out.println("==========> Test request createShipment.");
			String username = "AP";
			String password = "reP@ssw0rd7789";

			// create auth credentials
			String authStr = "AP:reP@ssw0rd7789";
			String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			//headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic " + base64Creds);

			Map<String, Object> mapCreate = new HashMap<String, Object>();
			// Map map = new HashMap<String, String>();
			mapCreate.put("jobCode", "1580092");
			mapCreate.put("jobName", "1580092");
			mapCreate.put("waitingTime", 120);
			mapCreate.put("AfterPlanEndDuration", 90);
			mapCreate.put("vehiclelicenseNo", "99-9999");
			mapCreate.put("planStartDateTime", "2020-11-27 00:00:00");
			mapCreate.put("planEndDateTime", "2020-11-28 00:00:00");

			List<JobWaypoint> listJobWaypoint = new ArrayList<>();
	 
			JobWaypoint jobWaypoint = new JobWaypoint();
			jobWaypoint.setAssignOrder(1);
			jobWaypoint.setJobWaypointCode("Test-01");
			jobWaypoint.setJobWaypointName("Test Drop 01");
			jobWaypoint.setDeliveryType("Both");
			jobWaypoint.setRadius((Integer) 500);
			jobWaypoint.setShiptoLat(13.07168);
			jobWaypoint.setShiptoLon(101.188614);
			jobWaypoint.setWaybillNumber("J2020-080810");
			jobWaypoint.setPlanIncomingDate("2020-11-27 13:10:00");
			jobWaypoint.setPlanOutgoingDate("2020-11-28 13:40:00" );
			listJobWaypoint.add(jobWaypoint);
			
			jobWaypoint = new JobWaypoint();
			jobWaypoint.setAssignOrder(2);
			jobWaypoint.setJobWaypointCode("Test-02");
			jobWaypoint.setJobWaypointName("Test Drop 02");
			jobWaypoint.setDeliveryType("Both");
			jobWaypoint.setRadius((Integer) 500);
			jobWaypoint.setShiptoLat(13.448182);
			jobWaypoint.setShiptoLon(101.06996);
			jobWaypoint.setWaybillNumber("J2020-080810");
			jobWaypoint.setPlanIncomingDate("2020-11-27 14:40:00");
			jobWaypoint.setPlanOutgoingDate("2020-11-28 14:55:00" );
			listJobWaypoint.add(jobWaypoint);
			
			mapCreate.put("jobs", listJobWaypoint);

			// Transform to JSON
			Gson gson = new Gson();
			String jsons = gson.toJson(mapCreate);

			// create request
			HttpEntity request = new HttpEntity(jsons, headers);

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> entityPOST = restTemplate.postForEntity(getCreateCurrentUrl, request, String.class);

			System.out.println("body : " + entityPOST.getStatusCode());
			System.out.println("body : " + entityPOST.getBody());
		}
		
	// summary-getCurrent
	public void getSummaryGetCurrent() {
		System.out.println("==========> Test request getSummaryGetCurrent");
		String username = "AP";
		String password = "reP@ssw0rd7789";

		// create auth credentials
		String authStr = "AP:reP@ssw0rd7789";
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + base64Creds);

		Map<String, String> mapSummary = new HashMap<String, String>();
		// Map map = new HashMap<String, String>();
		mapSummary.put("jobCode", "1580092");

		// Transform to JSON
		Gson gson = new Gson();
		String jsons = gson.toJson(mapSummary);

		// create request
		HttpEntity request = new HttpEntity(jsons, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> entityPOST = restTemplate.postForEntity(getSummaryGetCurrentUrl, request, String.class);

		System.out.println("body : " + entityPOST.getStatusCode());
		System.out.println("body : " + entityPOST.getBody());
	}

	// summary-allStopList
	public void getSummaryAllStopList() {
		System.out.println("==========> Test request getSummaryAllStopList");
		
		String username = "AP";
		String password = "reP@ssw0rd7789";

		// create auth credentials
		String authStr = "AP:reP@ssw0rd7789";
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + base64Creds);

		Map<String, String> mapSummary = new HashMap<String, String>();
		// Map map = new HashMap<String, String>();
		mapSummary.put("jobCode", "1580092");

		// Transform to JSON
		Gson gson = new Gson();
		String jsons = gson.toJson(mapSummary);

		// create request
		HttpEntity request = new HttpEntity(jsons, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> entityPOST = restTemplate.postForEntity(getSummaryAllStopListUrl, request, String.class);

		System.out.println("body : " + entityPOST.getStatusCode());
		System.out.println("body : " + entityPOST.getBody());
		
		JSONArray itemsWayPoints = new JSONArray(entityPOST.getBody()); 
		
		System.out.println("Show length : " + itemsWayPoints.length());
		
		for (int i = 0; i < itemsWayPoints.length(); i++) {
			
			JSONObject itemWayPoint = (JSONObject) itemsWayPoints.get(i);
			System.out.println("Show data by collectiion no : " + i + " =====> " 
								+ itemWayPoint.getString("order") + " | "
								+ itemWayPoint.getInt("jobWaypointStatusId"));
			
			
		}
	}
}