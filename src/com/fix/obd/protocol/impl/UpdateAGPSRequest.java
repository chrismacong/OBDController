package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class UpdateAGPSRequest extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(UpdateAGPSRequest.class);
	private String clientId;
	private String bufferId;
	
	public UpdateAGPSRequest(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("�յ������ն�" + this.getId() + "�ĸ���AGPS������Ϣ",true);
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String message = this.getRealMessage();
		System.out.println("AGPS���ݰ���ţ�"+message);
		String info = "�յ����ظ���AGPS������Ϣ";
		strForDiv += "���ݰ���ţ�"+message;
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		return false;
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
		ServerAck serverACK = new ServerAck(clientId,bufferId,operationId);
		return serverACK.replyToClient();
	}

	@Override
	public String getStrForDiv() {
		// TODO Auto-generated method stub
		return this.strForDiv;
	}

}
