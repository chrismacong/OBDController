package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class ServerInfoDecode implements Decode{
	private String messageStr;
	private String reply;
	public ServerInfoDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		int port = Integer.valueOf(messageStr.substring(0,4),16);
		int type_num = Integer.valueOf(messageStr.substring(4,6));
		String type = "";
		boolean inIP = false;
		switch(type_num){
		case 1:
			type="tcp,ip地址";
			inIP = true;
			break;
		case 2:
			type="udp,ip地址";
			inIP = true;
			break;
		case 3:
			type="tcp,域名地址";
			break;
		case 4:
			type="udp,域名地址";
			break;
		}
		System.out.println(inIP);
		String ascii_str = messageStr.substring(6);
		String address = "";
		int length = ascii_str.length()/2;
		for(int i=0;i<length;i++){
			String cutStr = ascii_str.substring(2*i,2*i+2);
			int ascii = Integer.valueOf(cutStr,16);
			if(inIP)
				address += ascii + ".";
			else{
				char realStr = (char)ascii;
				address += realStr;
			}
		}
		if(inIP){
			address = address.substring(0,address.length()-1);
		}
		reply = cha.getCname() + "\t端口号：" + port + "\t地址类型：" + type + "\t域名：" + address + "\t" + cha.getCnotice();
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t",";");
	}
	
}
