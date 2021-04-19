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
	@Table(name="tb_stake_holder_master")
	public class StakeHolderMaster {

		public StakeHolderMaster(){}
	
	@Id	
	@Column(name = "STAKE_HOLDER_UN_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int STAKE_HOLDER_UN_ID;

	
	@Size(min=2, max=30)
	@Column(name = "STAKE_HOLDER_ID", nullable = true)
	private String STAKE_HOLDER_ID;
		
	@Column(name = "STAKE_HOLDER_NAME", nullable = true)
	private String STAKE_HOLDER_NAME;
	
	@Column(name = "STAKE_HOLDER_ADDRESS", nullable = true)
	private String STAKE_HOLDER_ADDRESS;	
	
	@Column(name = "STAKE_HOLDER_SUBDISTRICT", nullable = true)
	private String STAKE_HOLDER_SUBDISTRICT;	
	
	@Column(name = "STAKE_HOLDER_DISTRICT", nullable = true)
	private String STAKE_HOLDER_DISTRICT;	
	
	@Column(name = "STAKE_HOLDER_PROVINCE", nullable = true)
	private String STAKE_HOLDER_PROVINCE;	
	
	@Column(name = "STAKE_HOLDER_POST_CODE", nullable = true)
	private String STAKE_HOLDER_POST_CODE;	
	
	@Column(name = "STAKE_HOLDER_TEL_NO", nullable = true)
	private String STAKE_HOLDER_TEL_NO;	
	
	@Column(name = "STAKE_HOLDER_FAX_NO", nullable = true)
	private String STAKE_HOLDER_FAX_NO;	
	
	@Column(name = "STAKE_HOLDER_MOBILE_NO", nullable = true)
	private String STAKE_HOLDER_MOBILE_NO;	
	
	@Column(name = "STAKE_HOLDER_EMAIL", nullable = true)
	private String STAKE_HOLDER_EMAIL;	
	
	@Column(name = "STAKE_HOLDER_CONTACT_NAME", nullable = true)
	private String STAKE_HOLDER_CONTACT_NAME;	
	
	@Column(name = "STAKE_HOLDER_TAX_NO", nullable = true)
	private String STAKE_HOLDER_TAX_NO;	
	
	@Column(name = "STAKE_HOLDER_SALE_TYPE", nullable = false, columnDefinition="INT NOT NULL DEFAULT 0")
	private int STAKE_HOLDER_SALE_TYPE;	
	
	@Column(name = "STAKE_HOLDER_PAYMENT_CONDITION", nullable = true)
	private String STAKE_HOLDER_PAYMENT_CONDITION;	
	
	@Column(name = "STAKE_HOLDER_PAYMENT_METHOD",nullable = false, columnDefinition="INT NOT NULL DEFAULT 0")
	private int STAKE_HOLDER_PAYMENT_METHOD;	
	
	@Column(name = "STAKE_HOLDER_REF_BANK", nullable = true)
	private String STAKE_HOLDER_REF_BANK;	
	
	@Column(name = "STAKE_HOLDER_REF_BANK_BRANCH", nullable = true)
	private String STAKE_HOLDER_REF_BANK_BRANCH;	
	
	@Column(name = "STAKE_HOLDER_REF_ACCOUNT_NO", nullable = true)
	private String STAKE_HOLDER_REF_ACCOUNT_NO;	
	
	@Column(name = "STAKE_HOLDER_REF_ACCOUNT_NAME", nullable = true)
	private String STAKE_HOLDER_REF_ACCOUNT_NAME;	
	
	@Column(name = "STAKE_HOLDER_PROJECT", nullable = true)
	private int STAKE_HOLDER_PROJECT;	
	
	@Column(name = "STAKE_HOLDER_CREDIT_TERM", nullable = false, columnDefinition="INT NOT NULL DEFAULT 0")
	private int STAKE_HOLDER_CREDIT_TERM ;	
	
	@Column(name = "STAKE_HOLDER_TYPE", nullable = false, columnDefinition="INT NOT NULL DEFAULT 0")
	private int STAKE_HOLDER_TYPE;	

	@Column(name = "STAKE_HOLDER_STATUS", nullable = false)
	private int STAKE_HOLDER_STATUS;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "STAKE_HOLDER_CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime STAKE_HOLDER_CREATE_DATE;
	
	@Column(name = "STAKE_HOLDER_CREATE_BY", nullable = false)
	private String STAKE_HOLDER_CREATE_BY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "STAKE_HOLDER_UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime STAKE_HOLDER_UPDATE_DATE;
	
	@Column(name = "STAKE_HOLDER_UPDATE_BY", nullable = false)
	private String STAKE_HOLDER_UPDATE_BY;
		

	@Override
	public int hashCode() {		
		final int prime = 31;
		int result = 1;
		result = prime * result + STAKE_HOLDER_UN_ID;
		return result;
	}	
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StakeHolderMaster))
			return false;
		StakeHolderMaster other = (StakeHolderMaster) obj;
		if (STAKE_HOLDER_UN_ID != other.STAKE_HOLDER_UN_ID)
			return false;
			
		
		return true;
	}
}
