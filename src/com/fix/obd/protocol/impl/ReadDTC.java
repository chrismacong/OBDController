package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.ResponseStrMaker;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class ReadDTC extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(ReadDTC.class);
	private String clientId;
	private String bufferId;

	public ReadDTC(String clientId, String bufferId){
		this.clientId = clientId;
		this.bufferId = bufferId;
		MessageUtil.printAndToDivContent("向终端" + clientId + "发送故障码信息请求",true);
	}
	
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		ResponseStrMaker response = new ResponseStrMaker();
		response.setId(clientId);
		response.setBufferId(bufferId);
		String message = new String();
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname = stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String findId = p.getIdByProtocol(classname);
		message += findId;
		response.setMessageBody(message);
		String messageLength = "0000" + (Integer.toHexString(message.length()/2));
		messageLength = messageLength.substring(messageLength.length()-4);
		response.setLength(messageLength);
		response.setCheckNode(MessageUtil.buildCheckNode(response));
		System.out.println(response.buildResponse());
		byte[] replyStr = MessageUtil.buildOutputStream(response);
		return replyStr;
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}

}
