package co.th.ford.tms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import co.th.ford.tms.job.CronJob;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.JobWaypoint;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.ReportSystemLoadData;
import co.th.ford.tms.model.Yard;
import co.th.ford.tms.service.CarrierService;
import co.th.ford.tms.service.CheckNostraSummaryService;
import co.th.ford.tms.service.GsdbService;
import co.th.ford.tms.service.JobService;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.LoadStopService;
import co.th.ford.tms.service.SummaryService;
import co.th.ford.tms.service.YardService;

@Controller
@RequestMapping("/")
public class ReportController {

	private static final Logger log = Logger.getLogger(ReportController.class);

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
	GsdbService gsdbService;
	
	@Autowired
	JobService jobService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CheckNostraSummaryService checkNostraSummaryService;

	@Autowired
	Environment environment;

	 // UAT 
	 @Value("${nostra.create.shipment.url}")
	 private String createShipmentNostraUrl;
	
	 @Value("${nostra.login.url}")
	 private String loginNostraUrl;
	
	 @Value("${nostra.summary.list.url}")
	 private String summaryListNostraUrl;
	 
	 @Value("${nostra.comp.id}")
	 private String companyId;
	 
	 @Value("${nostra.username}")
	 private String username;
	 
	 @Value("${nostra.password}")
	 private String password;
	 
	 @Value("${nostra.arrive.plan.minute}")
	 private int calArrivePlanMinute;
	 
	 private static RestTemplate restTemplate = new RestTemplate();
	 
