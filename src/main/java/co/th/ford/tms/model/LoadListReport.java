package co.th.ford.tms.model;

import lombok.Data;

@Data
public class LoadListReport {
	private String loadID;
	private String systemLoadID;
	private String loadDate;
	private String loadDescription;	
	private String truckNumber;	
	private String loadStartDateTime;	
	private String loadEndDateTime;
	private String completedFlag;
	private String gatein;
	private String gateout;
	private String carrierID;
	private String driverid;
	private String assignname;
}
