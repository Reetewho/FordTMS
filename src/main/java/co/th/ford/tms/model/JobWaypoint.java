package co.th.ford.tms.model;

import lombok.Data;

@Data
public class JobWaypoint {
	
	private int assignOrder;
	private String jobWaypointCode;
	private String jobWaypointName;
	private String deliveryType;
	private Integer radius;
	private Double shiptoLat;
	private Double shiptoLon;
	private String waybillNumber;
	
	private String PlanIncomingDate;
	private String PlanOutgoingDate;
}
