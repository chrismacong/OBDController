package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class CheckTime extends ODBProtocolParser implements ODBProtocol {
	private static final Logger logger = Logger.getLogger(CheckTime.class);
	private String clientId;
	private String bufferId;
	public CheckTime(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的时间查询请求信息",true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		try {
			this.clientId = this.getId();
			this.bufferId = this.getBufferId();
			String info = "收到查询时间信息请求";
			strForDiv += MessageUtil.printAndToDivContent(info, false);
			if(DBif){
				TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
				t.addOBDLog(clientId, info, messageStr);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		SendTime sendTime = new SendTime(clientId,bufferId);
		return sendTime.replyToClient();
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}
}
