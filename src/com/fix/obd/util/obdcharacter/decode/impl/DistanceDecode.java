package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class DistanceDecode implements Decode{
	private String messageStr;
	private String reply;
	public DistanceDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		if("ffffffff".equals(messageStr)){
			reply = cha.getCname() + "\t" + "维持里程不变" + "\t" + cha.getCnotice();
		}
		else{
			int recent_miles = Integer.valueOf(messageStr.substring(0,8),16);
			int fit_time_miles = Integer.valueOf(messageStr.substring(8,16),16);
			int next_fit_miles = Integer.valueOf(messageStr.substring(16,24),16);
			reply = cha.getCname() + "\t" + "当前里程为" + recent_miles + "公里\t保养间隔" + fit_time_miles + "公里\t下次保养的里程" + next_fit_miles + "公里"+ cha.getCnotice();
		}
		MessageUtil.printAndToDivContent(reply, true);
	}
	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return reply.replaceAll("\t", ";");
	}
}
