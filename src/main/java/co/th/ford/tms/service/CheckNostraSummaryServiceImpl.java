package co.th.ford.tms.service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.SummaryListServiceCrontrol;

@Service("checkNostraSummaryService")
public class CheckNostraSummaryServiceImpl implements CheckNostraSummaryService {
	
	private static Logger log = Logger.getLogger(CheckNostraSummaryServiceImpl.class);
	
	
	@Autowired
	private SummaryListServiceCrontrolService summaryListServiceCrontrolService;
	
	@Value("${nostra.login.url}")
	private String loginNostraUrl;
	
	@Value("${nostra.username}")
	private String username;
	 
	@Value("${nostra.password}")
	private String password;
	
	@Autowired
	private LoadStopService loadStopService;
	
	@Value("${nostra.summary.list.url}")
	private String summaryListNostraUrl;
	
	@Value("${nostra.comp.id}")
	private String companyId;
	
	@Value("${nostra.summary.list.completed.status}")
	private String completedStatus;
	
	@Value("${nostra.waypoint.list.url}")
	private String waypointListNostraUrl;
	
	@Autowired
	JobService jobService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void callWebService(String systemLoadID, String dataToken, LocalDateTime activeDate, String triggerName) {
		
		
		log.info("Execute call nostra by shipmentCode : " + systemLoadID + " | triggerName : " + triggerName);
		Map mapLogin = new HashMap<String, String>();

		mapLogin.put("username", username);
		mapLogin.put("password", password);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, mapLogin, String.class);
		HttpHeaders headers = resp.getHeaders();

		//String set_cookie = headers.getFirst(headers.SET_COOKIE);

		List<String> headerValues = headers.get("Set-Cookie");

		String token = headerValues.get(1);
		
		
		
		// Request Headers
		HttpHeaders headersSummary = new HttpHeaders();
		
		// set `content-type` header
		headersSummary.setContentType(MediaType.APPLICATION_JSON);
		headersSummary.add("GISC-CompanyId", companyId);
		headersSummary.add("Cookie", token);
		
		Map<String, String> map = new HashMap<String, String>();

		// Map map = new HashMap<String, String>();
		map.put("shipmentCode", systemLoadID);
		//log.debug(">>>>>>>>>>>>>>>>>>>> shipmentCode : " + systemLoadID);

		// Transform to JSON
		Gson gson = new Gson();
		String jsons = gson.toJson(map);
		
