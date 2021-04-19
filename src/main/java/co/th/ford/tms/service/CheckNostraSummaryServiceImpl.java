package co.th.ford.tms.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SOAPBody;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import co.th.ford.tms.model.Carrier;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.SummaryListServiceCrontrol;
import co.th.ford.tms.webservice.BaseForTaskScheduler;

@Service("checkNostraSummaryService")
public class CheckNostraSummaryServiceImpl extends BaseForTaskScheduler implements CheckNostraSummaryService {
	
	//private static Logger log = Logger.getLogger(CheckNostraSummaryServiceImpl.class);
	static final Logger log = Logger.getLogger("nostraLogger");
	
	private  final String wsEndpoint = "https://fordswsprd.jdadelivers.com/webservices/services/TransportationManager2";
	
	private  final String inputXML = 
	        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cis=\"http://www.i2.com/cis\"> \r\n" +					 
			"   <soapenv:Header/>\r\n" + 
			"   <soapenv:Body>\r\n" + 
			"      <cis:processLoadStatusUpdate>      \r\n" + 
			"         <cis:ApiHeader>\r\n" + 
			"            <cis:OperationName>processLoadStatusUpdate</cis:OperationName>\r\n" + 
			"         </cis:ApiHeader>\r\n" + 
			"         <cis:LoadStatusUpdateData>      \r\n" + 
			"            <cis:SystemLoadID>%s</cis:SystemLoadID>\r\n" + 
			"            <cis:TrailerNumber>%s</cis:TrailerNumber>          \r\n" + 
			"            <cis:StopArrivalDepartureData>\r\n" + 
			"               <cis:ShippingLocationCode>%s</cis:ShippingLocationCode>\r\n" + 
			"               <cis:StopTypeEnumVal>STR_NULL</cis:StopTypeEnumVal>\r\n" + 
			"               <cis:ArrivalDateTime>%s</cis:ArrivalDateTime>\r\n" + 
			"              <cis:ArrivalEventCode>DRVRCHKIN_</cis:ArrivalEventCode>\r\n" + 
			"               <cis:DepartureDateTime>%s</cis:DepartureDateTime>\r\n" + 
			"               <cis:DepartureEventCode>DRVRCHKOUT_</cis:DepartureEventCode>\r\n" + 
			"               <cis:Latitude>%s</cis:Latitude>\r\n" + 
			"               <cis:Longitude>%s</cis:Longitude>\r\n" + 
			"               <cis:UpdateStatusFlag>true</cis:UpdateStatusFlag>\r\n" + 												
			"            </cis:StopArrivalDepartureData>\r\n" + 
			"         </cis:LoadStatusUpdateData>        \r\n" + 
			"      </cis:processLoadStatusUpdate>\r\n" + 
			"   </soapenv:Body>\r\n" + 
			"</soapenv:Envelope>";
	
	private  final String inputSetStopEtaXML = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cis=\"http://www.i2.com/cis\"> \r\n" + 
			"   <soapenv:Header /> \r\n" + 
			"   <soapenv:Body> \r\n" + 
			"     <cis:processSetStopETA>\r\n" + 
			"		<cis:ApiHeader> \r\n" + 
			"			<cis:OperationName>processSetStopETA</cis:OperationName> \r\n" + 
			"		</cis:ApiHeader>\r\n" + 
			"		<cis:StopUpdateProgressData>\r\n" + 
			"				<cis:SystemLoadID>%s</cis:SystemLoadID>	\r\n" + 
			"				<cis:CarrierCode>%s</cis:CarrierCode>			\r\n" + 
			"				<cis:ShippingLocationCode>%s</cis:ShippingLocationCode>\r\n" + 
			"				<cis:StopEvent>\r\n" + 
			"					<cis:EventCode>RETA</cis:EventCode>\r\n" + 
			"					<cis:MovementDateTime>%s</cis:MovementDateTime>\r\n" + 
			"					<cis:EstimatedDateTimeAtStop>%s</cis:EstimatedDateTimeAtStop>	\r\n" + 
			"					<cis:Latitude>%s</cis:Latitude>\r\n" + 
			"					<cis:Longitude>%s</cis:Longitude>			\r\n" + 
			"				</cis:StopEvent>\r\n" + 
			"		</cis:StopUpdateProgressData>\r\n" + 
			"		<cis:EventSourceEnumVal>EVENTSRC_API</cis:EventSourceEnumVal> \r\n" + 
			"	</cis:processSetStopETA>\r\n" + 
			"   </soapenv:Body> \r\n" + 
			"</soapenv:Envelope>";
	
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
	
