package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class TenMinTimeDecode implements Decode{
	private String messageStr;
	private String reply;
	public TenMinTimeDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		int character_int = Integer.valueOf(messageStr,16);
		String cname = cha.getCname();
		String cnotice = cha.getCnotice();
		reply = cname + "\t" + character_int*10 + "min\t" + cnotice;
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}
}
