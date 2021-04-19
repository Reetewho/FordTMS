package co.th.ford.tms.job;

//import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
//import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import co.th.ford.tms.model.SummaryListServiceCrontrol;
import co.th.ford.tms.service.CheckNostraSummaryService;
import co.th.ford.tms.service.JobService;
import co.th.ford.tms.service.SummaryListServiceCrontrolService;

public class CronJob extends QuartzJobBean implements InterruptableJob{
	
	private static Logger log = Logger.getLogger("nostraLogger");
	
	//private static Logger log = Logger.getLogger(ScheduledJob.class);
	
	@Autowired
	private SummaryListServiceCrontrolService slServiceControl;
	
	@Autowired
	private CheckNostraSummaryService checkNostraSummaryService;
	
	@Value("${nostra.login.url}")
	private String loginNostraUrl;
	
	@Value("${nostra.username}")
	private String username;
	 
	@Value("${nostra.password}")
	private String password;
	
	@SuppressWarnings("unused")
	private volatile boolean toStopFlag = true;

	@Autowired
	JobService jobService;
	
	@Autowired
	Environment environment;
	
	//static final Logger log = Logger.getLogger(CronJob.class.getName());
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException  {
		JobKey key = jobExecutionContext.getJobDetail().getKey();
		System.out.println(DateTime.now().toString(dtf) + " SYSTEM-OUT " + CronJob.class.getName() + " ----->Cron Job started with key :" + key.getName() + ", Group :"+key.getGroup() + " , Thread Name :"+Thread.currentThread().getName() + " ,Time now :"+new Date());

		String strLoadID = "", strSystemLoadID="";
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		strLoadID = (String) jobDataMap.get("loadID");
		//strSystemLoadID = (String) jobDataMap.get("systemLoadID");
		

		
		log.debug("Execute >>>>>>>>>>>>>>>>>>> Thread : Run LoadID : "+ strLoadID);
		//log.debug("Execute >>>>>>>>>>>>>>>>>>> Thread : Run SystemLoadID : "+ strSystemLoadID);
		
		log.debug("Execute >>>>>>>>>>>>>>>>>>> checkSummaryListJob begin");
		//System.out.println(">>>>>>>>>>>>>>>>>>>> checkSummaryListJob begin");
		
		
		
		try {
			
			SummaryListServiceCrontrol sumListServiceCrontrol = slServiceControl.findByLoadID(strLoadID);
			
			try {
				
				checkNostraSummaryService.callWebService(sumListServiceCrontrol.getLoadID(), sumListServiceCrontrol.getToken(), sumListServiceCrontrol.getActiveDate(), key.getName());
				
			} catch (HttpClientErrorException e) {

				log.error(e.getMessage());

				log.debug(" Re-Login");
				//System.out.println(">>>>>>>>>>>>>>>>>>>> Re-Login");

				Map map = new HashMap<String, String>();

				map.put("username", username);
				map.put("password", password);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
				HttpHeaders headers = resp.getHeaders();

				//String set_cookie = headers.getFirst(headers.SET_COOKIE);

				List<String> headerValues = headers.get("Set-Cookie");

				String token = headerValues.get(1);
				log.debug(" New Token " + token);
				checkNostraSummaryService.callWebService(sumListServiceCrontrol.getLoadID(), token, sumListServiceCrontrol.getActiveDate(), key.getName());

			}
			
			
			
			/*
			List<SummaryListServiceCrontrol> slList = slServiceControl.findAll();

			log.debug("summary list size : " + slList.size());

			for (SummaryListServiceCrontrol sl : slList) {

				try {

					checkNostraSummaryService.callWebService(sl.getLoadID(), sl.getToken(), sl.getActiveDate());

				} catch (HttpClientErrorException e) {

					log.error(e.getMessage());

					log.debug(" Re-Login");
					//System.out.println(">>>>>>>>>>>>>>>>>>>> Re-Login");

					Map map = new HashMap<String, String>();

					map.put("username", username);
					map.put("password", password);

					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
					HttpHeaders headers = resp.getHeaders();

					//String set_cookie = headers.getFirst(headers.SET_COOKIE);

					List<String> headerValues = headers.get("Set-Cookie");

					String token = headerValues.get(1);
					log.debug(" New Token " + token);
					checkNostraSummaryService.callWebService(sl.getLoadID(), token, sl.getActiveDate());

				}

			}
			*/

			//jobService.deleteJob(key.getName());
			
			log.info("checkSummaryListJob end");

		} catch (final Exception e) {
			log.error("checkSummaryListJob error " + e.getMessage());
		}
		
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.error("Stopping thread, because : UnableToInterruptJobException.");
		toStopFlag = false;
	}


}