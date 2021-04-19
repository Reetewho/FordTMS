package co.th.ford.tms.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tb_payment_method")
public class PaymentMethod {
	public PaymentMethod(){}
	
	@Id	
	@Column(name = "PAYMENT_METHOD_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentmethodid;	
	
	
	@Column(name = "PAYMENT_METHOD_NAME", nullable = true)
	private String paymentmethodname;		
	
	@Column(name = "PAYMENT_METHOD_STATUS", nullable = true)	
	private String paymentmethodstatus;
		
	
	 
	
		
}

