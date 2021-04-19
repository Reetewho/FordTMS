package co.th.ford.tms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.th.ford.tms.job.CronJob;
import co.th.ford.tms.service.JobService;





@Controller
@RequestMapping("/")
public class CreateJobController {
	
	@Autowired
	Environment environment;
	
	@Value("${profile}")
	private String profile;
	
	
	@Autowired
	JobService jobService;
	
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/create-cron-job" }, method = RequestMethod.GET)
	public String gotoCreateJobPage(HttpSession session,ModelMap model) {	
		
		return "create-job";
	}
	
	
	@RequestMapping(value = {"/create-cron-job" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveCreateJob(HttpSession session, @RequestParam("dataCronJobName") String dataCronJobName, @RequestParam("dataCronJobTime") String dataCronJobTime) {	
		System.out.println(">>>>>>>>>>>>>>>>>>>> Get value from dataCronJobName : " + dataCronJobName + " | dataCronJobTime" + dataCronJobTime);
		
		
		//jobService.deleteJob("MARCOR6");
		
		
		
		// Test Quartz
		//String cronExpression = "0 0/5 * * * ?";
		@SuppressWarnings("unused")
		String strSecond, strMinute, strHour;
		strSecond = "0/15";
		strMinute = "*";
		strHour = "*";
		
		
		
		//set job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("loadID", "1548087");
		
		//String cronExpression = "0 55 20 15 * ?";
		//String cronExpression = strSecond + " " + strMinute + " " + strHour + " * * ?";
		String cronExpression = dataCronJobTime;
		//jobService.scheduleCronJob("MARCOR6", CronJob.class, new Date(), cronExpression);
		//jobService.scheduleCronJob(dataCronJobName, CronJob.class, new Date(), cronExpression);
		jobService.scheduleCronJobByJobDataMap(dataCronJobName, CronJob.class, new Date(), cronExpression, jobDataMap);
			
		
		
		return "Success - POST Create Ajax.";
	}
	
	
	@RequestMapping(value = {"/stop-cron-job" }, method = RequestMethod.POST)
	@ResponseBody
	public String stopCreateJob(HttpSession session, @RequestParam("dataStopJobName") String dataStopJobName) {	
		System.out.println(">>>>>>>>>>>>>>>>>>>> Get value from dataStopJobName : " + dataStopJobName);
		
		
		//jobService.deleteJob("MARCOR6");
		jobService.deleteJob(dataStopJobName);

		
		
		return "Success - POST Stop Ajax.";
	}
}
