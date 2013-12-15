package com.fix.obd.jpush.http;



public class BaseURL {

	public static String HOST_NAME_SSL = "https://api.jpush.cn:443";
	public static String HOST_NAME = "http://api.jpush.cn:8800";

	public static final String ALL_PATH = "/v2/push";       //全功能接�?
	public static final String SIMPLE_CUSTOM_PATH = "/v2/custom_message"; //�?��接口，自定义消息
	public static final String SIMPLE_NOTIFICATION_PATH = "/v2/notification"; //�?��接口，自定义通知


	public static  String RECEIVE_HOST_NAME = "https://report.jpush.cn:443";  
	public static  String RECEIVE_PATH = "/v2/received";

	private static String getHostname(boolean enableSSL,Integer type) {
		if(type == RequestTypeEnum.PUSH.value())
			return enableSSL? HOST_NAME_SSL :HOST_NAME;
		
		if(type == RequestTypeEnum.RECEIVE.value())
			return enableSSL? RECEIVE_HOST_NAME:RECEIVE_HOST_NAME;
		
		return null;
	}

	public  static String getUrlForPath(final String path,boolean enableSSL,Integer type) {
		return getHostname(enableSSL,type) + path;
	}
	
	
	

}
