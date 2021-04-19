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
@Table(name="tb_province")
public class Province {

	public Province(){}
	
	@Id	
	@Column(name = "id", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Column(name = "code", nullable = true)
	private String code;		
	
	@Column(name = "name_th", nullable = true)	
	private String nameTh;
	
	@Column(name = "name_en", nullable = true)	
	private String nameEn;
	
	@Column(name = "geography_id", nullable = true)	
	private Integer geographyId;
		
	
}
