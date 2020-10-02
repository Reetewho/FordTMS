package co.th.ford.tms.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tb_gps_type")
public class GpsType {
	public GpsType(){}
	
	@Id	
	@Column(name = "Id", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int GPSId;	
		
	@Column(name = "GPSTYPE", nullable = true)
	private String GPSTYPE;	
	
	@Column(name = "GPS_STATUS", nullable = true)
	private String GPS_STATUS;	
		
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GPS_CREATE_DATE;
	
	@Column(name = "CREATE_BY", nullable = false)
	private String GPS_CREATE_BY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GPS_UPDATE_DATE;
	
	@Column(name = "UPDATE_BY", nullable = false)
	private String GPS_UPDATE_BY;
		
}