	@Autowired
	LoadService loadService;
	
	@Autowired
	CarrierService cservice;
	
	@Autowired
	CarrierService carrierService;
	
	@Value("${nostra.summary.list.url}")
	private String summaryListNostraUrl;
	
	@Value("${nostra.comp.id}")
	private String companyId;
	
	@Value("${nostra.summary.list.completed.status}")
	private String completedStatus;
	
	@Value("${nostra.waypoint.list.completed.status}")
	private String waypointCompletedStatus;
	
	@Value("${nostra.waypoint.list.url}")
	private String waypointListNostraUrl;
	
	@Value("${ford.truck.replace.regex}")
	private String truckReplaceRegex;
	
	private static String replaceTruckNumber = "";
	
	@Autowired
	GsdbService gsdbService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	Environment environment;

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
		//headersSummary.setContentType(MediaType.APPLICATION_JSON);
		headersSummary.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
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
			//log.debug("[SystemLoadID : " + systemLoadID + " ] : " + " shipmentStatusId Status > 1 are completed. Status is "+ completedStatus.indexOf(String.valueOf(item.getInt("shipmentStatusId"))));
			//log.debug(" shipmentStatusId Status > 1 are completed. Status is "+ String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus));
			
			String[] strCompletedStatus = completedStatus.split(",");
			// Convert String Array to List
	        List<String> arrCompleteStatus = Arrays.asList(strCompletedStatus);
			
			
			//if( String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus) == -1 ) {
		    //if( completedStatus.indexOf(String.valueOf(item.getInt("shipmentStatusId"))) == -1 ) {	
	        log.debug("[SystemLoadID : " + systemLoadID + " ] : " + " shipmentStatusId Status = true are completed. Status is "+ arrCompleteStatus.contains(String.valueOf(item.getInt("shipmentStatusId"))));
	       
