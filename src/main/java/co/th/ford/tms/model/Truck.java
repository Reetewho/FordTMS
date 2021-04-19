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
@Table(name="tb_truck_profile")
public class Truck {

	public Truck(){}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int id;
	
	@Id
	@Size(min=2, max=20)
	@Column(name = "TRUCK_NUMBER", nullable = false)
	private String TRUCK_NUMBER;	

	@Column(name = "PROVINCE", nullable = false)
	private String PROVINCE;
	
	@Column(name = "YARDS", nullable = false)
	private int YARD;
	
	@Column(name = "COMPANY", nullable = false)
	private String COMPANY;
	
	@Column(name = "PLATE_TYPE", nullable = false)
	private String PLATE_TYPE;
	
	@Column(name = "TRUCK_TYPE", nullable = false)
	private int TRUCK_TYPE;
	
	@Column(name = "BRAND", nullable = false)
	private String BRAND;
	
	@Column(name = "FUEL_TYPE", nullable = false)
	private String FUEL_TYPE;
	
	@Column(name = "CONTAINER_BORDER_SIZE", nullable = false)
	private String CONTAINER_BORDER_SIZE;
	
	@Column(name = "CONTAINER_SIZE", nullable = false)
	private String CONTAINER_SIZE;
	
	@Column(name = "AVAERAGE_FUEL", nullable = false)
	private String AVAERAGE_FUEL;
	
	@Column(name = "PROJECT", nullable = false)
	private int PROJECT;
	
	@Column(name = "LATEST_MILAGE", nullable = false)
	private String LATEST_MILAGE;
	
	@Column(name = "TRUCK_STATUS", nullable = false)
	private int STATUS;
	
	
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "FIRST_REGIST_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime FIRST_REGIST_DATE;
	
	@Column(name = "FIRST_REGIST_DATE", nullable = false)
	private String FIRST_REGIST_DATE;
	
	@Column(name = "TRUCK_AGE", nullable = false)
	private String TRUCK_AGE;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "EXPIRE_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime EXPIRE_DATE;
	
	@Column(name = "EXPIRE_DATE", nullable = false)
	private String EXPIRE_DATE;
	
	@Column(name = "MODEL", nullable = false)
	private String MODEL;
	
	@Column(name = "CHASSIS_NO", nullable = false)
	private String CHASSIS_NO;
	
	@Column(name = "ENGINE_NO", nullable = false)
	private String ENGINE_NO;
	
	@Column(name = "POSITION1", nullable = false)
	private String POSITION1;
	
	@Column(name = "POSITION2", nullable = false)
	private String POSITION2;
	
	@Column(name = "PUMP_NO", nullable = false)
	private String PUMP_NO;
	
	@Column(name = "HPS", nullable = false)
	private int HPS;
	
	@Column(name = "WEIGHT", nullable = false)
	private String WEIGHT;
	
	@Column(name = "WEIGHT_CARRY", nullable = false)
	private String WEIGHT_CARRY;
	
	@Column(name = "WEIGHT_TOTAL", nullable = false)
	private String WEIGHT_TOTAL;
	
	@Column(name = "ACT_NUM", nullable = false)
	private int ACT_NO;
	
	@Column(name = "POLICY_NUM", nullable = false)
	private int POLICY_NO;
	
	@Column(name = "FLEETCARD_NUM", nullable = false)
	private int FLEETCARD_NO;
	
	@Column(name = "GPS_TRUCK", nullable = false)
	private int GPS_TRUCK;
	
	@Column(name = "SUPPLIER_SUM", nullable = false)
	private int SUPPLIER_CODE;
	
	@Column(name = "TRUCK_AGE_YEAR", nullable = false)
	private String TRUCK_AGE_YEAR;
	
	@Column(name = "TRUCK_AGE_MONTH", nullable = false)
	private String TRUCK_AGE_MONTH;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "INSTALLATION_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime INSTALLATION_DATE;
	
	@Column(name = "INSTALLATION_DATE", nullable = false)
	private String INSTALLATION_DATE;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "RENEW_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime RENEW_DATE;
	
	@Column(name = "RENEW_DATE", nullable = false)
	private String RENEW_DYATE;

	@Column(name = "ACT_CAR_NUM", nullable = false)
	private String ACT_CAR_NUM;
	
	@Column(name = "ACT_CAR_EXPIRE", nullable = false)
	private String ACT_CAR_EXPIRE;
	
	@Column(name = "ACT_CAR_COMPANY", nullable = false)
	private int ACT_CAR_COMPANY;
	
	@Column(name = "SUBSIDAIRY", nullable = false)
	private String SUBSIDAIRY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime UPDATE_DATE;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime CREATE_DATE;
	
	@Column(name = "CREATE_BY", nullable = false)
	private String CREATE_BY;
	
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
	
//	@Override
//	public String toString() {
//		return "User [TRUCK_NUMBER=" + TRUCK_NUMBER + ", GPS_TRUCK=" + GPS_TRUCK + ", STATUS="
//				+ STATUS + ", CREATE_DATE=" + CREATE_DATE + ", TRUCK_TYPE=" + TRUCK_TYPE
//				+", CREATE_BY=" + CREATE_BY  + ", UPDATE_DATE=" + UPDATE_DATE + ",UPDATE_BY=" + UPDATE_BY  + ""
//						+ ",UPDATE_BY=" + UPDATE_BY  + ",UPDATE_BY=" + UPDATE_BY  + ",UPDATE_BY=" + UPDATE_BY  + ",UPDATE_BY=" + UPDATE_BY  + ""
//								+ ",PORVINCE=" + PORVINCE  + ",OWNER=" + OWNER  + ",AFFILIATE=" + AFFILIATE  + ",BRAND=" + BRAND  + ""
//								+ ",FUEL_TYPE=" + FUEL_TYPE  + ",PLATE_TYPE=" + PLATE_TYPE  + ",PROJECT_NAME=" + PROJECT_NAME  + ",LATEST_MILAGE=" + LATEST_MILAGE  + ""
//								+ ",TRUCK_STATUS=" + TRUCK_STATUS  + ",AVERAGE_FUEL=" + AVERAGE_FUEL  + "]";
//	}

		
}
