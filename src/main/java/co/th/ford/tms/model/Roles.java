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
@Table(name="tb_roles")
public class Roles {
	public Roles(){}
	
	@Id	
	@Column(name = "roleId", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;	
	
	
	@Column(name = "role_n", nullable = true)
	private String role_n;	
	

	
	@Column(name = "role_c", nullable = true)
	private String role_c;	
	
	
	@Column(name = "status", nullable = true)	
	private String status;
		
	
	 
	
		
}
