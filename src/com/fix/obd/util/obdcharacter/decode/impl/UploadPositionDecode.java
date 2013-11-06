package com.fix.obd.util.obdcharacter.decode.impl;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.obdcharacter.decode.Decode;
import com.fix.obd.web.model.util.CharacterIterator;

public class UploadPositionDecode implements Decode {
	private String messageStr;
	private String reply;
	public UploadPositionDecode(String messageStr){
		this.messageStr = messageStr;
	}
	@Override
	public void print(CharacterIterator cha) {
		// TODO Auto-generated method stub
		if("ffff".equals(messageStr)){
			reply = cha.getCname() + "\t" + "����̼�������洢���߻ش�λ����Ϣ" + "\t" + cha.getCnotice();
		}
		else{
			int save_interval_time = Integer.valueOf(messageStr.substring(0,4),16);
			int reply_times = Integer.valueOf(messageStr.substring(4,8),16);
			reply = cha.getCname() + "\t" + "��" + save_interval_time + "KMΪ�ش�����洢λ����Ϣ���ش�" + reply_times + "��" + "\t" + cha.getCnotice();
		}
		MessageUtil.printAndToDivContent(reply, true);
	}

	@Override
	public String ReplyForOperation(CharacterIterator cha) {
		// TODO Auto-generated method stub
		return this.reply.replaceAll("\t", ";");
	}

}
