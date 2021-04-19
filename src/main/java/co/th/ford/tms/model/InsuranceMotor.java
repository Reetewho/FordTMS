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
@Table(name="tb_insurance_motor")
public class InsuranceMotor {
	public InsuranceMotor(){}
	
	@Id	
	@Column(name = "ACT_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int InsuranceMotor_id;
	
	@Column(name = "ACT_NO", nullable = false)	
	private String InsuranceMotor_Act_No;

//	@NotNull
//	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "ACT_EXPIRE_DATE", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime InsuranceMotor_Act_Expire_date;
	
	@Column(name = "ACT_EXPIRE_DATE", nullable = true)
	private String InsuranceMotor_Act_Expire_date;

	@Column(name = "ACT_INSURANCE_MOTOR", nullable = true)
	private String InsuranceMotor_Motor;	
	
	@Column(name = "ACT_RESPONSE_BY", nullable = true)
	private int InsuranceMotor_Act_Response_By;	
	
	@Column(name = "ACT_STATUS", nullable = false)
	private int InsuranceMotor_Act_Status;
	
	@Column(name = "ACT_INSURANCE_LIMIT", nullable = false)
	private String InsuranceMotor_Act_Limit;

}
