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
@Table(name="tb_project_name")
public class ProjectName {
	
	public ProjectName(){}
	
	@Id	
	@Column(name = "PROJECT_NAME_ID", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectnameid;	
	
	
	@Column(name = "PROJECT_NAME_NAME", nullable = true)
	private String projectnamename;		
	
	@Column(name = "PROJECT_NAME_STATUS", nullable = true)	
	private String projectnamestatus;
		
	
	 
	
		
}

