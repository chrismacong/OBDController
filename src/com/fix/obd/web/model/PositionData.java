package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="position_data")
public class PositionData {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="tid",nullable=false,columnDefinition="varchar(20)")
	private String terminalId;
	@Column(name="date",nullable=false,columnDefinition="varchar(19)")
	private String date;
	@Column(name="info",columnDefinition="varchar(500)")
	private String info;
	@Column(name="alert",columnDefinition="varchar(500)")
	private String alert;
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
