package com.fix.obd.jpush.api;
import java.util.HashSet;
import java.util.Set;


/**
 *  接口更多参数的详解请参�? :http://docs.jpush.cn/display/dev/Push+API+v2 
 *  
 */
public class MessageParams {
	/*
	 * 发�?编号。由�?��者自己维护，标识�?��发�?请求
	 */
	private int sendNo = 0;

	/*
	 * 待覆盖的上一条消息的 ID�?
	 * 指明此参数，并且经确认该 msg_id 的确是该 AppKey 之前曾经使用过的，那么：Android 通知展示时会覆盖之前的�?
	 */
	private String overrideMsgId;

	/*
	 *  (appKey)，只能填写一个�?
	 * 如果不填，则会向�?��的应用发送�?
	 */
	private String appKey = "";

	/*
	 * 枚举类定�?ReceiverTypeEnum
	 */
	private ReceiverTypeEnum receiverType;

	/*
	 * 发�?范围值，�?receiverType 相对应�?
	 * receiverType = 4 不用设置
	 */
	private String receiverValue = "";

	/*
	 * 保存离线的时长�?秒为单位。最多支�?0天（864000秒）�?
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息�?
	 * 此参数不设置则表示默认，默认为保�?天的离线消息�?6400秒）�?
	 */
	private long timeToLive; 

	/*
	 * 每个应用对应�?��masterSecret，用来校�?
	 */
	private String masterSecret;

	/*
	 * 描述此次发�?调用�?
	 * 不会发到用户�?
	 */
	private String sendDescription = "";

	/*
	 * 目标用户中断手机的平台类型，如：android, ios
	 */
	private Set<DeviceEnum> platform = new HashSet<DeviceEnum>();

	/*
	 * 推�?介意接口消息内容 
	 */
	private String txt;
	private String targetPath;

	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	/*
	 * 发�?消息的内容�?
	 * �?msg_type 相对应的值�?
	 */
	private MsgContent msgContent = new MsgContent();

	public class MsgContent {
		private String title = "";
		private String message = "";

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "";
		}
	}
	public String getOverrideMsgId() {
		return overrideMsgId;
	}
	public void setOverrideMsgId(String overrideMsgId) {
		this.overrideMsgId = overrideMsgId;
	}
	public MsgContent getMsgContent() {
		return this.msgContent;
	}
	public int getSendNo() {
		return sendNo;
	}
	public void setSendNo(int sendNo) {
		this.sendNo = sendNo;
	}
	public String getAppKey() {
		return this.appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public long getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getMasterSecret() {
		return masterSecret;
	}
	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	public ReceiverTypeEnum getReceiverType() {
		return this.receiverType;
	}
	public void setReceiverType(ReceiverTypeEnum receiverType) {
		this.receiverType = receiverType;
	}
	public String getReceiverValue() {
		return this.receiverValue;
	}
	public void setReceiverValue(String receiverValue) {
		this.receiverValue = receiverValue;
	}
	public String getSendDescription() {
		return sendDescription;
	}
	public void setSendDescription(String sendDescription) {
		this.sendDescription = sendDescription;
	}
	public String getPlatform() {
		String keys = "";
		for (DeviceEnum key : this.platform) {
			keys += (key.value() + ",");
		}
		return keys.length() > 0 ? keys.substring(0, keys.length()-1) : "";
	}
	public void addPlatform(DeviceEnum platform) {
		this.platform.add(platform);
	}

	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}

}
