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
@Table(name="tb_department")
public class Department {
	public Department(){}
	
	@Id	
	@Column(name = "departmentId", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;	
	
	
	@Column(name = "department_n", nullable = true)
	private String department_n;	
	

	
	@Column(name = "department_c", nullable = true)
	private String department_c;	
	
	
	@Column(name = "status", nullable = true)	
	private String status;
	
}
