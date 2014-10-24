package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class YY_User {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="email",nullable=false,columnDefinition="varchar(50)")
	private String email;
	@Column(name="password",nullable=false,columnDefinition="varchar(100)")
	private String password;
	@Column(name="realname",nullable=false,columnDefinition="varchar(25)")
	private String realname;
	@Column(name="idnumber",nullable=false,columnDefinition="varchar(25)")
	private String idnumber;
	@Column(name="nickname",nullable=false,columnDefinition="varchar(25)")
	private String nickname;
	@Column(name="tel",nullable=false,columnDefinition="varchar(25)")
	private String tel;
	@Column(name="cartype",nullable=false,columnDefinition="varchar(25)")
	private String cartype;
	@Column(name="obdnumber",nullable=false,columnDefinition="varchar(25)")
	private String obdnumber;
	@Column(name="carnumber",nullable=false,columnDefinition="varchar(25)")
	private String carnumber;
	@Column(name="role",nullable=false,columnDefinition="varchar(50)")
	private String role;

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
	public String getObdnumber() {
		return obdnumber;
	}
	public void setObdnumber(String obdnumber) {
		this.obdnumber = obdnumber;
	}
	
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
