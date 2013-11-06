package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="obd_terminal_info")
public class OBDTerminalInfo {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="oid",nullable=false)
	private int oid;
	@Column(name="tid",nullable=false,columnDefinition="varchar(20)")
	private String terminalId;
	@Column(name="tip",nullable=false,columnDefinition="varchar(21)")
	private String terminalIp;
	@Column(name="product",columnDefinition="varchar(50)")
	private String product;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalIp() {
		return terminalIp;
	}
	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}
}
