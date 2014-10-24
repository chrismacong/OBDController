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
@Table(name="rescueprocess")
public class RescueProcess {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="rid",nullable=false)
	private int rid;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public SOSMessage getSosmessage() {
		return sosmessage;
	}
	public void setSosmessage(SOSMessage sosmessage) {
		this.sosmessage = sosmessage;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(targetEntity=SOSMessage.class, fetch = FetchType.EAGER)
    @JoinColumn(name="mid", referencedColumnName="mid")
    private SOSMessage sosmessage;
	@ManyToOne(targetEntity=Business.class, fetch = FetchType.EAGER)
	@JoinColumn(name="bid", referencedColumnName="bid")
	private Business business;
	@Column(name="status",columnDefinition="varchar(10)")
	//format:broadcast, grabbed, deal, completed, failed
	private String status;
}
