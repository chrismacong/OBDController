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
@Table(name="rescueprocesshistory")
public class RescueProcessHistory {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="hid",nullable=false)
	private int hid;
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public RescueProcess getRescueProcess() {
		return rescueProcess;
	}
	public void setRescueProcess(RescueProcess rescueProcess) {
		this.rescueProcess = rescueProcess;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@ManyToOne(targetEntity=RescueProcess.class, fetch = FetchType.EAGER)
    @JoinColumn(name="rid", referencedColumnName="rid")
    private RescueProcess rescueProcess;
	@Column(name="status",columnDefinition="varchar(10)")
	//format:broadcast, grabbed, deal, completed, failed
	private String status;
	@Column(name="date", nullable=false, columnDefinition="varchar(19)")
	//format:xxxx-xx-xx xx:xx:xx
	private String date;
}
