package com.fix.obd.protocol.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.fix.obd.jpush.service.JPushClientExample;
import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.obd.ByteDecoder;
import com.fix.obd.util.obd.XMLReader;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class UploadTravelInfo extends ODBProtocolParser implements ODBProtocol{
	private static final Logger logger = Logger.getLogger(UploadTravelInfo.class);
	private String clientId;
	private String bufferId;
	private XMLReader xmlReader;
	private String point_address_str = "";
	
	public UploadTravelInfo(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的行程记录信息",true);
		xmlReader = new XMLReader("OBD_0007.xml");
	}
	
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		String realMessage = this.getRealMessage();	
		String otherResult = readParameter(realMessage);
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			point_address_str = t.updateTravelInfo(clientId, otherResult);
		}
		String info = "收到行程信息";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			System.out.println(clientId);
			t.addOBDLog(clientId, info, messageStr);
			try {
				this.sentByXML(otherResult);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private String readParameter(String message){
		System.out.println(message.length());
		int mapSize = xmlReader.getMapSize();
		int effIndex = 0;
		StringBuilder resultBuilder = new StringBuilder();
		for(int index = 0 ; index < mapSize ; index++){
			String name = xmlReader.getElementName(index);
			int length = xmlReader.getElementLength(index);
			String handler = xmlReader.getElementHandler(index);
			String introduction = xmlReader.getElementIntroduction(index);

			if(length > 0 && !handler.isEmpty()){
				String effString = message.substring(effIndex , effIndex+length*2);
				effIndex += length*2;
				
				try {
					Constructor con = Class.forName("com.fix.obd.util.obd."+handler).getConstructor();
					ByteDecoder decoder = (ByteDecoder) con.newInstance();
					String result = decoder.decode(effString, length);
					resultBuilder.append(name);
					resultBuilder.append(";");
					resultBuilder.append(result);
					resultBuilder.append(";");
					resultBuilder.append(introduction);
					resultBuilder.append("@");
					String info = name + ":" + result + " " + introduction;
					strForDiv += MessageUtil.printAndToDivContent(info, false);
					System.out.println(strForDiv);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return resultBuilder.toString();
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
	public void sentByXML(String str) throws FileNotFoundException, IOException{
		String[] characters = str.split("@");
		JPushClientExample jpush = new JPushClientExample();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
        StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
		String character_str = "";
		for(int i=0;i<characters.length;i++){
        	String character_sep[] = characters[i].split(";"); 
        	System.out.println(characters[i]);
        	if(character_sep.length>=3){
        		character_str += character_sep[1] + ";";
        	}
		}
		character_str += point_address_str;
		//jpush.sendMessageToRandomSendNo(operationId + "(" + now + ")", character_str, this.getId());
	}
}
