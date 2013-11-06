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
		MessageUtil.printAndToDivContent("�յ������ն�" + this.getId() + "��Զ�����������Ϣ",true);
		
		updateResult.put("00", "�����ɹ�");
		updateResult.put("01", "����ʧ�ܣ�дflash����");
		updateResult.put("02", "����ʧ�ܣ�У�������");
		updateResult.put("03", "�������ݰ�������");
		updateResult.put("04", "�������ݰ���С������");
		updateResult.put("05", "��ʱʧ��");
		
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String message = this.getRealMessage();
		
		if(updateResult.containsKey(message)){
			strForDiv += "Զ���������:" + updateResult.get(message);
		}
		String info = "�յ�Զ���������";
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
