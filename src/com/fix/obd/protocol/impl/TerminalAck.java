package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class TerminalAck extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(TerminalAck.class);
	private String clientId;
	private String bufferId;
	public TerminalAck(String messageStr){
		super(messageStr);
		strForDiv += MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的Ack", true);
		strForDiv += MessageUtil.printAndToDivContent("接受消息的命令字：" + this.getRealMessage().substring(0,4),true);
		strForDiv += MessageUtil.printAndToDivContent("接受消息的流水号：" + this.getRealMessage().substring(4,6),true);
		strForDiv += MessageUtil.printAndToDivContent("是否成功：" + (this.getRealMessage().substring(6,8).equals("00")?"成功":"失败"),true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String info = "收到来自终端的Ack";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		return true;
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}

}
