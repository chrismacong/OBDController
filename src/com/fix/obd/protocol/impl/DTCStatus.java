package com.fix.obd.protocol.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class DTCStatus extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(TerminalHeartbeat.class);
	private String clientId;
	private String bufferId;
	
	public DTCStatus(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "OBD故障状态",true);
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();

		String message = this.getRealMessage();
		int messageInteger = Integer.valueOf(message , 16);
		if(messageInteger == 0){
			message = "正常";
		}
		else if(messageInteger == 1){
			message = "警告";
		}
		else if(messageInteger == 2){
			message = "严重";
		}
		String info = "收到OBD故障状态："+messageInteger+"，"+message;
		strForDiv += MessageUtil.printAndToDivContent(info, true);
		try {
			this.sentByXML(message);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
			t.updateOBDDefect(clientId, message);
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
	public void sentByXML(String str) throws FileNotFoundException, IOException{
		Element root = new Element("faultstatusxml");
		Document Doc = new Document(root);
		Element elements = new Element("faultstatus");
		elements.setAttribute("id", "" + 0);
		elements.addContent(new Element("status").setText(str));
		root.addContent(elements);  
		XMLOutputter XMLOut = new XMLOutputter();  
		XMLOut.output(Doc, new FileOutputStream("e://faultstatus_to_apk.xml"));  
	}
}
