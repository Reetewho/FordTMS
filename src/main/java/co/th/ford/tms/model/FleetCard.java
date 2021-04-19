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
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Entity
@Table(name="tb_fleet_card")
public class FleetCard {

	public FleetCard(){}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FLEETCARD_ID", nullable = false)
	private int FLEETCARD_ID;

	@Column(name = "FLEETCARD_BRAND", nullable = false)
	private String FLEETCARD_BRAND;	

	@Column(name = "FLEETCARD_NO", nullable = false)
	private String FLEETCARD_NO;
	
	@Column(name = "FLEETCARD_CREDIT_LIMIT", nullable = false)
	private String FLEETCARD_CREDIT_LIMIT;
	
	@Column(name = "FLEETCARD_RECIEVE_DATE", nullable = false)
	private String FLEETCARD_RECIEVE_DATE;
	
	@Column(name = "FLEET_EXPIRY_DATE", nullable = false)
	private String FLEET_EXPIRY_DATE;

//	@NotNull
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "FLEETCARD_RECIEVE_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime FLEETCARD_RECIEVE_DATE;
	
//	@NotNull
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "FLEET_EXPIRY_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime FLEET_EXPIRY_DATE;
	
	@Column(name = "FLEET_CARD_STATUS", nullable = false)
	private String FLEET_CARD_STATUS;
	
	@Override
	public int hashCode() {		
		return FLEETCARD_BRAND.hashCode();
	}	
	 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FleetCard))
			return false;
		FleetCard other = (FleetCard) obj;
		if (!FLEETCARD_BRAND.equals(other.FLEETCARD_BRAND))
			return false;
		
		return true;
	}
	

		
}
