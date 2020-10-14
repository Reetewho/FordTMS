package co.th.ford.tms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.th.ford.tms.job.CronJob;
import co.th.ford.tms.model.TaskJob;
import co.th.ford.tms.service.JobService;





@Controller
@RequestMapping("/")
public class TaskSchedulerController {
	
	private static final Logger log = Logger.getLogger(TaskSchedulerController.class);
	
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
		log.info(">>>>>>>>>>>>>>>>>>>> Get value from dataCronJobName : " + dataCronJobName + " | dataCronJobTime" + dataCronJobTime);
		
		
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
	
	
	@RequestMapping(value = {"/show-all-job" }, method = RequestMethod.GET)
	public String gotoTaskSchedulerPage(HttpSession session, ModelMap model) {	

		
		//SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		//formatterDateTime.format(entry.getValue())
		TaskJob dataTaskJob = new TaskJob();
		List<TaskJob> lstJobData = new ArrayList<TaskJob>();
		
		List<Object[]> itemTriggers = jobService.displayAllTrigger();

    	if(itemTriggers!=null && itemTriggers.size()>0) {

	        for(Object[] itemTrigger : itemTriggers) {
	        	
	        	//log.info(">>>>>>>>>>>>>>>>>>>> TRIGGER_NAME : " + itemTrigger[0].toString());
	        	/*
	        	log.info(">>>>>>>>>>>>>>>>>>>> TRIGGER_NAME : " + itemTrigger[0].toString()
		       			+ " | START_TIME : " + itemTrigger[1]==null?"":itemTrigger[1].toString()
		       			+ " | NEXT_FIRE_TIME : " + itemTrigger[2]==null?"":itemTrigger[2].toString()
		       			+ " | LAST_FIRE_TIME : " + itemTrigger[3]==null?"":itemTrigger[3].toString());
		       	
	        	
	        	*/
	        	dataTaskJob = new TaskJob();
	        	dataTaskJob.setJobName(itemTrigger[0].toString());
	        	dataTaskJob.setStartTime(itemTrigger[1]==null?"":itemTrigger[1].toString());
	        	dataTaskJob.setNextFireTime(itemTrigger[2]==null?"":itemTrigger[2].toString());
	        	dataTaskJob.setLastFireTime(itemTrigger[3]==null?"":itemTrigger[3].toString());
	        	lstJobData.add(dataTaskJob);
	        }
    	}
		
		model.addAttribute("jobData", lstJobData);
		return "job-list";
	}
	
	@RequestMapping(value = {"/stop-job/{JobName}" }, method = RequestMethod.GET)
	public String stopTaskScheduler(HttpSession session, @PathVariable("JobName") String jobName, ModelMap model) {	
		
		List<TaskJob> lstJobData = new ArrayList<TaskJob>();
		
		try {
			if(!jobName.equals("") && jobName != null) {
				jobService.deleteJob(jobName);	
			}

			//SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");	
			TaskJob dataTaskJob = new TaskJob();
			
			
			List<Object[]> itemTriggers = jobService.displayAllTrigger();

	    	if(itemTriggers!=null && itemTriggers.size()>0) {

		        for(Object[] itemTrigger : itemTriggers) {
		        	
		        	//log.info(">>>>>>>>>>>>>>>>>>>> TRIGGER_NAME : " + itemTrigger[0].toString());
		        	
		        	dataTaskJob = new TaskJob();
		        	dataTaskJob.setJobName(itemTrigger[0].toString());
		        	dataTaskJob.setStartTime(itemTrigger[1]==null?"":itemTrigger[1].toString());
		        	dataTaskJob.setNextFireTime(itemTrigger[2]==null?"":itemTrigger[2].toString());
		        	dataTaskJob.setLastFireTime(itemTrigger[3]==null?"":itemTrigger[3].toString());
		        	lstJobData.add(dataTaskJob);
		        }
	    	}
	    	
	    	model.addAttribute("Success", "Successfully, Delete task scheduler by JobName : " + jobName + ".");
	    	log.info("Successfully, Delete task scheduler by JobName : " + jobName + ".");
	    	
		} catch (Exception e) {
			
			model.addAttribute("Error", "Error exception msg : " + e.getMessage());
			log.error("Error exception msg : " + e.getMessage());
			
		}
		

		model.addAttribute("jobData", lstJobData);
		return "job-list";
	}
	
	
}
