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
  import org.joda.time.LocalDateTime;
  import org.springframework.format.annotation.DateTimeFormat;
  
  import lombok.AllArgsConstructor; 
  import lombok.Data;
  
  @Data
  @AllArgsConstructor  
  @Entity  
  @Table(name="tb_gsdb_code") 
  public class Gsdb {
  
  public Gsdb(){}
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int id;
	
	@Id
	//@Size(min=2, max=30)
	@Column(name = "GSDB_CODE", nullable = true)
	private String GSDBCODE;	

	@Column(name = "GSDB_NAME", nullable = true)
	private String GSDBNAME;
		
	@Column(name = "Latitude", nullable = true)
	private Double GSDBLATITUDE;
		
	@Column(name = "Longitude", nullable = true)
	private Double GSDBLONGITUDE;
	
	@Column(name = "RADIUS", nullable = true)
	private Double GSDBRADIUS;
	
	@Column(name = "DELIVERY_TYPE", nullable = true)
	private Integer GSDBDELIVERYTYPE;
	
	@Column(name = "STATUS", nullable = true)
	private String GSDBSTARUS;

	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GSDBCREATERDATE;
	
	@Column(name = "CREATE_BY", nullable = true)
	private String GSDBCREATEBY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GSDBUPDATEDATE;
	
	@Column(name = "UPDATE_BY", nullable = true)
	private String GSDBUPDATEBY;

	@Column(name = "GSDB_PROVINCE_ID", nullable = false)
	private Integer provinceId;
	
	@Column(name = "GSDB_AREA_ZONE", nullable = false)
	private String areaZone;
	

	@Override
	public int hashCode() {		
		return GSDBCODE.hashCode();
	}	
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Gsdb))
			return false;
		Gsdb other = (Gsdb) obj;
		if (!GSDBCODE.equals(other.GSDBCODE))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "GSDB [GSDBCODE=" + GSDBCODE + ", GSDBNAME=" + GSDBNAME + ",GSDBLATITUDE=" + GSDBLATITUDE + ", GSDBLONGITUDE=" + GSDBLONGITUDE + ", GSDBRADIUS=" + GSDBRADIUS + ",GSDBDELIVERYTYPE=" + GSDBDELIVERYTYPE + ", "
				+ "GSDBSTARUS="	+ GSDBSTARUS + ", GSDBCREATERDATE=" + GSDBCREATERDATE + ", GSDBCREATEBY=" + GSDBCREATEBY
				+", GSDBUPDATEDATE=" + GSDBUPDATEDATE  + ", GSDBUPDATEBY=" + GSDBUPDATEBY + "]";
	}

  }
 