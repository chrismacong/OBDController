package com.fix.obd.web.util;

import java.util.Random;

public class Random24BitArray {
	public static String getArray(){
		StringBuffer buf = new StringBuffer("");
		int random=0;       //随机数
		int count=0;        //位数
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random r = new Random();
		while(count<24){
			random = Math.abs(r.nextInt(36));             //随机数最大为36-1
			buf.append(str[random]);
			count++;
		}
		return buf.toString();
	}

}