		// WebService Request
		RestTemplate restSummarytList = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);
		log.debug("Show request : " + request);
		ResponseEntity<String> entityPOST = restSummarytList.postForEntity(summaryListNostraUrl, request, String.class);
		//log.debug(entityPOST.getBody());
		
		
		JSONObject jsonResp = new JSONObject(entityPOST.getBody());
		
		JSONArray items = jsonResp.getJSONArray("items");
		
		if( null != items && items.length() > 0) { // No Complete
			
			log.debug("[ 200 OK ] POST-Request -> summary-list for SystemLoadID : " + systemLoadID);
			//System.out.println(">>>>>>>>>>>>>>>>>>>> [ 200 OK ] POST-Request -> summary-list");
			
			JSONObject item = (JSONObject)items.get(0);
			log.debug("[SystemLoadID : " + systemLoadID + " ] : " + " completedStatus = " + completedStatus + " | shipmentStatusId = " + item.getInt("shipmentStatusId"));
			log.debug("[SystemLoadID : " + systemLoadID + " ] : " + " shipmentStatusId Status > 1 are completed. Status is "+ completedStatus.indexOf(String.valueOf(item.getInt("shipmentStatusId"))));
			//log.debug(" shipmentStatusId Status > 1 are completed. Status is "+ String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus));
			
			//if( String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus) == -1 ) {
		    if( completedStatus.indexOf(String.valueOf(item.getInt("shipmentStatusId"))) == -1 ) {	
				SummaryListServiceCrontrol slServiceCtrl = summaryListServiceCrontrolService.findByLoadID(systemLoadID);
				
				if( null == slServiceCtrl ) {
					slServiceCtrl =new SummaryListServiceCrontrol();
					slServiceCtrl.setLoadID(systemLoadID);
					slServiceCtrl.setToken(token);
					slServiceCtrl.setActiveDate(activeDate);
					log.debug("################### create SystemLoadID : " + systemLoadID + " Control fire update load stop table ");
					//System.out.println(">>>>>>>>>>>>>>>>>>>> create control table ");
					summaryListServiceCrontrolService.save(slServiceCtrl);
				}else {
					
					//log.debug("##### update");
					//log.debug(">>>>>>>>>>>>>>>>>>>> update token");
					//log.debug("old token "+slServiceCtrl.getToken());
					log.debug("Old token [shipmentCode : " + systemLoadID + " ] : " + slServiceCtrl.getToken());
					slServiceCtrl.setToken(token);
					log.debug("New token [shipmentCode : " + systemLoadID + " ] : " + slServiceCtrl.getToken());
					summaryListServiceCrontrolService.update(slServiceCtrl);
					
				}
				
			}else { 
				// Completed
				//===================> Comment By AuiAye 2020-10-05
				//summaryListServiceCrontrolService.deleteByID(systemLoadID);
				//===================> Comment By AuiAye 2020-10-05
				summaryListServiceCrontrolService.deleteByID(systemLoadID);
				if(!triggerName.equals("") && triggerName != null) {
					jobService.deleteJob(triggerName);	
				}
				
			}
			
			
			
			
			log.debug(">>>>>>>>>>>>>>>>>>>> id for WayPoint = " + item.getInt("id"));
			//System.out.println(">>>>>>>>>>>>>>>>>>>> id for WayPoint : " + item.getInt("id"));
			String idWayPoint = "";
			idWayPoint = String.valueOf(item.getInt("id"));
			
			HttpHeaders headersWaypoint = new HttpHeaders();

			headersWaypoint.setContentType(MediaType.APPLICATION_JSON);
			headersWaypoint.add("GISC-CompanyId", "170");
			headersWaypoint.add("Cookie", token);

			// WebService Request
			RestTemplate respWayPointList = new RestTemplate();
			HttpEntity<String> requestWayPoint = new HttpEntity<String>(headersWaypoint);

			
			
			
			String wayPointListNostraUrlById = waypointListNostraUrl + "/" + idWayPoint;
			ResponseEntity<String> entityGET = respWayPointList.exchange(wayPointListNostraUrlById, HttpMethod.GET, requestWayPoint, String.class);
			// log.debug(entityPOST.getBody());
			JSONArray itemsWayPoints = new JSONArray(entityGET.getBody()); 

			if (null != itemsWayPoints && itemsWayPoints.length() > 0) {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Pass, fire API found waypointListNostra!");
				
				int systemLoadID_Waypoint = 0, stopSequence_Waypoint =0;
				
				for (int i = 0; i < itemsWayPoints.length(); i++) {
					/*
					JSONObject itemWayPoint = (JSONObject) itemsWayPoints.get(i);
					log.debug(">>>>>>>>>>>>>>>>>>>> shipmentCode : " + itemWayPoint.getInt("shipmentCode"));
					log.debug(">>>>>>>>>>>>>>>>>>>> order : " + itemWayPoint.getInt("order"));
					log.debug(">>>>>>>>>>>>>>>>>>>> infoStatus : " + itemWayPoint.getInt("infoStatus"));
					log.debug(">>>>>>>>>>>>>>>>>>>> jobWaypointStatusId : " + itemWayPoint.getInt("jobWaypointStatusId"));
					log.debug(">>>>>>>>>>>>>>>>>>>> jobWaypointStatusDisplayName : " + itemWayPoint.getInt("jobWaypointStatusDisplayName"));
					*/
					
					JSONObject itemWayPoint = (JSONObject) itemsWayPoints.get(i);
					log.info("[WayPoint-Data] shipmentCode : " + itemWayPoint.getInt("shipmentCode")
							  + " >>>>> order : " + itemWayPoint.getInt("order")
							  + " >>>>> infoStatus : " + itemWayPoint.getInt("infoStatus")
							  + " >>>>> jobWaypointName : " + itemWayPoint.get("jobWaypointName")
							  + " >>>>> jobWaypointCode : " + itemWayPoint.get("jobWaypointCode")
							  + " >>>>> actualArriveDate : " + itemWayPoint.get("actualArriveDate")
							  + " >>>>> actualDepartDate : " + itemWayPoint.get("actualDepartDate")
							  + " >>>>> jobWaypointStatusId : " + itemWayPoint.getInt("jobWaypointStatusId")
							  + " >>>>> jobWaypointStatusDisplayName : " + itemWayPoint.get("jobWaypointStatusDisplayName"));
		
					try {
						
						systemLoadID_Waypoint = itemWayPoint.getInt("shipmentCode");
						stopSequence_Waypoint = itemWayPoint.getInt("order");
						
						LoadStop loadStop = loadStopService.findLoadStopByLoadIdAndSeq(systemLoadID_Waypoint, stopSequence_Waypoint);
						if(loadStop != null) {
							//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Found LoadStop data by SystemLoadID : " + systemLoadID_Waypoint);
							
							/*
							log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Found LoadStop getId : " + loadStop.getId()
								  + " >>>>> getLoadID : " + loadStop.getLoadID()
								  + " >>>>> getSystemLoads : " + loadStop.getSystemLoads()
								  + " >>>>> getStopSequence : " + loadStop.getStopSequence()
								  + " >>>>> getTruckNumber : " + loadStop.getTruckNumber()
								  + " >>>>> getDepartureTime : " + loadStop.getDepartureTime()
								  + " >>>>> getArriveTime : " + loadStop.getArriveTime());
							*/
						
							//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ActualStartDate(LoadStop) : " + loadStop.getActualStartDate());
							//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ActualEndDate(LoadStop) : " + loadStop.getActualEndDate());
						
							String strActualArriveDate = !itemWayPoint.isNull("actualArriveDate") ?  (String)itemWayPoint.get("actualArriveDate") : null;
							String strActualDepartDate = !itemWayPoint.isNull("actualDepartDate") ?  (String)itemWayPoint.get("actualDepartDate") : null;
							//String strActualStartDate = "2020-10-02T10:12:30";
							//String strActualEndDate = "2020-10-02T13:17:03";	
							//DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"); 
							//formatter.withLocale(Locale.US);
							//LocalDateTime actualStartDateTime = LocalDateTime.parse(strActualArriveDate, formatter);
							//LocalDateTime actualEndDateTime = LocalDateTime.parse(strActualDepartDate, formatter);
						
							if( null != strActualArriveDate ) {
								LocalDateTime actualArrivalDate = LocalDateTime.parse(strActualArriveDate);
								loadStop.setActualStartDate(actualArrivalDate);
							}
							if( null != strActualDepartDate ) {
								LocalDateTime actualDepartDate = LocalDateTime.parse(strActualDepartDate);
								loadStop.setActualEndDate(actualDepartDate);
							}
							//loadStop.setActualStartDate(actualStartDateTime);
							//loadStop.setActualEndDate(actualEndDateTime);
						
							//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ActualStartDate(Update LoadStop) : " + loadStop.getActualStartDate());
							//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ActualEndDate(Update LoadStop) : " + loadStop.getActualEndDate());
						
						
							loadStopService.updateLoadStop(loadStop);
						
							log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Successfully, Update loadstop id : " + loadStop.getId() + " | SystemLoads : " + loadStop.getSystemLoads());	
						}else {
							log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Not found LoadStop data by SystemLoadID : " + systemLoadID_Waypoint);
						}
						
					

					} catch (Exception e) {
						log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Exception Not found LoadStop.");
					}
					
				}

			}else {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Fail, fire API not found waypointListNostra!");
			}
			
			//===================> Comment By AuiAye 2020-10-05
//			// Update Load Stop
//			String waypointProgress = item.getString("waypointProgress");
//			
//			if( null != waypointProgress ) {
//				
//				Integer seq = Integer.parseInt(waypointProgress.split("/")[0]);
//				
//				if( seq > 1 ) {
//					
//					log.info("### systemLoadID : "+systemLoadID + ", seq : "+seq);
//					LoadStop loadStop = loadStopService.findLoadStopByLoadIdAndSeq(Integer.parseInt(systemLoadID), seq);
//					
//					if( null != loadStop ) {
//					
//						log.info("load stop not null");
//						String actualStartDateStr = !item.isNull("actualStartDate") ?  (String)item.get("actualStartDate") : null;
//						String actualEndDateStr = !item.isNull("actualEndDate") ? (String)item.get("actualEndDate") : null;
//						String etaDateStr = !item.isNull("eta") ? (String)item.get("eta") : null;
//						
//						if( null != actualStartDateStr) {
//							LocalDateTime actualStartDate = LocalDateTime.parse(actualStartDateStr);
//							loadStop.setActualStartDate(actualStartDate);
//						}
//						if( null != actualEndDateStr) {
//							LocalDateTime actualEndDate = LocalDateTime.parse(actualEndDateStr);
//							loadStop.setActualEndDate(actualEndDate);
//						}
//						if( null != etaDateStr ) {
//							LocalDateTime etaDate = LocalDateTime.parse(etaDateStr);
//							loadStop.setEtaDate(etaDate);
//						}
//						
//						log.info("getActualStartDate "+loadStop.getActualStartDate());
//						log.info("getActualEndDate "+loadStop.getActualEndDate());
//						log.info("getEtaDate "+loadStop.getEtaDate());
//						
//						loadStopService.updateLoadStop(loadStop);
//					
//					}
//					
//				}
//				
//			}
			//===================> Comment By AuiAye 2020-10-05
			
		}
		
	}

}
