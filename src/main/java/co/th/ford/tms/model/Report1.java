package co.th.ford.tms.model;


import lombok.Data;

@Data
public class Report1 {
	
	private String systemLoadID;
	private String nostraStatus;
	private String nostraRemark;
	private String alertTypeCode;	
	private String loadDescription;	
	private String driverId; 
	private String stopSequence;	
	private String stopShippingLocation;	
	private String stopShippingLocationName;		
	private String truckNumber;	
	private String waybillNumber;	
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
	private int id;
	private String loadstopYardCode;
	private int loadID;
	private String actualStartDate;
	private String actualEndDate;
	private String etaDate;
	private String etaColor;
	private String loadDate;
	private String shipmentStatusId;
	private String shipmentStatusDesc;
	private String loadCompletedFlag;
	
}
