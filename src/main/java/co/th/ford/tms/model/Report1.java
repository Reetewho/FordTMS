package co.th.ford.tms.model;


import lombok.Data;

@Data
public class Report1 {
	
	private String systemLoadID;
	private String alertTypeCode;	
	private String loadDescription;	
	private String driverId; 
	private String stopSequence;	
	private String stopShippingLocation;	
	private String stopShippingLocationName;		
	private String truckNumber;	
	private String lastUpdateUser;
	private String departureTime;	
	private String arriveTime;
	private String loadStartDateTime;	
	private String loadEndDateTime;
	private String completedFlag;
	private String latitude;
	private String longitude;
	private String movementDateTime;
	private String estimatedDateTime;
	private String assignname;
	private String loadstopremark;
	private String contactnumber;
	private String lastUpdateDate;
}
