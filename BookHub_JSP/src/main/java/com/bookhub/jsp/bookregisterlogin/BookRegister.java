package com.bookhub.jsp.bookregisterlogin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "register_table")
public class BookRegister {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "register_id")
	private int registerId;  

	@Column(name = "username") 
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "gmail") 
	private String gmail;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	public BookRegister(int registerId, String username, String password, String gmail, String phoneNumber) {
		super();
		this.registerId = registerId;
		this.username = username;
		this.password = password;
		this.gmail = gmail;
		this.phoneNumber = phoneNumber;
	}

	public BookRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRegisterId() {
		return registerId;
	}

	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	} 
	
	
}
