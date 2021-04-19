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
@Table(name="tb_credit_term")
public class CreditTerm {
	public CreditTerm(){}
	
	@Id	
	@Column(name = "CREDIT_TERM_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int credittermid;	
	
	
	@Column(name = "CREDIT_TERM_NAME", nullable = true)
	private String credittermname;		
	
	@Column(name = "CREDIT_TERM_STATUS", nullable = true)	
	private String credittermstatus;
		
	
	 
	
		
}

