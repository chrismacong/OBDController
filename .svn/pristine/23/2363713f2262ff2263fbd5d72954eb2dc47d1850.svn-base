package com.fix.obd.protocol;

public class ODBProtocolParser {
	protected String messageStr;
	protected String strForDiv;
	public ODBProtocolParser() {
	}
	public ODBProtocolParser(String wholeStr) {
		this.messageStr = wholeStr;
		this.strForDiv = "÷’∂ÀID£∫" + this.getId() + "<br/>" + "÷∏¡Ó£∫" + this.getRealMessage() + "<br/>";
	}
	public String getId(){
		return messageStr.substring(0,20);
	}
	public String getBufferId(){
		return messageStr.substring(20,22);
	}
	public String getLength(){
		return messageStr.substring(22,26);
	}
	public String getCommand(){
		return messageStr.substring(26,30);
	}
	public String getRealMessage(){
		return messageStr.substring(30,messageStr.length()-2);
	}
	public String getCheckNode(){
		return messageStr.substring(messageStr.length()-2);
	}
}
