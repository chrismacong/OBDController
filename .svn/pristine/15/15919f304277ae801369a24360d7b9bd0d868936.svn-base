package com.fix.obd.protocol.impl;
/*
 * 还没写。。。。10.24
 */
import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.ResponseStrMaker;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class RemoteUpdateRequest extends ODBProtocolParser implements ODBProtocol {
	private static final  Logger logger = Logger.getLogger(RemoteUpdateRequest.class);
	private String clientId;
	private String bufferId;

	public RemoteUpdateRequest(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的升级请求信息", true);

	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String message = this.getRealMessage();
		System.out.println("包序号："+message);
		strForDiv += "包序号："+message;
		String info = "收到更新包请求";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		return false;
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		String[] updateData = new String[5];
		updateData[0] = this.getRealMessage();
		/*
		 * 为保障运行的测试数据
		 */
		updateData[1] = "00";
		updateData[2] = "01";
		updateData[3] = "02";
		updateData[4] = "03";
		SendUpdateData reply = new SendUpdateData(clientId,bufferId,updateData);
		return reply.replyToClient();
	}

	@Override
	public String getStrForDiv() {
		// TODO Auto-generated method stub
		return this.strForDiv;
	}

}
