package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class OilCharacterDecode implements Decode {
	private String messageStr;
	private String reply;
	public OilCharacterDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		double swept_volume = Integer.valueOf(messageStr.substring(0,2))*0.1;
		int volume_efficienty = Integer.valueOf(messageStr.substring(2,4));
		reply = cha.getCname() + "\t排量" + swept_volume + "L\t容积效率" + volume_efficienty + "\t" + cha.getCnotice();
		MessageUtil.printAndToDivContent(reply, true);
	}
	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}
}
