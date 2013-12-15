package com.fix.obd.jpush.api;

/*
 *  IOS 发�?通知 附加扩展�?
 */
public class IOSExtra {
	
	public IOSExtra(int badge, String sound) {
		this.badge = badge;
		this.sound = sound;
	}
	
	public IOSExtra(String sound) {
		this.sound = sound;
	}
	
	public IOSExtra(int badge) {
		this.badge = badge;
	}
	
	/*
	 * Badge Notification,默认�?0)
	 */
	private int badge = 0;
	/*
	 *  当前软件里面的所拥有的铃声名称（如：message.wav)�?
	 *  不设置，手机默认通知铃声
	 */
	private String sound = ""; 
	
	public int getBadge() {
		return badge;
	}
	
	public void setBadge(int badge) {
		this.badge = badge;
	}
	
	public String getSound() {
		return sound;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	} 
	
}
