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
@Table(name="tb_truck_type")
public class TruckType {
	public TruckType(){}
	
	
	@Id	
	@Column(name = "id", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int TRUCKTYPE_id;	
	
	
	@Column(name = "TYPE", nullable = true)
	private String TRUCKTYPE_TYPE;	
	

	
	@Column(name = "STATUS", nullable = true)
	private String TRUCKTYPE_STATUS;	
	
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime TRUCKTYPE_CREATE_DATE;
	
	@Column(name = "CREATE_BY", nullable = false)
	private String TRUCKTYPE_CREATE_BY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime TRUCKTYPE_UPDATE_DATE;
	
	@Column(name = "UPDATE_BY", nullable = false)
	private String TRUCKTYPE_UPDATE_BY;
		
}
