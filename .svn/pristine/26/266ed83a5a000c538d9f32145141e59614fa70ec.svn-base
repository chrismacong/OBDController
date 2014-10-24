package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class TimeDecode implements Decode{
	private String messageStr;
	private String reply;
	public TimeDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		String date = messageStr.substring(0,2) + "-" + 
				messageStr.substring(2,4) + "-" +
				messageStr.substring(4,6) + " " + 
				messageStr.substring(6,8) + ":" +
				messageStr.substring(8,10) + ":" + 
				messageStr.substring(10,12);
		String cname = cha.getCname();
		String cnotice = cha.getCnotice();
		reply = cname + "\t" + date + "\t" + cnotice;
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t",";");
	}
}
