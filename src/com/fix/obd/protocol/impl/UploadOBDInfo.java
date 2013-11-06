package com.fix.obd.protocol.impl;

import java.lang.reflect.Constructor;
import org.apache.log4j.Logger;
import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.obd.ASCIIByteDecoder;
import com.fix.obd.util.obd.ByteDecoder;
import com.fix.obd.util.obd.XMLReader;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class UploadOBDInfo extends ODBProtocolParser implements ODBProtocol{
	private static final Logger logger = Logger.getLogger(UploadOBDInfo.class);
	private String clientId;
	private String bufferId;
	private XMLReader reader;
	private String dbStr;

	public UploadOBDInfo(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("�յ������ն�" + this.getId() + "��ODB��Ϣ",true);
		MessageUtil.printAndToDivContent("����������ݣ�"+messageStr,true);
		reader = new XMLReader("OBD_0008.xml");
	}
	
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		
		String realMessage = this.getRealMessage();		
		String time = readTime(realMessage);
		
		boolean sign = true;
		if(sign){
			dbStr = readEffectiveParameter(realMessage);
		}
		else{
			readParameter(realMessage);
		}
		System.out.println(dbStr);
		TerminalServerService t1 = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
		t1.addOBDData(clientId, dbStr,time);
		String info = "�յ�OBD��Ϣ";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		return true;
	}
	
	private boolean isTimeOdd(String time){
		String oneChar = time.charAt(time.length()-1)+"";
		int lastChar = Integer.parseInt(oneChar);
		if(lastChar % 2 == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	//not use
	private void readParameter(String message) {
		// TODO Auto-generated method stub
		int mapSize = reader.getMapSize();
		int effIndex = 12;
		StringBuilder resultBuilder = new StringBuilder();
		
		for(int index = 0 ; index < mapSize ; index++){
			String name = reader.getElementName(index);
			int length = reader.getElementLength(index);
			String handler = reader.getElementHandler(index);
			String introduction = reader.getElementIntroduction(index);

			if(length > 0){
				System.out.println(message.length()+"--"+effIndex+"--"+length*2);
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
					
//					logger.info("�յ�OBD��Ϣ-"+name+":"+result);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(length == -1 && handler.equals("ASCIIByteDecoder")){
				ASCIIByteDecoder decoder = new ASCIIByteDecoder();

				String effString = message.substring(effIndex , effIndex+2);
				effIndex += 2;
				int effInteger = decoder.getStringValue(effString);
				String obdMeg = "�յ�OBD��Ϣ-"+name+":"+effInteger;
				logger.info(obdMeg);
				strForDiv += MessageUtil.printAndToDivContent(obdMeg, false);
				effString = message.substring(effIndex, effIndex+effInteger*2);
				effIndex += effInteger*2;
				String result = decoder.decode(effString, effInteger);
				
				resultBuilder.append(name);
				resultBuilder.append(";");
				resultBuilder.append(result);
				resultBuilder.append(";");
				resultBuilder.append(introduction);

//				logger.info("�յ�OBD��Ϣ-"+name+":"+result);
			}
			
			resultBuilder.append("@");
		}
	}

	private String readEffectiveParameter(String message) {
		// TODO Auto-generated method stub
		String eff = message.substring(12, 22);
		byte[] effBits = changeStringToBits(eff);
		int index = 0;
		int effIndex = 22;
		StringBuilder resultBuilder = new StringBuilder();
		
		while(index < effBits.length){
			if(effBits[index] == 1){
				String name = reader.getElementName(index);
				int length = reader.getElementLength(index);
				String handler = reader.getElementHandler(index);
				String introduction = reader.getElementIntroduction(index);

				if(length > 0){
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
						strForDiv += MessageUtil.printAndToDivContent("�յ�OBD��Ϣ-"+name+":("+length+")"+result, false);
//						logger.info("�յ�OBD��Ϣ-"+name+":("+length+")"+result);
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if(length == -1 && handler.equals("ASCIIByteDecoder")){
					ASCIIByteDecoder decoder = new ASCIIByteDecoder();
					
					String effString = message.substring(effIndex , effIndex+2);
					effIndex += 2;
					int effInteger = decoder.getStringValue(effString);
					if(effIndex+effInteger*2 <  message.length()){
						effString = message.substring(effIndex, effIndex+effInteger*2);
						effIndex += effInteger*2;
						String result = decoder.decode(effString, effInteger);
						resultBuilder.append(name);
						resultBuilder.append(";");
						resultBuilder.append(result);
						resultBuilder.append(";");
						resultBuilder.append(introduction);
						strForDiv += MessageUtil.printAndToDivContent("�յ�OBD��Ϣ-"+name+":("+effInteger+")"+result, false);
//						logger.info("�յ�OBD��Ϣ-"+name+":("+effInteger+")"+result);
					}
					else{
						effString = message.substring(effIndex, message.length());
						effIndex = message.length();
						String result = decoder.decode(effString, effInteger);
						resultBuilder.append(name);
						resultBuilder.append(";");
						resultBuilder.append(result);
						resultBuilder.append(";");
						resultBuilder.append(introduction);
						strForDiv += MessageUtil.printAndToDivContent("�յ����ܴ����OBD��Ϣ-"+name+":("+effInteger+")"+result, false);
//						logger.info("�յ����ܴ����OBD��Ϣ-"+name+":("+effInteger+")"+result);
					}
				}
			}
			index++;
			resultBuilder.append("@");
		}
		return resultBuilder.toString();
	}

	private String readTime(String message){
		String timePart = message.substring(0, 12);
		timePart = timePart.substring(0,2) + "-" + timePart.substring(2,4) + "-" + timePart.substring(4,6) + " " + timePart.substring(6,8) + ":" + timePart.substring(8,10) + ":" + timePart.substring(10,12);
		String timeMeg = "OBD״̬��Ϣ-ʱ�䣺" + timePart;
		logger.info(timeMeg);
		strForDiv += MessageUtil.printAndToDivContent(timeMeg, false);
		return timePart;
	}
	
	private byte[] changeStringToBits(String eff) {
		// TODO Auto-generated method stub
		int length = eff.length();
		byte[] result = new byte[length*4];
		for(int i = 0 ; i < length ; i++){
			char c = eff.charAt(i);
			int cInteger = (c >= 58)?(c-87):(c-48);
			for(int k = 3 ; k >= 0 ; k--){
				if(cInteger >= Math.pow(2, k)){
					result[i*4+3-k] = 1;
					cInteger -= Math.pow(2, k);
				}
			}
		}
		for(int revise = 0 ; revise < length*2 ; revise++){
			byte temp = result[revise];
			result[revise] = result[result.length-1-revise];
			result[result.length-1-revise] = temp;
		}
		
		return result;
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
