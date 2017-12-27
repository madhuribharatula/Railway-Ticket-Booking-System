package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User implements Serializable{

	

	@NotNull(message = "User Name must not be null")
	@Column(name = "name")
	private String name;

	
    @Id
	@NotNull(message = "emailid must not be null")
	@Column(name = "Email")
	private String emailid;

	@NotNull(message = "password must not be null")
	@Column(name = "Password")
	private String password;


	public User() {
		super();
	}

	public User(String name, String emailid, String password) {
		super();
		this.name = name;
	
		this.emailid = emailid;
		this.password = password;
		
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
