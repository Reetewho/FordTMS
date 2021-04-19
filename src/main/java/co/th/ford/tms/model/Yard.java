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
	private int yard_id;	
	
	@Column(name = "Yard_Name", nullable = true)
	private String yard_name;		
	
	@Column(name = "Yard_Code", nullable = true)
	private String yard_code;	
	
	@Column(name = "Yard_Address", nullable = true)	
	private String yard_address;
		
	@NotNull	
	@Column(name = "Yard_Latitude", nullable = false)
	private double yard_latitude;
	
	@NotNull
	@Column(name = "Yard_Longitude", nullable = false)
	private double yard_longitude;
		
	@Column(name = "Yard_Radius", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int yard_radius;	
	
	@Column(name = "Yard_Status", nullable = true)	
	private String yard_status;
	 
}