	        if(!(arrCompleteStatus.contains(String.valueOf(item.getInt("shipmentStatusId"))))) {
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
	        
	        Load loadData = loadService.findLoadBySystemLoadID(Integer.valueOf(systemLoadID));
	        log.info(">>>>>>>>>>>>>>>>>>>> etaColor : " + item.get("etaColor"));
			String strEtaColor = !item.isNull("etaColor") ? (String) item.get("etaColor") : null;
			loadData.setEtaColor(strEtaColor);
			
			
			String strShipmentStatusId = !item.isNull("shipmentStatusId") ? String.valueOf(item.getInt("shipmentStatusId")) : null;
			loadData.setShipmentStatusId(strShipmentStatusId);
			
			loadService.updateLoad(loadData);
			
			
			
			String strStopSequenceNo = !item.isNull("no") ? String.valueOf(item.getInt("no")) : null;
			String strEtaDate = !item.isNull("eta") ? (String) item.get("eta") : null;
					
			if (null != strStopSequenceNo && null != strEtaDate) {
				int nextStopSequenceNo = Integer.valueOf(strStopSequenceNo) + 1; 
				LoadStop nextLoadStop = loadStopService.findLoadStopByLoadIdAndSeq(Integer.valueOf(systemLoadID), nextStopSequenceNo);
				
				if (null != nextLoadStop) {
		
					String strGsbCode = nextLoadStop.getStopShippingLocation();
						
					Gsdb gsdb = gsdbService.findByGsdbCode(strGsbCode);
					
					if (null != gsdb) {

						try {

							Carrier carrier = carrierService.findCarrierByID(loadData.getCarrierID());
							
							LocalDateTime etaDate = LocalDateTime.now();
							
							if (null != strEtaDate) {
								etaDate = LocalDateTime.parse(strEtaDate);
							}
							NumberFormat numberFormatterForStopEta = new DecimalFormat("##.0000");
							String resLatitudeStopEta = numberFormatterForStopEta.format(gsdb.getGSDBLATITUDE());
							String resLongitudeStopEta = numberFormatterForStopEta.format(gsdb.getGSDBLONGITUDE());
							
							SOAPBody sbSetStopEta = getResponse(
									sendRequest(wsEndpoint, environment.getRequiredProperty("webservice.Authorization"),
											environment.getRequiredProperty("webservice.SOAPAction"),
											String.format(inputSetStopEtaXML, "" + Integer.valueOf(systemLoadID),
													carrier.getCarrierCode(),
													gsdb.getGSDBCODE(),
													etaDate,
													etaDate,
													resLatitudeStopEta, 
													resLongitudeStopEta
													),
											systemLoadID,
											"ProcessSetStopETA"
											)
									);
							
							String respReqStopEta = extract(sbSetStopEta,"ns1:CompletedSuccessfully");
							
							if (!respReqStopEta.equals("true")) {
								log.info("Missing Data >>>>>>>>> No CompletedSuccessfully for SetStopEta By : " 
										+ " systemLoadID : " + systemLoadID
										+ " | nextStopSequenceNo : " + String.valueOf(nextStopSequenceNo)
										+ " , ErrorMessage : "
										+ extract(sbSetStopEta, "ns1:SystemMessage") + ".");
							}else {
								log.info("==========> CompletedSuccessfully for SetStopEta By : " 
										+ " systemLoadID : " + systemLoadID
										+ " | nextStopSequenceNo : " + String.valueOf(nextStopSequenceNo) + ".");
							}
							
							
						} catch (Exception e) {
							log.error("Error Message from sendRequest SetStopEta : " + e.getMessage()
									+ ", By systemLoadID : " + systemLoadID
									+ " | nextStopSequenceNo : " + String.valueOf(nextStopSequenceNo) 
									+ ".");
						}

					}else {
						log.info("Missing Data >>>>>>>>> gsdb is null, By GsbCode : " + strGsbCode);
					}
					
				}else {
					log.info("Missing Data >>>>>>>>> Next StopSequenceNo is null, By systemLoadID : " + systemLoadID + " | nextStopSequenceNo : " + String.valueOf(nextStopSequenceNo));
				}
			
			}else {
				log.info("Missing Data >>>>>>>>> SystemLoad : " + systemLoadID + " stop process : SetStopEta, because StopSequenceNo is null or EtaDate is null.");
			}
			

			
			log.debug(">>>>>>>>>>>>>>>>>>>> id for WayPoint : " + item.getInt("id"));
			//System.out.println(">>>>>>>>>>>>>>>>>>>> id for WayPoint : " + item.getInt("id"));
			String idWayPoint = "";
			idWayPoint = String.valueOf(item.getInt("id"));
			
			HttpHeaders headersWaypoint = new HttpHeaders();

			//headersWaypoint.setContentType(MediaType.APPLICATION_JSON);
			headersWaypoint.setContentType(MediaType.APPLICATION_JSON_UTF8);
			
			headersWaypoint.add("GISC-CompanyId", companyId);
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
					
					JSONObject itemWayPoint = (JSONObject) itemsWayPoints.get(i);
					log.info("[WayPoint-Data] shipmentCode : " + itemWayPoint.getInt("shipmentCode")
							  + " | order : " + itemWayPoint.getInt("order")
							  + " | infoStatus : " + itemWayPoint.getInt("infoStatus")
							  + " | jobWaypointName : " + itemWayPoint.get("jobWaypointName")
							  + " | jobWaypointCode : " + itemWayPoint.get("jobWaypointCode")
							  + " | actualArriveDate : " + itemWayPoint.get("actualArriveDate")
							  + " | actualDepartDate : " + itemWayPoint.get("actualDepartDate")
							  + " | jobWaypointStatusId : " + itemWayPoint.getInt("jobWaypointStatusId")
							  + " | jobWaypointStatusDisplayName : " + itemWayPoint.get("jobWaypointStatusDisplayName"));
		
					try {
						
						systemLoadID_Waypoint = itemWayPoint.getInt("shipmentCode");
						stopSequence_Waypoint = itemWayPoint.getInt("order");
					
						
						String[] strWaypointCompletedStatus = waypointCompletedStatus.split(",");
						// Convert String Array to List
				        List<String> arrWaypointCompletedStatus = Arrays.asList(strWaypointCompletedStatus);

						//if (waypointCompletedStatus.indexOf(String.valueOf(itemWayPoint.getInt("jobWaypointStatusId"))) > -1) {
						if (arrWaypointCompletedStatus.contains(String.valueOf(itemWayPoint.getInt("jobWaypointStatusId")))) {
							
							log.info("Success, update load stop because :" + " jobWaypoint Status = true are completed. Status is "
									 + arrWaypointCompletedStatus.contains(String.valueOf(itemWayPoint.getInt("jobWaypointStatusId"))));
							
							LoadStop loadStop = loadStopService.findLoadStopByLoadIdAndSeq(systemLoadID_Waypoint, stopSequence_Waypoint);
							if (loadStop != null) {

								String strActualArriveDate = !itemWayPoint.isNull("actualArriveDate")
										? (String) itemWayPoint.get("actualArriveDate")
										: null;
								String strActualDepartDate = !itemWayPoint.isNull("actualDepartDate")
										? (String) itemWayPoint.get("actualDepartDate")
										: null;
								// String strActualStartDate = "2020-10-02T10:12:30";
								// String strActualEndDate = "2020-10-02T13:17:03";
								// DateTimeFormatter formatter =
								// DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
								// formatter.withLocale(Locale.US);
								// LocalDateTime actualStartDateTime = LocalDateTime.parse(strActualArriveDate,
								// formatter);
								// LocalDateTime actualEndDateTime = LocalDateTime.parse(strActualDepartDate,
								// formatter);
								// loadStop.setActualStartDate(actualStartDateTime);
								// loadStop.setActualEndDate(actualEndDateTime);

								if (null != strActualArriveDate) {
									LocalDateTime actualArrivalDate = LocalDateTime.parse(strActualArriveDate);
									//loadStop.setArriveTime(actualArrivalDate);
									loadStop.setActualStartDate(actualArrivalDate);
								}
								if (null != strActualDepartDate) {
									LocalDateTime actualDepartDate = LocalDateTime.parse(strActualDepartDate);
									//loadStop.setDepartureTime(actualDepartDate);
									loadStop.setActualEndDate(actualDepartDate);
								}

								double dblLatitude = !itemWayPoint.isNull("latitude")
										? (double) itemWayPoint.get("latitude")
										: 0.00;

								double dblLongitude = !itemWayPoint.isNull("longitude")
										? (double) itemWayPoint.get("longitude")
										: 0.00;

								NumberFormat numberFormatter = new DecimalFormat("##.0000");
								String resultLatitude = numberFormatter.format(dblLatitude);
								String resultLongitude = numberFormatter.format(dblLongitude);

								loadStop.setLatitude(Double.valueOf(resultLatitude));
								loadStop.setLongitude(Double.valueOf(resultLongitude));


								loadStopService.updateLoadStop(loadStop);
								
								
								String strTruckNumber = loadStop.getTruckNumber();
								Pattern p = Pattern.compile(truckReplaceRegex);		
								Matcher matcherTruckNumber = p.matcher(strTruckNumber);
								//strTruckNumber = matcherTruckNumber.replaceAll(replaceTruckNumber);
								int countMatch = 0;

								while(matcherTruckNumber.find()){
									countMatch++;
								    //System.out.println("Found match at: "  + matcher.start() + " to " + matcher.end());
								}
								
								if (countMatch > 0) {
									//System.out.println("Show index : " + text.indexOf("-"));
									//System.out.println("Show index : " +  strSendTruckNumber.substring(text.indexOf("-") + 1));
									strTruckNumber = strTruckNumber.substring((int) strTruckNumber.indexOf("-") + 1);
									log.info("Send Trucknumber No[TH] : " + strTruckNumber + " by SystemLoadID : " + loadStop.getSystemLoads());
								}else {
									log.info("Send Trucknumber No[ENG] : " + strTruckNumber + " by SystemLoadID : " + loadStop.getSystemLoads());
								}

								// Gsdb gsdb = gsdbService.findByGsdbCode(loadStop.getStopShippingLocation());
								if (!loadStop.getStatus().equals("update")) {
									log.info("#################### Start Execute ProcessLoadStatusUpdate. ####################");

									log.info("lsModel Data -> SystemLoadID : " + loadStop.getSystemLoads());

									try {
										
										//Load loadTest = loadService.findLoadByID(loadStop.getLoadID());
										try {

											if (null != strActualArriveDate && null != strActualDepartDate) {
												SOAPBody sb = getResponse(sendRequest(wsEndpoint,
														environment.getRequiredProperty("webservice.Authorization"),
														environment.getRequiredProperty("webservice.SOAPAction"),
														String.format(inputXML, "" + loadStop.getSystemLoads(),
																strTruckNumber,
																loadStop.getStopShippingLocation(),
																loadStop.getActualStartDate(),
																loadStop.getActualEndDate(), resultLatitude,
																resultLongitude),
														systemLoadID, "ProcessLoadStatusUpdate"));

												loadStop.setStatus(extract(sb, "ns1:CompletedSuccessfully"));

												if (!loadStop.getStatus().equals("true")) {
													loadStop.setErrorMessage(extract(sb, "ns1:SystemMessage"));
												}

											} else {
												log.info(
														"Not fire processLoadStatusUpdate send SaopUI By systemloads : "
																+ loadStop.getSystemLoads()
																+ " , because actualArriveDate is null or actualDepartDate is null.");
											}

										} catch (Exception e) {
											log.info("Error[Exception] Activity send SaopUI By systemloads : "
													+ loadStop.getSystemLoads() + " Msg : " + e.getMessage());
										}

										if (loadStop.getStatus().equals("true")) {
											loadStop.setErrorMessage("");
											loadStop.setStatus("update");

											loadStopService.updateLoadStop(loadStop);

											Load load = loadService.findLoadByID(loadStop.getLoadID());
											List<LoadStop> ls = loadStopService
													.findNotCompletedStatusByLoadID(load.getLoadID());

											if (ls != null && ls.size() == 0) {
												load.setStatus("Completed");
												load.setLastUpdateUser("System");
												load.setLastUpdateDate(LocalDateTime.now());
												loadService.updateLoad(load);
											} else if (!load.getStatus().equalsIgnoreCase("In transit")) {
												load.setStatus("In transit");
												load.setLastUpdateUser("System");
												load.setLastUpdateDate(LocalDateTime.now());
												loadService.updateLoad(load);
											}

											log.info("Success, Load Status Update of SystemLoadID : "
													+ loadStop.getSystemLoads() + " |  Shipping Location :"
													+ loadStop.getStopShippingLocation() + "  processed successfully");

										} else {

											log.info(
													"Missing Data >>>>>>>>> Error, Load Status Update of SystemLoadID : "
															+ loadStop.getSystemLoads() + " |  Shipping Location :"
															+ loadStop.getStopShippingLocation() + "  Error : "
															+ loadStop.getErrorMessage());

										}

									} catch (Exception e) {
										log.error("Error [Exception], Execute ProcessLoadStatusUpdate : "
												+ e.getMessage());
									}

									log.info(
											"#################### End Execute ProcessLoadStatusUpdate. ####################");
								}

								log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Successfully, Update loadstop id : "
										+ loadStop.getId() + " | SystemLoads : " + loadStop.getSystemLoads());
							} else {
								log.info(
										">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Not found LoadStop data by SystemLoadID : "
												+ systemLoadID_Waypoint);
							}

						}else {
							log.info("Failure, Not update load stop because :"
									 + " jobWaypoint Status = true are completed. Status is "
									 + arrWaypointCompletedStatus.contains(String.valueOf(itemWayPoint.getInt("jobWaypointStatusId"))));
						}

					} catch (Exception e) {
						log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Exception Not found LoadStop or jobWaypointStatusId.");
					}
					
				}

			}else {
				log.info("Missing Data >>>>>>>>> Fail, fire API not found waypointListNostra!");
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
