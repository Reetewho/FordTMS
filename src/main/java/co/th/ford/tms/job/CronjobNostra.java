package co.th.ford.tms.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CronjobNostra extends QuartzJobBean implements InterruptableJob{
	private static Logger log = Logger.getLogger(CronjobNostra.class);
	
	@SuppressWarnings("unused")
	private volatile boolean toStopFlag = true;
	
	//static final Logger log = Logger.getLogger(CronJob.class.getName());
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException  {
		log.info("=========> Start Execution Action : CronjobNostra. <==========");
		JobKey key = jobExecutionContext.getJobDetail().getKey();
		log.info(DateTime.now().toString(dtf) + " SYSTEM-OUT " + CronJob.class.getName() + " ----->Cron Job started with key :" + key.getName() + ", Group :"+key.getGroup() + " , Thread Name :"+Thread.currentThread().getName() + " ,Time now :"+new Date());
		
		String strJobName = "", strJobType="";
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		strJobName = (String) jobDataMap.get("JobName");
		strJobType = (String) jobDataMap.get("JobType");
		
		log.info("JobName : " + strJobName +  " | JobType : " + strJobType);
	}
	
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.error("Stopping thread, because : UnableToInterruptJobException.");
		toStopFlag = false;
	}
}
