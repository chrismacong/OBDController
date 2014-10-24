package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class ASCIIDecode implements Decode{
	private String messageStr;
	private String reply;
	public ASCIIDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		String urlMessage = "";
		int length = messageStr.length()/2;
		for(int i=0;i<length;i++){
			String cutStr = messageStr.substring(2*i,2*i+2);
			int ascii = Integer.valueOf(cutStr,16);
			char realStr = (char)ascii;
			urlMessage += realStr;
		}
		reply = cha.getCname() + "\t" + urlMessage + "\t" + cha.getCnotice();
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}

}
