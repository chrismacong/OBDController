package com.fix.obd.protocol.impl;

public class app {
	public static void main(String args[]){

		String str = "��ǰ���+������̼��+�´α��������;��ǰ���Ϊ15451����;�������5000����;�´α��������10000����";
		String[] characters = str.split(";");
		String parameterStr = "";
		parameterStr += characters[1].substring(characters[1].indexOf("��ǰ���Ϊ")+5, characters[1].indexOf("����")) + ";";
		parameterStr += characters[2].substring(characters[2].indexOf("�������")+4, characters[2].indexOf("����")) + ";";
		parameterStr += characters[3].substring(characters[3].indexOf("�´α��������")+7, characters[3].indexOf("����"));
		System.out.println(parameterStr);
	}
}
