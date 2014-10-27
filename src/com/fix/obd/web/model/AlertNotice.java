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
@Table(name="alertnotice")
public class AlertNotice {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="nid",nullable=false)
	private int nid;
	@OneToOne(targetEntity=YY_User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="uid", referencedColumnName="id")
	private YY_User user;
	@Column(name="date",columnDefinition="varchar(19)")
	private String date;
	@Column(name="title",columnDefinition="varchar(50)")
	private String title;
	@Column(name="message",columnDefinition="varchar(500)")
	private String message;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public YY_User getUser() {
		return user;
	}
	public void setUser(YY_User user) {
		this.user = user;
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
}
