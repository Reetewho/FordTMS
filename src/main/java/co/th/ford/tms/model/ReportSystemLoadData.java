package co.th.ford.tms.model;

import lombok.Data;

@Data
public class ReportSystemLoadData {
	
	private String systemLoadID;
	private String loadID;
	private String loadDate;
	private String loadStatus;
	private String countLoadStop;
	
}
