package com.fix.obd.jpush.http;

public enum RequestTypeEnum {
	PUSH(0),
	
	RECEIVE(1);
	
	private final int value;
	
	private RequestTypeEnum(final int value) {
		this.value = value;
	}
	public int value() {
		return this.value;
	}
}
