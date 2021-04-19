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
@Table(name="tb_user")
public class User {	
	
	public User(){}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int id;
	
	@Id
	@Size(min=8, max=20)
	@Column(name = "username", nullable = true)
	private String username;	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int id;
	
	@Size(min=8, max=30)
	@Column(name = "password", nullable = true)
	private String password;
		
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "lastname", nullable = true)
	private String lastname;

	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	@Column(name = "joining_date", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime joiningDate;

	
	@Column(name = "role", nullable = true)
	private Integer role;	
	
	@Column(name = "department", nullable = true)
	private Integer department;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "contactnumber", nullable = true)
	private String contactnumber;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	//@Column(name = "last_login_date", nullable = true)
	@Column(name = "lastLogin", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime logoutDate;
	
	@Column(name = "status", nullable = true)
	private int status;
	
	
	
	@Override
	public int hashCode() {		
		return username.hashCode();
	}	
	 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (!username.equals(other.username))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", joiningDate="
				+ joiningDate + ", role=" + role + ", department=" + department
				+", status=" + status  + "]";
	}

		
	
}
