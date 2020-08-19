package co.th.ford.tms.model;

import lombok.Data;

@Data
public class PaymentReport1 {
	private String systemLoadID;
	private String loadDescription;	
	private String truckNumber;	
	private String loadStartDateTime;	
	private String loadEndDateTime;
	private String completedFlag;
	private String gatein;
	private String gateout;
	private String driverId;
}
