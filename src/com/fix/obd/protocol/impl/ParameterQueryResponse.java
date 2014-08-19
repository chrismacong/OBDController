package com.fix.obd.protocol.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import java.lang.reflect.Constructor;

import com.fix.obd.jpush.service.JPushClientExample;
import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.CharacterXMLUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class ParameterQueryResponse extends ODBProtocolParser implements ODBProtocol {
	private static final Logger logger = Logger.getLogger(ParameterQueryResponse.class);
	private String clientId;
	private String bufferId;
	public ParameterQueryResponse(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("收到来自终端" + this.getId() + "的参数查询应答信息", true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		this.clientId = this.getId();
		this.bufferId = this.getBufferId();
		CharacterXMLUtil cha = new CharacterXMLUtil(getClass().getClassLoader().getResource("OBD_Character.xml").getPath());
		ArrayList<CharacterIterator> list = cha.parse();
		String message = this.getRealMessage();
		int count = Integer.valueOf(message.substring(0,2),16);
		message = message.substring(2);
		String info = "收到参数应答信息：";
		if(DBif){
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addOBDLog(clientId, info, messageStr);
		}
		strForDiv += MessageUtil.printAndToDivContent(info, false);
		try {
			String allStr = "";
			for(int i=0;i<count;i++){
				if(message.length()>2){
					String character_id = message.substring(0,2);
					Decode decode;
					Constructor con = null;
					CharacterIterator cha_iterator = null;
					int character_length = Integer.valueOf(message.substring(2,4),16);
					for(int j=0;j<list.size();j++){
						if(list.get(j).getCid().equals(character_id)){
							con = Class.forName("com.fix.obd.util.obdcharacter.decode.impl." + list.get(j).getCdecode()).getConstructor(String.class);
							cha_iterator = list.get(j);
							String length_from_list = list.get(j).getClength();
							if(!"Len".equals(length_from_list)){
								if(character_length!=Integer.parseInt(length_from_list))
									logger.info(list.get(j).getCname() + "长度信息与配置不符");
							}
						}
					}
					System.out.println(message);
					String real_character_message = message.substring(4,4+character_length*2);
					decode = (Decode) con.newInstance(real_character_message);
					decode.print(cha_iterator);
					if(DBif){
						TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
						t.addOBDLog(clientId, decode.ReplyForOperation(cha_iterator), "");
					}
					allStr += decode.ReplyForOperation(cha_iterator) + ";";
					message = message.substring(4+character_length*2);
				}
			}
			if(DBif){
				TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
				t.addParameterResponse(clientId, allStr);
				System.out.println(allStr);
				if(allStr.contains("当前里程+保养里程间隔+下次保养的里程")){

					try {
						this.sentByXML(allStr);
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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}
	public void sentByXML(String str) throws FileNotFoundException, IOException{
		String[] characters = str.split(";");
		JPushClientExample jpush = new JPushClientExample();
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
		String parameterStr = "";
		parameterStr += characters[1].substring(characters[1].indexOf("当前里程为")+5, characters[1].indexOf("公里")) + ";";
		parameterStr += characters[2].substring(characters[2].indexOf("保养间隔")+4, characters[2].indexOf("公里")) + ";";
		parameterStr += characters[3].substring(characters[3].indexOf("下次保养的里程")+7, characters[3].indexOf("公里"));
		jpush.sendMessageToRandomSendNo(operationId + "(" + now + ")", parameterStr, this.getId());

	}
}
