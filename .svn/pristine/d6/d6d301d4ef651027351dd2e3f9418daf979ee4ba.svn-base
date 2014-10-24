package com.fix.obd.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MessageUtil {
	public static String buildCheckNode(ResponseStrMaker response){
		String strForCheckNode = response.getId() + response.getBufferId() + response.getLength() + response.getMessageBody();
		byte[] checkBytes = BytesOperater.HexString2Bytes(strForCheckNode);
		BytesOperater.printHexString(strForCheckNode, checkBytes);
		strForCheckNode = BytesOperater.Byte2HexString(BytesOperater.BCC(checkBytes));
		return strForCheckNode;
	}
	public static byte[] buildOutputStream(ResponseStrMaker response) {
		String responseStr = response.buildResponse();
		byte[] protocolBytes = BytesOperater
				.HexString2Bytes(responseStr);
		return protocolBytes;
	}
	public static String reverseStr(String str){
		StringBuffer buffer = new StringBuffer(str);
		StringBuffer result = buffer.reverse();
		return result.toString();
	}
	public static String frontCompWithZore(String oldStr,int formatLength)  
	{  
		String newString = oldStr;
		while(newString.length()<formatLength)
			newString = "0" + newString;
		return  newString;  
	}  
	public static String printAndToDivContent(String info,boolean printOrNot){
		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter("e:\\OBD.txt", true));
			bw1.write(info + "\r\n");
			bw1.flush();
			bw1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(printOrNot)
			System.out.println(info);

		return info+"<br/>";
	}
}
