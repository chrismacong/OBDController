package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.ResponseStrMaker;

public class RequestTravelInfo extends ODBProtocolParser implements ODBProtocol {
	private static final  Logger logger = Logger.getLogger(RequestTravelInfo.class);
	private String protocolClientId;
	private String protocolBufferId;
	private String characterSentence;
	
	public RequestTravelInfo(String clientId, String bufferId, String characterSentence){
		this.protocolClientId = clientId;
		this.protocolBufferId = bufferId;
		this.characterSentence = characterSentence;
		MessageUtil.printAndToDivContent("向终端" + clientId + "发送行程记录信息请求",true);
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
		response.setId(protocolClientId);
		response.setBufferId(protocolBufferId);
		String message = new String();
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname = stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String findId = p.getIdByProtocol(classname);
		message += findId;
		characterSentence = Integer.toHexString(Integer.parseInt(characterSentence));
		if(characterSentence.length()==1){
			characterSentence = "0" + characterSentence;
		}
		message += characterSentence;
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

