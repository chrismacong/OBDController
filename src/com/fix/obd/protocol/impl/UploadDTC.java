package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class UploadDTC extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(TerminalHeartbeat.class);
	private String clientId;
	private String bufferId;
	
	public UploadDTC(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "上传故障码",true);
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		
		String message = this.getRealMessage();
		int dtcNumber = Integer.valueOf(message.substring(0,2), 16);
		int stringIndex = 2;
		String allErrors = "";
		for(int i = 0 ; i < dtcNumber ; i++){
			String dtcString = "";
			for(int charIndex = 0 ; charIndex < 5 ; charIndex++){
				dtcString += (char)(int)Integer.valueOf(message.substring(stringIndex,stringIndex+2), 16);
				stringIndex += 2;
			}
			allErrors += dtcString + ",";
			String str = "解析来自终端的故障码-"+(i+1)+":" + dtcString;
			logger.info(str);
			strForDiv += MessageUtil.printAndToDivContent(str, false);
		}

		String info = "收到故障码";
		strForDiv += MessageUtil.printAndToDivContent(info, false);
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
			if(allErrors.lastIndexOf(",")==-1)
				t.updateDTCDefect(clientId, "No Error");
			else
				t.updateDTCDefect(clientId, allErrors.substring(0,allErrors.lastIndexOf(",")));
		}
		return true;
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
	public String getStrForDiv(){
		return this.strForDiv;
	}
}
