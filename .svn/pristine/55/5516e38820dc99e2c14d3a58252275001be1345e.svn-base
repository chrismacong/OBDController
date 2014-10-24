package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="online_terminal")
public class OnLineTerminal {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="tid",nullable=false,columnDefinition="varchar(20)")
	private String terminalId;
	@Column(name="tbuffer",nullable=false,columnDefinition="varchar(2)")
	private String terminalBuffer;
	@Column(name="lastheartbeat",nullable=false,columnDefinition="varchar(19)")
	private String lastheartbeat;
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
	public String getTerminalBuffer() {
		return terminalBuffer;
	}
	public void setTerminalBuffer(String terminalBuffer) {
		this.terminalBuffer = terminalBuffer;
	}
	public String getLastheartbeat() {
		return lastheartbeat;
	}
	public void setLastheartbeat(String lastheartbeat) {
		this.lastheartbeat = lastheartbeat;
	}
	
}
