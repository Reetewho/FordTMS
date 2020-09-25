package co.th.ford.tms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tb_summarylist_service_control")

public class Summary {
	@Id	
	@Column(name = "Id", nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "LoadIDSummary")
	private String LoadIDSummary;
	
	@Column(name = "SummaryStatus")
	private String SummaryStatus;
		
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "CreateDate", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime CreateDate;
		
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "StopDate", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime StopDate;
	
	@Override
	public int hashCode() {		
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;

		return result;
	}	
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Summary))
			return false;
		Summary other = (Summary) obj;
		if (LoadIDSummary != other.LoadIDSummary)
			return false;
			
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Summary [Id=" + Id + ", LoadIDSummary=" + LoadIDSummary + ", CreateDate="
				+ CreateDate +  ", StopDate= " + StopDate				
				+ "]";
//				+ ", Status Flag =" + statusFlag + "  ]";
	}
	
	
}