	 private static ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = { "/report" }, method = RequestMethod.GET)
	public String login(HttpSession session, ModelMap model) {
		
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		
		List<Report1> report = cservice.findAll(getThaiDate(today), getThaiDate(yesterday));

		log.debug("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
		model.addAttribute("startDate", yesterday);
		model.addAttribute("endDate", today);
		
		return "report1";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/report" }, method = RequestMethod.POST)
	public String login(HttpSession session, @RequestParam String startDate, @RequestParam String endDate,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		String nextPage = "report1";

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		if (startDate.trim().equals("")) {
			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.startDate", new String[] { startDate }, Locale.getDefault()));
		} else if (endDate.trim().equals("")) {
			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.endDate", new String[] { endDate }, Locale.getDefault()));
		} else {
			LocalDate startlocalDate = LocalDate.now();
			try {

				startlocalDate = LocalDate.parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.startDate",
						new String[] { startDate }, Locale.getDefault()));
			}
			if (model.get("errorMsg") == null) {
				try {

					LocalDate endlocalDate = LocalDate.parse(endDate);
					List<Report1> report = cservice.findAll(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					// List<Report1> report = cservice.findAll(startlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")));
					model.addAttribute("report", report);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.endDate",
							new String[] { endDate }, Locale.getDefault()));
				}
			}
		}

		return nextPage;
	}
	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------
	 */

	@RequestMapping(value = { "/adminreport" }, method = RequestMethod.GET)
	public String logins(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		// List<Employee> employees = service.findAllEmployees();
		// model.addAttribute("employees", employees);
		List<Report1> report = cservice.findAll(getThaiDate(today), getThaiDate(yesterday));

		log.debug("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
		model.addAttribute("startDate", yesterday);
		model.addAttribute("endDate", today);
		return "adminreport";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/adminreport" }, method = RequestMethod.POST)
	public String logins(HttpSession session, @RequestParam String startDate, @RequestParam String endDate,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		String nextPage = "adminreport";

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		if (startDate.trim().equals("")) {
			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.startDate",
					new String[] { startDate }, Locale.getDefault()));
		} else if (endDate.trim().equals("")) {
			model.addAttribute("errorMsg",
					messageSource.getMessage("NotEmpty.report.endDate", new String[] { endDate }, Locale.getDefault()));
		} else {
			LocalDate startlocalDate = LocalDate.now();
			try {

				startlocalDate = LocalDate.parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.startDate",
						new String[] { startDate }, Locale.getDefault()));
			}
			if (model.get("errorMsg") == null) {
				try {

					LocalDate endlocalDate = LocalDate.parse(endDate);
					List<Report1> report = cservice.findAll(getThaiDate(startlocalDate), getThaiDate(endlocalDate));
					// List<Report1> report = cservice.findAll(startlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")));
					model.addAttribute("report", report);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.endDate",
							new String[] { endDate }, Locale.getDefault()));
				}
			}
		}

		return nextPage;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------
	 */

	@RequestMapping(value = { "/nostra" }, method = RequestMethod.GET)
	public String logins1(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		// List<Employee> employees = service.findAllEmployees();
		// model.addAttribute("employees", employees);
		List<Report1> report = cservice.findAllLoadId(getThaiDate(today), getThaiDate(yesterday));

		log.debug("----------> ! Test Date Times In Report  ! <----------" + report);

		model.addAttribute("report", report);
		model.addAttribute("startDate", today);
		model.addAttribute("endDate", today);
		return "nostra";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/nostra" }, method = RequestMethod.POST)
	public String logins1(HttpSession session, @RequestParam String startDate, @RequestParam String endDate,
			HttpServletRequest requestServer, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		String nextPage = "nostra";

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		if (startDate.trim().equals("")) {
			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.startDate", null, Locale.getDefault()));
		} else if (endDate.trim().equals("")) {
			model.addAttribute("errorMsg",
					messageSource.getMessage("NotEmpty.report.endDate", null, Locale.getDefault()));
		} else {
			LocalDate startlocalDate = LocalDate.now();
			try {

				startlocalDate = LocalDate.parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.startDate", null, Locale.getDefault()));
			}
			if (model.get("errorMsg") == null) {
				try {

					Map map = new HashMap<String, String>();
//					map.put("username", "marcotechnology");
//					map.put("password", "Z)2opyqj");

					 map.put("username", username);
					 map.put("password", password);

					
					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
					HttpHeaders headers = resp.getHeaders();

					String set_cookie = headers.getFirst(headers.SET_COOKIE);

					List<String> headerValues = headers.get("Set-Cookie");

					log.debug("Response: " + resp.toString() + "\n");
					log.debug("Set-Cookie: " + set_cookie + "\n");
					log.debug("********* FINISH *******");

					log.debug(resp.getStatusCode());
					log.debug("Show body : " + resp.getBody());

					HttpSession sessionAPI = requestServer.getSession();
					sessionAPI.setAttribute("KeyCookie", headerValues.get(1));

					LocalDate endlocalDate = LocalDate.parse(endDate);
					List<Report1> report = cservice.findAllLoadId(getThaiDate(startlocalDate),
							getThaiDate(endlocalDate));
					// List<Report1> report = cservice.findAll(startlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")), endlocalDate.toString(
					// DateTimeFormat.forPattern("yyyy-MM-dd")));
					List<Yard> Yards = ydervice.findAllYard();

					model.addAttribute("Yards", Yards);
					model.addAttribute("report", report);
					log.debug("----------> ! Test Yards  In Report  ! <----------" + report);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.endDate", null, Locale.getDefault()));
				}
			}
		}

		return nextPage;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/updateNostraAPI" }, method = RequestMethod.POST)
	public String updateNostraAPI(HttpSession session, HttpServletRequest requestServer, @RequestParam String startDateUpdateNostra,
			@RequestParam String endDateUpdateNostra, @RequestParam("console-select-rows") String getSelectItemData, ModelMap model)
			throws InterruptedException, JsonProcessingException {
		
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		String nextPage = "nostra";

		String json = getSelectItemData;
		
		log.info("getSelectItemData : " + getSelectItemData);
		
		JSONObject obj = new JSONObject(json);
		JSONArray arr = obj.getJSONArray("theGroupData");
		List<LoadStop> allListLoadStop = new ArrayList<LoadStop>();

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S"); 
		formatter.withLocale(Locale.US);
		LocalDateTime arrivePlanLocalDateTime = LocalDateTime.now();

		Date arrivePlanDate = new Date();
		Date calArrivePlanDate = new Date();
		
		List<String> dataLoadNotWaybillNumber = new ArrayList<>();
		List<String> dataLoadNotFoundGSDB = new ArrayList<>();
		List<String> dataLoadNullDptArv = new ArrayList<>();
		List<String> dataLoadNotCreate = new ArrayList<>();
		List<String> dataLoadSuccess = new ArrayList<>();
		
		
		
		int intMinute = 0, intHour = 0, intDate = 0, intMonth=0, intYear=0;
		String createJobName = "";
		
		for (int i = 0; i < arr.length(); i++) {

			if (arr.getJSONObject(i).getString("SystemID") != null) {
				createJobName = "";
				intMinute = 0;
				intHour = 0;
				intDate = 0;
				intMonth = 0;
				intYear = 0;

				arrivePlanDate = new Date();
				calArrivePlanDate = new Date();

				int intLoadID = 0;
				intLoadID = Integer.parseInt(arr.getJSONObject(i).getString("LoadID"));

				int intsystemid = Integer.parseInt(arr.getJSONObject(i).getString("SystemID"));
				LoadStop loadStop = lsservice.findLoadStopByID(intsystemid);
				log.debug("SystemID for send nostra : " + intsystemid);
				
				String strSystemLoadID = arr.getJSONObject(i).getString("SystemLoadID");
				strSystemLoadID=strSystemLoadID.replace("\n","");
				strSystemLoadID=strSystemLoadID.replace("\t","");
				
				int systemLoadID = Integer.parseInt(strSystemLoadID);
				
				List<LoadStop> nullDptArvloadStops = lsservice.findLoadStopByLoadIDAndNullDptArv(intLoadID);
				
				List<ReportSystemLoadData> reportLoadData = lservice.findLoadGroupBySystemLoad(systemLoadID);

				
				
				String strWaybillNumber = !arr.getJSONObject(i).getString("WaybillNumber").equals("")
											? (String) arr.getJSONObject(i).getString("WaybillNumber").trim()
										    : null;
				
				if ((null != strWaybillNumber) && !(strWaybillNumber.isEmpty())) {

					if (nullDptArvloadStops.size() == 0 && reportLoadData.size() == 1) {

						Load loads = lservice.findLoadByID(intLoadID);

						log.info("----------> SystemLoadID : " + systemLoadID);
						log.info("----------> GsdbCode : " + arr.getJSONObject(i).getString("GsdbCode"));
						log.info("----------> TruckNumber : " + arr.getJSONObject(i).getString("TruckNumber"));
						log.info("----------> WaybillNumber : " + arr.getJSONObject(i).getString("WaybillNumber"));
						log.info("----------> ArrivePlan : " + arr.getJSONObject(i).getString("ArrivePlan"));
						log.info("----------> DeparturePlan : " + arr.getJSONObject(i).getString("DeparturePlan"));
						log.info("----------> DepartureActual : " + arr.getJSONObject(i).getString("DepartureActual"));

						arrivePlanLocalDateTime = LocalDateTime
								.parse((String) arr.getJSONObject(i).getString("ArrivePlan"), formatter);
						log.info("----------> Convert to date [ArrivePlan] : " + arrivePlanLocalDateTime);

						arrivePlanDate = arrivePlanLocalDateTime.toDate();

						Calendar arrivePlanCalendar = Calendar.getInstance();
						arrivePlanCalendar.setTime(arrivePlanDate);
						arrivePlanCalendar.add(Calendar.MINUTE, -calArrivePlanMinute);

						log.info("----------> Convert to Calendar [ArrivePlan] : " + arrivePlanCalendar.getTime());

						intMinute = arrivePlanCalendar.get(Calendar.MINUTE);
						intHour = arrivePlanCalendar.get(Calendar.HOUR_OF_DAY);
						intDate = arrivePlanCalendar.get(Calendar.DATE);
						intMonth = Integer.valueOf(arrivePlanCalendar.get(Calendar.MONTH) + 1);
						intYear = arrivePlanCalendar.get(Calendar.YEAR);

						calArrivePlanDate = arrivePlanCalendar.getTime();

						log.info("----------> calArrivePlanDate : " + calArrivePlanDate);

						HttpSession sessionAPI = requestServer.getSession();
						// Thread.sleep(5000);
						// create headers
						HttpHeaders headersSummary = new HttpHeaders();
						String strCookie = "";
						strCookie = (String) sessionAPI.getAttribute("KeyCookie");

						// set `content-type` header
						// headersSummary.setContentType(MediaType.APPLICATION_JSON);
						headersSummary.setContentType(MediaType.APPLICATION_JSON_UTF8);
						headersSummary.add("GISC-CompanyId", companyId);
						// headersSummary.add("GISC-CompanyId", "40");
						headersSummary.add("Cookie", strCookie);

						String jobId = Integer.toString(systemLoadID);
						String TruckNumberId = loadStop.getTruckNumber();

						LocalDateTime localDateTime1 = loads.getLoadStartDateTime();
						String dateFormat1 = "yyyy/MM/dd HH:mm:ss";
						String date1 = localDateTime1.toString(dateFormat1);

						LocalDateTime localDateTime2 = loads.getLoadEndDateTime();
						String dateFormat2 = "yyyy/MM/dd HH:mm:ss";
						String date2 = localDateTime2.toString(dateFormat2);

						if (!jobId.equals("0")) {

							Map map = new HashMap<>();

							// Map map = new HashMap<String, String>();
							map.put("jobCode", jobId);
							map.put("jobName", jobId);
							map.put("waitingTime", "120");
							map.put("vehiclelicenseNo", arr.getJSONObject(i).getString("TruckNumber"));
							map.put("planStartDateTime", date1);
							map.put("planEndDateTime", date2);
							// #################### Comment by AuiAye : 2020-10-21 ####################
							// map.put("startShipment", "D");
							// map.put("jobAutoFinish", "Manual");
							// #################### Comment by AuiAye : 2020-10-21 ####################
							map.put("startShipment", "A");
							map.put("closeShipment", "D");
							map.put("jobAutoFinish", "AfterPlanEnd");
							map.put("AfterPlanEndDuration", "90");
							map.put("lateShipmentRole", "W");
							map.put("lastWaypointIsTerminal", "false");
							map.put("waypointFindBestSequent", "false");

							// Call findLoadByID
							Load loadID1 = lservice
									.findLoadByID(Integer.parseInt(arr.getJSONObject(i).getString("LoadID")));
							log.info("Test-Start-Load-ID " + loadID1);

							// Update driver id
							// loadID1.setDriverid(arr.getJSONObject(i).getString("Driverids"));
							// lservice.updateLoad(loadID1);

							// Get Load stop
							JobWaypoint jobWaypoint;
							List<JobWaypoint> lstJobWaypoint = new ArrayList<>();
							List<LoadStop> loadStops = lsservice.findLoadStopByLoadID(intLoadID);
							for (LoadStop loadStop2 : loadStops) {

								Gsdb gsdb = gsdbService.findByGsdbCode(loadStop2.getStopShippingLocation());

								if (gsdb != null) {
									log.debug("----------> gsdb.toString : " + gsdb.toString());
									log.debug("----------> RADIUS : " + gsdb.getGSDBRADIUS());
									log.debug("----------> Latitude : " + gsdb.getGSDBLATITUDE());
									log.debug("----------> Longitude : " + gsdb.getGSDBLONGITUDE());

									String JobWaypointNames = loadStop2.getStopShippingLocationName();
									log.info("Test-Load-ID " + loadStop2);

									LocalDateTime localDateTime3 = loadStop2.getArriveTime();
									String dateFormat3 = "yyyy/MM/dd HH:mm:ss";
									String date3 = localDateTime3.toString(dateFormat3);

									LocalDateTime localDateTime4 = loadStop2.getDepartureTime();
									String dateFormat4 = "yyyy/MM/dd HH:mm:ss";
									String date4 = localDateTime4.toString(dateFormat4);

									loadStop2.setSystemLoads(systemLoadID);
									loadStop2.setTruckNumber(arr.getJSONObject(i).getString("TruckNumber")); // Truck
																												// Number
									loadStop2.setWaybillNumber(arr.getJSONObject(i).getString("WaybillNumber")); // Job
																													// Number
									// loadStop2.setLoadstopYardCode(arr.getJSONObject(i).getString("Yard"));
									lsservice.updateLoadStop(loadStop2);
									jobWaypoint = new JobWaypoint();
									jobWaypoint.setAssignOrder(loadStop2.getStopSequence());
									jobWaypoint.setPlanIncomingDate(date3);
									jobWaypoint.setPlanOutgoingDate(date4);
									jobWaypoint.setJobWaypointCode(loadStop2.getStopShippingLocation());
									jobWaypoint.setJobWaypointName(JobWaypointNames);
									jobWaypoint.setDeliveryType("Both");
									// jobWaypoint.setRadius(500);
									// jobWaypoint.setShiptoLat(loadStop2.getLatitude());
									// jobWaypoint.setShiptoLon(loadStop2.getLongitude());
									jobWaypoint.setRadius(gsdb.getGSDBRADIUS().intValue());
									jobWaypoint.setShiptoLat(gsdb.getGSDBLATITUDE());
									jobWaypoint.setShiptoLon(gsdb.getGSDBLONGITUDE());
									jobWaypoint.setWaybillNumber(loadStop2.getWaybillNumber());

									lstJobWaypoint.add(jobWaypoint);
								} else {

									dataLoadNotFoundGSDB.add(String.valueOf(systemLoadID));
									log.error("----------> GSDB_CODE not found.");
								}

							}

							map.put("jobWaypoint", lstJobWaypoint);

							// log.info("Map Data for POST[CreateShipmentNostraUrl] : " + map);

							Gson gson = new Gson();
							String jsons = gson.toJson(map);

							log.info("Show-request jsons[map] : " + jsons);

							// model.addAttribute("Success", "Successfully");
							// Call WebService for update Create Shipment

							RestTemplate respCreatetList = new RestTemplate();
							HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);
							log.debug("WebService request : " + request);
							ResponseEntity<String> entityPOST = respCreatetList.postForEntity(createShipmentNostraUrl,
									request, String.class);
							log.debug("WebService response : " + entityPOST.getBody());

							JSONObject jsonResp = new JSONObject(entityPOST.getBody());

							String nostraStatus = jsonResp.getBoolean("success") ? "true" : "false";
							String nostraRemark = jsonResp.getString("message");

							loadID1.setNostraStatus(nostraStatus);
							loadID1.setNostraRemark(
									nostraRemark.length() > 200 ? nostraRemark.substring(0, 200) : nostraRemark);

							lservice.updateLoad(loadID1);

							if (nostraStatus.equals("true")) {

								dataLoadSuccess.add(jobId);

								checkNostraSummaryService.callWebService(String.valueOf(systemLoadID), strCookie,
										localDateTime1, "");

								JobDataMap jobDataMap = new JobDataMap();
								jobDataMap.put("loadID", String.valueOf(systemLoadID));

								// String cronExpression = "0 55 20 15 * ?";
								// String cronExpression = strSecond + " " + strMinute + " " + strHour + " * *
								// ?";
								// String cronExpression = "0" + " " + intMinute + " " + intHour + " " + intDate
								// + " " + intMonth + " ? " + intYear;
								String cronExpression = "0 0/10 * * * ?";
								// Calendar calendarCurrent = Calendar.getInstance();
								SimpleDateFormat formatterTime = new SimpleDateFormat("yyyyMMddHHmm");

								// createJobName = String.valueOf(systemLoadID) + "_"+
								// formatterTime.format(calendarCurrent.getTime());
								createJobName = String.valueOf(systemLoadID) + "_"
										+ formatterTime.format(calArrivePlanDate);

								jobService.scheduleCronJobByJobDataMap(createJobName, CronJob.class, calArrivePlanDate,
										cronExpression, jobDataMap);

							} else {
								log.error("Unsuccessfully, Craete shipment on Nostra by SystemLoadID : " + jobId);
								dataLoadNotCreate.add(jobId);
							}
							
							
							
							
							
							
							
							
							

						} else {

							model.addAttribute("WarningNotFound",
									"Unsuccessfully, Not select systemload for create shipment on Nostra.");

						}

					} else {

						log.error("SystemLoadID : " + String.valueOf(systemLoadID)
								+ " | DepartureTime is null OR ArriveTime is null OR SystemLoad duplicate.");
						dataLoadNullDptArv.add(String.valueOf(systemLoadID));

					}

				} else {

					log.error("SystemLoadID : " + String.valueOf(systemLoadID) + " | WaybillNumber is null .");
					dataLoadNotWaybillNumber.add(String.valueOf(systemLoadID));
			
				}
				

				
			}
		}
		
		
		if (dataLoadSuccess != null && !(dataLoadSuccess.isEmpty())) {
			model.addAttribute("Success", "Successfully, create shipment on Nostra by systemloads : " + dataLoadSuccess.toString());
		}
		
		
		if (dataLoadNotCreate != null && !(dataLoadNotCreate.isEmpty())) {
			model.addAttribute("Warning", "Unsuccessfully for createShipment on Nostra : " + dataLoadNotCreate.toString());
		}
		
		
		if(dataLoadNullDptArv != null && !(dataLoadNullDptArv.isEmpty())){
			model.addAttribute("ErrorNullDptArv", "DepartureTime is null OR ArriveTime is null by SystemLoadIDs : " + dataLoadNullDptArv.toString());
		}
		
		
		if(dataLoadNotFoundGSDB != null && !(dataLoadNotFoundGSDB.isEmpty())){
			model.addAttribute("Error", "GSDB_CODE not found by LoadID : " + dataLoadNotFoundGSDB.toString());
		}
		
		if((dataLoadNotWaybillNumber!=null) && !(dataLoadNotWaybillNumber.isEmpty())) {
			model.addAttribute("ErrorNullWaybillNumber", "WaybillNumber is null by SystemLoadIDs : " + dataLoadNotWaybillNumber.toString());
		}
		
		
		model.addAttribute("startDate", startDateUpdateNostra);
		model.addAttribute("endDate", endDateUpdateNostra);

		if (startDateUpdateNostra.trim().equals("")) {

			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.startDate",
					new String[] { startDateUpdateNostra }, Locale.getDefault()));

		} else if (endDateUpdateNostra.trim().equals("")) {

			model.addAttribute("errorMsg", messageSource.getMessage("NotEmpty.report.endDate",
					new String[] { endDateUpdateNostra }, Locale.getDefault()));

		} else {

			LocalDate startlocalDate = LocalDate.now();

			try {

				startlocalDate = LocalDate.parse(startDateUpdateNostra);

			} catch (Exception e) {

				e.printStackTrace();
				model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.startDate",
						new String[] { startDateUpdateNostra }, Locale.getDefault()));

			}

			if (model.get("errorMsg") == null) {

				try {

					LocalDate endlocalDate = LocalDate.parse(endDateUpdateNostra);
					List<Report1> report = cservice.findAllLoadId(getThaiDate(startlocalDate),
							getThaiDate(endlocalDate));
					List<Yard> Yards = ydervice.findAllYard();
					model.addAttribute("Yards", Yards);
					model.addAttribute("report", report);
					log.debug("----------> ! Test Yards  In Report  ! <----------" + report);

				} catch (Exception e) {

					e.printStackTrace();
					model.addAttribute("errorMsg", messageSource.getMessage("InvalidFormatDate.report.endDate",
							new String[] { endDateUpdateNostra }, Locale.getDefault()));

				}

			}
		}
		
		return nextPage;
	}
	
	

	@RequestMapping(value = { "/nostra-detail/{loadID}/{systemLoadID}" }, method = RequestMethod.GET)
	public String NostraDetails(HttpSession session, HttpServletRequest requestServer, @PathVariable int loadID,
			@PathVariable int systemLoadID, ModelMap model) {
		// if(!checkAuthorization(session))return "redirect:/login";
		String nextPage = "nostradetail";

		List<Report1> ReportbySystemLoadId = cservice.findSystembyLoadId(loadID);

		for (Report1 loadStops : ReportbySystemLoadId) {
			log.debug("---------------Test-Load-By-LoadStop------------------>" + loadStops
					+ "<-------------------------------------------");
		}
		
		/*
		HttpSession sessionAPI = requestServer.getSession();

		HttpHeaders headersSummary = new HttpHeaders();
		String strCookie = "";
		strCookie = (String) sessionAPI.getAttribute("KeyCookie");

		// set `content-type` header
		headersSummary.setContentType(MediaType.APPLICATION_JSON);
		headersSummary.add("GISC-CompanyId", companyId);
		// headersSummary.add("GISC-CompanyId", "40");
		headersSummary.add("Cookie", strCookie);

		Map map = new HashMap<>();

		// Map map = new HashMap<String, String>();
		map.put("shipmentCode", systemLoadID);

		Gson gson = new Gson();
		String jsons = gson.toJson(map);

		log.debug("Show-request : " + jsons);

		RestTemplate respSummarytList = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);
		log.debug("Show request : " + request);
		ResponseEntity<String> entityPOST = respSummarytList.postForEntity(summaryListNostraUrl, request, String.class);
		log.debug(entityPOST.getBody());

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootJson;
		try {

			rootJson = objectMapper.readTree(entityPOST.getBody());
			// log.debug("Show body [timestamp] : " +
			// rootJson.path("timestamp").path("actualStartDate").textValue());
			//
			// log.debug("Show body [timestamp] : " +
			// rootJson.path("timestamp").asText("actualStartDate"));
			// log.debug("Show body [timestamp] : " +
			// rootJson.path("timestamp").asText("actualEndDate"));

			// LocalDateTime StartdateTime =
			// LocalDateTime.parse(rootJson.path("actualStartDate").asText());
			// LocalDateTime EnddateTime =
			// LocalDateTime.parse(rootJson.path("actualEndDate").asText());

			// Summary FindSum = smervice.findSummaryLoadByID(loadID);
			// FindSum.setLoadIDSummary(rootJson.path("shipmentCode").asText());
			// FindSum.setSummaryStatus(rootJson.path("shipmentStatusId").asText());
			// FindSum.setStopDate(StartdateTime);
			// FindSum.setStopDate(EnddateTime);

			// smervice.saveSummarylist(FindSum);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		*/
		
		model.addAttribute("lsLoadID", ReportbySystemLoadId);
		return nextPage;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(value = { "/view-on-map/{getSystemLoadID}" }, method = RequestMethod.GET)
	public String ViewOnNostraMap(HttpSession session, HttpServletRequest requestServer, @PathVariable("getSystemLoadID") int systemLoadID, ModelMap model) {
		
		log.info("Show systemLoad No : " + systemLoadID);
		
		Map map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
		HttpHeaders headers = resp.getHeaders();

		//String set_cookie = headers.getFirst(headers.SET_COOKIE);
		//List<String> headerValues = headers.get("Set-Cookie");

		log.debug("Response: " + resp.toString() + "\n");
		//log.debug("Set-Cookie: " + set_cookie + "\n");
		log.debug("********* FINISH *******");

		log.debug(resp.getStatusCode());
		log.debug("Show body : " + resp.getBody());
		
		JsonNode root;
		String getCompany = null;
		String getToken = null;
		try {
			root = objectMapper.readTree(resp.getBody());	
			getToken = root.path("token").asText();
			getCompany = root.path("companyId").asText();
			//log.info("Show body [token] : " + getToken);
		
			
		} catch (JsonProcessingException e) {
			log.error("Error JsonProcessingException : " + e.getMessage());
		} catch (IOException e) {
			log.error("Error IOException : " + e.getMessage());
		} catch(Exception e) {
			log.error("Error Exception : " + e.getMessage());
		}
			
		if(!(getToken.equals("")) || !(getToken.isEmpty())) {
			log.info("Show get token : " + getToken);
		}else {
			log.info("Show get token is empty");
		}
		
		if(!(getCompany.equals("")) || !(getCompany.isEmpty())) {
			log.info("Show get company : " + getCompany);
		}else {
			log.info("Show get token is company");
		}
		//1658290
		//String getJobCode = "1660266";
		
		String redirectUrl = "https://mapwidget.nostralogistics.com/" + getCompany + "/" + String.valueOf(systemLoadID) + "/" + getToken;
		
		return "redirect:" + redirectUrl;
		
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------------------------------------------------
	 */

	public boolean checkAuthorization(HttpSession session) {
		if (session.getAttribute("S_FordUser") == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getThaiDate(LocalDate date) {
		int plus543 = 0;
		if (environment.getRequiredProperty("local.datetime").equals("th"))
			plus543 = 543;
		return (date.getYear() + plus543) + "-" + date.getMonthOfYear() + "-" + date.getDayOfMonth();
//		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
//		fmt.withLocale(Locale.US);
//		
//		return date.toString(fmt);
		
	}
}
