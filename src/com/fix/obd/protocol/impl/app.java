package com.fix.obd.protocol.impl;

public class app {
	public static void main(String args[]){

		String str = "当前里程+保养里程间隔+下次保养的里程;当前里程为15451公里;保养间隔5000公里;下次保养的里程10000公里";
		String[] characters = str.split(";");
		String parameterStr = "";
		parameterStr += characters[1].substring(characters[1].indexOf("当前里程为")+5, characters[1].indexOf("公里")) + ";";
		parameterStr += characters[2].substring(characters[2].indexOf("保养间隔")+4, characters[2].indexOf("公里")) + ";";
		parameterStr += characters[3].substring(characters[3].indexOf("下次保养的里程")+7, characters[3].indexOf("公里"));
		System.out.println(parameterStr);
	}
}
