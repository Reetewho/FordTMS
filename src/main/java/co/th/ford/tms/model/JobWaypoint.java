package co.th.ford.tms.model;

import lombok.Data;

@Data
public class JobWaypoint {
	
	private int assignOrder;
	private String jobWaypointCode;
	private String jobWaypointName;
	private String deliveryType;
	private int radius;
	private double shiptoLat;
	private double shiptoLon;
	private String waybillNumber;
	
	private String PlanIncomingDate;
	private String PlanOutgoingDate;
}
