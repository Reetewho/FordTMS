package co.th.ford.tms.model;



import lombok.Data;

@Data
public class TaskJob {
	private String jobName;
	private String startTime;
	private String nextFireTime;
	private String lastFireTime;
}
