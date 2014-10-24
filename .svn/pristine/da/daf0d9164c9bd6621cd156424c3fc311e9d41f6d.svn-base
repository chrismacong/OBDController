package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class GMTTimeDecode implements Decode{
	private String messageStr;
	private String reply;
	public GMTTimeDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		int hour = Integer.valueOf(messageStr.substring(0,2));
		int minute = Integer.valueOf(messageStr.substring(2,4));
		reply = cha.getCname() + "\t在格林威治时间加上" + hour + "时" + minute + "分\t" + cha.getCnotice();
		MessageUtil.printAndToDivContent(reply, true);
	}
	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}

}
