package com.masai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int admin_id;
	@Column(name = "username",length = 50,nullable = false)
	private String userName;
	@Column(name = "password",length = 50,nullable = false)
	private String password;
	@Column(name = "email",length = 50,nullable = false)
	private String email;
	@Column(name = "phone_number",length = 20,nullable = false)
	private String phone_number;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String userName, String password, String email, String phone_number) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
}
