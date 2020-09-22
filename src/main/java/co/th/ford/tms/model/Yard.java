package co.th.ford.tms.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tb_masteryard")
public class Yard {
public Yard(){}
	
	@Id	
	@Column(name = "Yard_Id", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int YardId;	
	
	@Column(name = "Yard_Name", nullable = true)
	private String YardName;		
	
	@Column(name = "Yard_Code", nullable = true)
	private String YardCode;	
	
	@Column(name = "Yard_Address", nullable = true)	
	private String YardAddress;
		
	@NotNull	
	@Column(name = "Yard_Latitude", nullable = false)
	private double YardLatitude;
	
	@NotNull
	@Column(name = "Yard_Longitude", nullable = false)
	private double YardLongitude;
		
	@Column(name = "Yard_Radius", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int YardRadius;	
	
	@Column(name = "Yard_Status", nullable = true)	
	private String YardStatus;
	 
}
