package com.fix.obd.protocol.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class RemoteUpdateResult extends ODBProtocolParser implements ODBProtocol {
	private static final Logger logger = Logger.getLogger(RemoteUpdateResult.class);
	private String clientId;
	private String bufferId;
	private final Map<String , String> updateResult = new HashMap<String ,String>();
	
	public RemoteUpdateResult(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的远程升级结果信息",true);
		
		updateResult.put("00", "升级成功");
		updateResult.put("01", "升级失败，写flash错误");
		updateResult.put("02", "升级失败，校验码错误");
		updateResult.put("03", "升级数据包有问题");
		updateResult.put("04", "升级数据包大小有问题");
		updateResult.put("05", "超时失败");
		
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String message = this.getRealMessage();
		
		if(updateResult.containsKey(message)){
			strForDiv += "远程升级结果:" + updateResult.get(message);
		}
		String info = "收到远程升级结果";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
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

	@Override
	public String getStrForDiv() {
		// TODO Auto-generated method stub
		return this.strForDiv;
	}

}
