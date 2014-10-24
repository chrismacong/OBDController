package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_password")
public class YY_UserPassword {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="email",nullable=false,columnDefinition="varchar(50)")
	private String email;
	@Column(name="passwordregetarray",nullable=false,columnDefinition="varchar(24)")
	private String passwordregetarray;        //找回密码用的一次性随机24位字符串
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordregetarray() {
		return passwordregetarray;
	}
	public void setPasswordregetarray(String passwordregetarray) {
		this.passwordregetarray = passwordregetarray;
	}
}
