package com.fix.obd.protocol.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import java.lang.reflect.Constructor;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.CharacterXMLUtil;
import com.fix.obd.util.MessageUtil;
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
}
