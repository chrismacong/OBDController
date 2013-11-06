package com.fix.obd.protocol.impl;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class TerminalAck extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(ServerAck.class);
	private String clientId;
	private String bufferId;
	public TerminalAck(String messageStr){
		super(messageStr);
		strForDiv += MessageUtil.printAndToDivContent("�յ������ն�" + this.getId() + "��Ack", true);
		strForDiv += MessageUtil.printAndToDivContent("������Ϣ�������֣�" + this.getRealMessage().substring(0,4),true);
		strForDiv += MessageUtil.printAndToDivContent("������Ϣ����ˮ�ţ�" + this.getRealMessage().substring(4,6),true);
		strForDiv += MessageUtil.printAndToDivContent("�Ƿ�ɹ���" + (this.getRealMessage().substring(6,8).equals("00")?"�ɹ�":"ʧ��"),true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String info = "�յ������ն˵�Ack";
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
