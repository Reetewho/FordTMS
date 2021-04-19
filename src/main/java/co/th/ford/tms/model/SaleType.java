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
@Table(name="tb_sale_type")
public class SaleType {
	public SaleType(){}
	
	@Id	
	@Column(name = "SALE_TYPE_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int saletypeid;	
	
	
	@Column(name = "SALE_TYPE_NAME", nullable = true)
	private String saletypename;		
	
	@Column(name = "SALE_TYPE_STATUS", nullable = true)	
	private String saletypestatus;
		
	
	 
	
		
}

