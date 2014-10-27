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
@Table(name="membernotice")
public class MemberNotice {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="nid",nullable=false)
	private int nid;
	@OneToOne(targetEntity=Business.class, fetch = FetchType.EAGER)
    @JoinColumn(name="bid", referencedColumnName="bid")
	private Business business;
	@Column(name="date_upload",columnDefinition="varchar(19)")
	private String date_upload;
	@Column(name="date_publish",columnDefinition="varchar(19)")
	private String date_publish;
	@Column(name="title",columnDefinition="varchar(50)")
	private String title;
	@Column(name="message",columnDefinition="varchar(500)")
	private String message;
	@Column(name="photopath",columnDefinition="varchar(100)")
	private String photopath;
	@Column(name="status",columnDefinition="varchar(20)")
	private String status;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getDate_upload() {
		return date_upload;
	}
	public void setDate_upload(String date_upload) {
		this.date_upload = date_upload;
	}
	public String getDate_publish() {
		return date_publish;
	}
	public void setDate_publish(String date_publish) {
		this.date_publish = date_publish;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
