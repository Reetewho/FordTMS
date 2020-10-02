package co.th.ford.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Entity
@Table(name="tb_trucknumber")
public class Truck {

	public Truck(){}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int id;
	
	@Id
	@Size(min=2, max=20)
	@Column(name = "TRUCK_NUMBER", nullable = false)
	private String TRUCK_NUMBER;	

	@Column(name = "TRUCK_TYPE", nullable = false)
	private int TRUCK_TYPE;
	
	
	@Column(name = "GPS_TRUCK", nullable = false)
	private int GPS_TRUCK;
		
	@Column(name = "STATUS", nullable = false)
	private String STATUS;

	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime CREATE_DATE;
	
	@Column(name = "CREATE_BY", nullable = false)
	private String CREATE_BY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime UPDATE_DATE;
	
	@Column(name = "UPDATE_BY", nullable = false)
	private String UPDATE_BY;
	
	@Override
	public int hashCode() {		
		return TRUCK_NUMBER.hashCode();
	}	
	 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Truck))
			return false;
		Truck other = (Truck) obj;
		if (!TRUCK_NUMBER.equals(other.TRUCK_NUMBER))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "User [TRUCK_NUMBER=" + TRUCK_NUMBER + ", GPS_TRUCK=" + GPS_TRUCK + ", STATUS="
				+ STATUS + ", CREATE_DATE=" + CREATE_DATE + ", TRUCK_TYPE=" + TRUCK_TYPE
				+", CREATE_BY=" + CREATE_BY  + ", UPDATE_DATE=" + UPDATE_DATE + ",UPDATE_BY=" + UPDATE_BY  + "]";
	}

		
}
