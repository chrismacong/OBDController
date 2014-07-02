package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="business")
public class BusinessInfo {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@OneToOne(targetEntity=YY_User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="uid", referencedColumnName="id")
	private YY_User user;
	@Column(name="address",columnDefinition="varchar(200)")
	private String address;
	@Column(name="latitude",columnDefinition="varchar(10)")
	private String latitude;
	@Column(name="longitute",columnDefinition="varchar(10)")
	private String longitute;
	@Column(name="tel",columnDefinition="varchar(20)")
	private String tel;
	@Column(name="baktel",columnDefinition="varchar(20)")
	private String baktel;
	@Column(name="introduction",columnDefinition="varchar(400)")
	private String introduction;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBaktel() {
		return baktel;
	}
	public void setBaktel(String baktel) {
		this.baktel = baktel;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public YY_User getUser() {
		return user;
	}
	public void setUser(YY_User user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitute() {
		return longitute;
	}
	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}
}
