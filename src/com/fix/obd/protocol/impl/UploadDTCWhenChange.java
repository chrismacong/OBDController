package com.fix.obd.protocol.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fix.obd.jpush.service.JPushClientExample;
import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.FaultCodeXMLUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.model.util.FaultCodeIterator;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

//当设备检测到车辆出现故障码信息时，使用这个消息来进行通讯，以区别于主动请求后得到的故障码信息
public class UploadDTCWhenChange extends ODBProtocolParser implements ODBProtocol{
	private static final  Logger logger = Logger.getLogger(UploadDTCWhenChange.class);
	private String clientId;
	private String bufferId;

	public UploadDTCWhenChange(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "上传故障码",true);
	}

	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();

		String message = this.getRealMessage();
		int dtcNumber = Integer.valueOf(message.substring(0,2), 16);
		int stringIndex = 2;
		String allErrors = "";
		for(int i = 0 ; i < dtcNumber ; i++){
			String dtcString = "";
			for(int charIndex = 0 ; charIndex < 5 ; charIndex++){
				dtcString += (char)(int)Integer.valueOf(message.substring(stringIndex,stringIndex+2), 16);
				stringIndex += 2;
			}
			allErrors += dtcString + ",";
			String str = "解析来自终端的故障码-"+(i+1)+":" + dtcString;
			logger.info(str);
			strForDiv += MessageUtil.printAndToDivContent(str, false);
		}

		String info = "收到故障码";
		strForDiv += MessageUtil.printAndToDivContent(info, false);
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
			if(allErrors.lastIndexOf(",")==-1)
				t.updateDTCDefect(clientId, "No Error");
			else{
				t.updateDTCDefect(clientId, allErrors.substring(0,allErrors.lastIndexOf(",")));
				try {
					this.sentByXML(allErrors.substring(0,allErrors.lastIndexOf(",")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		String[] characters = str.split(",");
		JPushClientExample jpush = new JPushClientExample();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		String faultStr = "";
		for(int i=0;i<characters.length;i++){
			faultStr += characters[i] + ":";
			FaultCodeXMLUtil f = new FaultCodeXMLUtil();
			ArrayList<FaultCodeIterator> list = f.parseByIndex(characters[i]);
			if(list.size()==0){
				faultStr += "未识别的故障码";
			}
			else{
				for(int j=0;j<list.size();j++)
					faultStr += list.get(j).getFaultDetail();
			}
			faultStr += ";";
		}
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
		jpush.sendMessageToRandomSendNo(operationId + "(" + now + ")", faultStr, this.getId());
	}
}
