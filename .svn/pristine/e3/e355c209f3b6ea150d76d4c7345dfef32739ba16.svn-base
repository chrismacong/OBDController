package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class RailDecode implements Decode {
	private String messageStr;
	private String reply;
	public RailDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		int positionIndex = Integer.valueOf(messageStr.substring(0,2));
		String scapeNode = Integer.toBinaryString(Integer.valueOf(messageStr.substring(2,3)));
		String scape = "";
		if(scapeNode.charAt(0)=='1')
			scape = "圆形";
		else
			scape = "矩形";
		int alertNode = Integer.valueOf(messageStr.substring(3,4));
		String alertType = "";
		switch(alertNode){
		case 1:alertType = "进区域报警";break;
		case 2:alertType = "出区域报警";break;
		case 3:alertType = "进出都报警";break;
		case 4:alertType = "取消围栏";break;
		case 5:alertType = "取消所有围栏";break;
		}
		String longitute_outer = messageStr.substring(4,7)+ "°" + messageStr.substring(7,9) + "." + messageStr.substring(9,12);
		String latitude_outer = messageStr.substring(12,14) + "°" + messageStr.substring(14,16) + "." + messageStr.substring(16,20);
		String longitute_inner = messageStr.substring(20,23)+ "°" + messageStr.substring(23,25) + "." + messageStr.substring(25,28);
		String latitude_inner = messageStr.substring(28,30) + "°" + messageStr.substring(30,32) + "." + messageStr.substring(32,36);
		reply = cha.getCname() + "\t" + scape + "\t" + alertType + "\t大经：" + longitute_outer + "\t大纬：" + latitude_outer + "\t小经：" + longitute_inner + "\t小纬：" + latitude_inner;
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t",";");
	}

}
