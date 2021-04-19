package co.th.ford.tms.model;

import lombok.Data;

@Data
public class ReportGSDB {

	private String gsdbCode;
	private String gsdbName;
	private String gsdbLatitude;
	private String gsdbLongtitude;	
	private String gsdbRadius;	
	private String gsdbUpdateDate;
	private String gsdbUpdateBy;
	private String gsdbStatus;
	
}
