package com.fix.obd.util;

public class ResponseStrMaker {
	private final String startNode = "aa";
	private final String endNode = "aa";
	private String Id;
	private String bufferId;
	private String length;
	private String checkNode;
	private String messageBody;
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getBufferId() {
		return bufferId;
	}
	public void setBufferId(String bufferId) {
		this.bufferId = bufferId;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCheckNode() {
		return checkNode;
	}
	public void setCheckNode(String checkNode) {
		this.checkNode = checkNode;
	}
	public String buildResponse(){
		return startNode + Id + bufferId + length + messageBody + checkNode + endNode;
	}
}
