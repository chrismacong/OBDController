package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class SleepTimeDecode implements Decode{
	private String messageStr;
	private String reply;
	public SleepTimeDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		int not_sleep_time = Integer.valueOf(messageStr.substring(0,4));
		int sleep_time = Integer.valueOf(messageStr.substring(4,8));
		reply = cha.getCname() + "\t√ª–›√ﬂ ±£∫" + not_sleep_time + "s\t–›√ﬂ ±£∫" + sleep_time + "s\t" + cha.getCnotice();
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t",";");
	}

}
