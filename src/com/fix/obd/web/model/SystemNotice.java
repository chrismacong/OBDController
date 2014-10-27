package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SystemNotice {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="nid",nullable=false)
	private int nid;
	@Column(name="date",columnDefinition="varchar(19)")
	private String date;
	@Column(name="title",columnDefinition="varchar(50)")
	private String title;
	@Column(name="message",columnDefinition="varchar(500)")
	private String message;
	@Column(name="photopath",columnDefinition="varchar(100)")
	private String photopath;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
}
