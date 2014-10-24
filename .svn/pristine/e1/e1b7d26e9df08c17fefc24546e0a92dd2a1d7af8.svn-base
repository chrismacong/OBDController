package com.fix.obd.protocol.impl;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.ResponseStrMaker;

public class SendTime  extends ODBProtocolParser implements ODBProtocol{
	private static final Logger logger = Logger.getLogger(SendTime.class);
	private String protocolClientId;
	private String protocolBufferId;
	public SendTime(String protocolClientId, String protocolBufferId){
		this.protocolClientId = protocolClientId;
		this.protocolBufferId = protocolBufferId;
		MessageUtil.printAndToDivContent("向终端" + protocolClientId + "下发信息",true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		ResponseStrMaker response = new ResponseStrMaker();
		response.setId(protocolClientId);
		response.setBufferId(protocolBufferId);
		String message = new String();
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname = stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String findId = p.getIdByProtocol(classname);
		message += findId;
		Calendar calendar = Calendar.getInstance();
		String year = (calendar.get(Calendar.YEAR) + "").substring(2); 
		String month = calendar.get(Calendar.MONTH) + 1 + ""; 
		month = month.length()==1 ? "0" + month : month;
		String date = calendar.get(Calendar.DATE) + ""; 
		date = date.length()==1 ? "0" + date : date;
		String hour = calendar.get(Calendar.HOUR_OF_DAY) + ""; 
		hour = hour.length()==1 ? "0" + hour : hour;
		String minute = calendar.get(Calendar.MINUTE) + "";
		minute = minute.length()==1 ? "0" + minute : minute;
		String second = calendar.get(Calendar.SECOND) + ""; 
		second = second.length()==1 ? "0" + second : second;
		message = message + year + month + date + hour + minute + second;
		response.setMessageBody(message);
		String messageLength = "0000" + Integer.toHexString(message.length()/2);
		messageLength = messageLength.substring(messageLength.length()-4);
		response.setLength(messageLength);
		response.setCheckNode(MessageUtil.buildCheckNode(response));
		MessageUtil.printAndToDivContent("发送:" + response.buildResponse(), true);
		byte[] replyStr = MessageUtil.buildOutputStream(response);
		return replyStr;
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}
}
