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
@Table(name="tb_insurance_cover_product")
public class InsuranceCoverProduct {
	public InsuranceCoverProduct(){}
	
	@Id	
	@Column(name = "POLICY_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int InsuranceCover_Id;
	
	@Column(name = "INSURANCE_LIMIT", nullable = false)	
	private String InsuranceCover_Limit;
	
	@Column(name = "POLICY_EXPIRY_DATE", nullable = true)
	private String InsuranceCover_date;

	@Column(name = "INSURANCE_COVER_PRODUCT", nullable = true)
	private String InsuranceCover_Cover_Product;	
	
	@Column(name = "INSURANCE_RESPONSE_BY", nullable = true)
	private int InsuranceCover_Response_By;	
	
	@Column(name = "POLICY_NO", nullable = false)
	private String InsuranceCover_No;
	
	@Column(name = "INSURANCE_STATUS", nullable = false)
	private int InsuranceCover_Status;

}
