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
  @Table(name="tb_gsdb_code") public class Gsdb {
  
  public Gsdb(){}
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int id;
	
	@Id
	@Size(min=2, max=20)
	@Column(name = "GSDB_CODE", nullable = false)
	private String GSDBCODE;	

	@Column(name = "GSDB_NAME", nullable = false)
	private String GSDBNAME;
		
	@Column(name = "Latitude", nullable = false)
	private double GSDBLATITUDE;
		
	@Column(name = "Longitude", nullable = false)
	private double GSDBLONGITUDE;
	
	@Column(name = "RADIUS", nullable = false)
	private double GSDBRADIUS;
	
	@Column(name = "DELIVERY_TYPE", nullable = false)
	private int GSDBDELIVERYTYPE;
	
	@Column(name = "STATUS", nullable = false)
	private String GSDBSTARUS;

	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CREATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GSDBCREATERDATE;
	
	@Column(name = "CREATE_BY", nullable = false)
	private String GSDBCREATEBY;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "UPDATE_DATE", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime GSDBUPDATEDATE;
	
	@Column(name = "UPDATE_BY", nullable = false)
	private String GSDBUPDATEBY;
	
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
 