package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="obd_logs")
public class OBDLogs {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="lid",nullable=false)
	private int lid;
	@Column(name="tid",nullable=false,columnDefinition="varchar(20)")
	private String terminalId;
	@Column(name="date",nullable=false,columnDefinition="varchar(19)")
	private String date;
	@Column(name="info",nullable=false,columnDefinition="varchar(250)")
	private String info;
	@Column(name="message",nullable=false,columnDefinition="varchar(500)")
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
}
