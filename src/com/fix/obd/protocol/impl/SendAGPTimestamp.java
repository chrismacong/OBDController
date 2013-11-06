package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.obd.XMLReader;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class SendAGPTimestamp extends ODBProtocolParser implements ODBProtocol{
	private static final Logger logger = Logger.getLogger(SendAGPTimestamp.class);
	private String clientId;
	private String bufferId;
	public SendAGPTimestamp(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的发送本地AGPS数据包时间戳信息",true);
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		
		String message = this.getRealMessage();
		String time = readTime(message);
		String info = "收到本地AGPS数据包时间戳信息";
		strForDiv += "时间戳："+time;
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		System.out.println(time);
		return false;
	}

	private String readTime(String message){
		String timePart = message.substring(0, 12);
		timePart = timePart.substring(0,2) + "-" + timePart.substring(2,4) + "-" + timePart.substring(4,6) + " " + timePart.substring(6,8) + ":" + timePart.substring(8,10) + ":" + timePart.substring(10,12);
		String timeMeg = "本地AGPS数据包更新时间：" + timePart;
		logger.info(timeMeg);
		strForDiv += MessageUtil.printAndToDivContent(timeMeg, false);
		return timePart;
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
