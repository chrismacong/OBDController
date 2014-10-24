package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sosmessage")
public class SOSMessage {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="mid",nullable=false)
	private int mid;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public YY_User getYy_user() {
		return yy_user;
	}
	public void setYy_user(YY_User yy_user) {
		this.yy_user = yy_user;
	}
	public String getObd_err() {
		return obd_err;
	}
	public void setObd_err(String obd_err) {
		this.obd_err = obd_err;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@ManyToOne(targetEntity=YY_User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="uid", referencedColumnName="id")
    private YY_User yy_user;
	@Column(name="obd_err",columnDefinition="varchar(200)")
	private String obd_err;
	@Column(name="obd_err_description",columnDefinition="varchar(1000)")
	private String obd_err_description;
	public String getObd_err_description() {
		return obd_err_description;
	}
	public void setObd_err_description(String obd_err_description) {
		this.obd_err_description = obd_err_description;
	}
	@Column(name="date", nullable=false, columnDefinition="varchar(19)")
	//format:xxxx-xx-xx xx:xx:xx
	private String date;
	@Column(name="longitude", nullable=false, columnDefinition="varchar(8)")
	//format:111.11111 => 11111111
	private String longitude;
	@Column(name="latitude", nullable=false, columnDefinition="varchar(8)")
	//format:33.111111 => 33111111
	private String latitude;
	@Column(name="type",nullable=false, columnDefinition="varchar(6)")
	//format:native,member
	private String type;
	@Column(name="info",columnDefinition="varchar(400)")
	private String info;
}
