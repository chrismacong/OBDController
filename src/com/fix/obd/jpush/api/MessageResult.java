package com.fix.obd.jpush.api;

import com.google.gson.Gson;

/*
 * 鍙戦�娑堟伅绔嬪嵆杩斿洖鐨勭姸鎬佸畾涔�
 */
public class MessageResult extends BaseResult {
	public MessageResult() {super();}
	
	public MessageResult(int sendNo,int errcode,String errormsg){		
		this.sendno = sendNo;
		this.errcode = errcode;
		this.errmsg = errormsg;
		
	}
	
	//鍙戦�搴忓彿
	private int sendno = -1;
	//閿欒鐮侊紝璇﹁ErrorCodeEnum
	private int errcode = 10;
	

	public int getSendno() {
		return sendno;
	}
	public void setSendno(int sendno) {
		this.sendno = sendno;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	public static MessageResult fromValue(String result) {	
		MessageResult messageResult = null;
		if ( (null != result) && (!"".equals(result)) ) {
			messageResult = new Gson().fromJson(result, MessageResult.class);
		}
		return messageResult;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
