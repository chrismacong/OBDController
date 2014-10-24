package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class SwitchDecode implements Decode{
	private String messageStr;
	private String reply;
	public SwitchDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		String[] switches = cha.getCnotice().split(";");
		String content = "";
		for(int i=0;i<switches.length;i++){
			String[] choiceandcontent = switches[i].split(":");
			if(messageStr.equals(choiceandcontent[0]))
				content = choiceandcontent[1];
		}
		reply = cha.getCname() + "\t" + content;
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}
	
}
