package com.fix.obd.protocol.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.fix.obd.jpush.service.JPushClientExample;
import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.ODBProtocolParser;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ProtocolPropertiesUtil;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

public class PositionInfo extends ODBProtocolParser implements ODBProtocol{
	private static final Logger logger = Logger.getLogger(PositionInfo.class);
	private String clientId;
	private String bufferId;
	public PositionInfo(String messageStr){
		super(messageStr);
		MessageUtil.printAndToDivContent("�յ������ն�" + this.getId() + "��λ����Ϣ",true);
	}
	@Override
	public boolean DBOperation(boolean DBif) {
		// TODO Auto-generated method stub
		try {
			String alertStr = "";
			String dbStr = "";
			this.clientId = this.getId();
			this.bufferId = this.getBufferId();
			String message = this.getRealMessage();
			//			System.out.println(message);
			String gpsDate = message.substring(0,2) + "-" + message.substring(2,4) + "-" + message.substring(4,6) + " " + message.substring(6,8) + ":" + message.substring(8,10) + ":" + message.substring(10,12);
			strForDiv += MessageUtil.printAndToDivContent("GPSʱ��" + gpsDate, true);
			String gpsAlert = message.substring(12,16);
			//			System.out.println(gpsAlert);
			String alertInBinary = Integer.toBinaryString(Integer.valueOf(gpsAlert,16));
			alertInBinary = String.format("%016d", Integer.parseInt(alertInBinary));
			alertInBinary = MessageUtil.reverseStr(alertInBinary);
			System.out.println("-----------------------");
			String[] alerts = {"������","���ٱ���","<Ԥ��>","���Ƿѹ","��ع�ѹ","���������򱨾�","���������򱨾�","��ײ����","�����ڱ���","�¸߱���","��������","Obd�������ر���","�𶯱���","gsm��γ�ȸ�ʽ","��ɲ��","����ɲ��"};
			for(int i=0;i<alerts.length;i++){
				if(alertInBinary.charAt(i)=='1'){
					strForDiv += MessageUtil.printAndToDivContent(alerts[i], true);
					alertStr += alerts[i] + ":1;";
				}
				else
					alertStr += alerts[i] + ":0;";
			}
			String gpsState = message.substring(16,18);
			String statusInBinary = Integer.toBinaryString(Integer.valueOf(gpsState,16));
			statusInBinary = String.format("%08d", Integer.parseInt(statusInBinary));
			statusInBinary = MessageUtil.reverseStr(statusInBinary);
			System.out.println("-----------------------");
			System.out.println(alertInBinary);
			System.out.println(statusInBinary);
			String[][] statuses = {
					{"ACC״̬","ACC��","ACC��"},
					{"GPS״̬","GPS��λ","GPS����λ"},
					{"γ������","��γ","��γ"},
					{"��������","����","����"},
					{"ƣ�ͼ�ʻ���","ƣ�ͼ�ʻ","ƣ�ͼ�ʻ�������"},
					{"OBD���","OBD����","OBDδ��������"},
					{"�Ƿ�ʵʱλ����Ϣ","ʵʱλ����Ϣ","��ʵʱλ����Ϣ"},
					{"�豸״̬","�豸�Ѳ�","�豸δ��"}
			};
			for(int i=0;i<statuses.length;i++){
				dbStr += statuses[i][0] + ":" + (statusInBinary.charAt(i)=='1'?statuses[i][1]:statuses[i][2]) + ";";
				strForDiv += MessageUtil.printAndToDivContent(statuses[i][0] + ":" + (statusInBinary.charAt(i)=='1'?statuses[i][1]:statuses[i][2]), true);
			}
			char celidLACorNot = alertInBinary.charAt(2);
			char GPSorNot = statusInBinary.charAt(1);
			//			System.out.println(GPSorNot + " " + celidLACorNot);
			if(GPSorNot=='1'){
				GPSOrientate gps = new GPSOrientate(message.substring(18),dbStr,gpsDate,alertStr);
			}
			else{
				//				if(celidLACorNot=='1'){
				CeliidLACOrientate cellac = new CeliidLACOrientate(message.substring(18),dbStr,gpsDate,alertStr);
				//				}
				//				else if(celidLACorNot=='0'){
				//					Celiid7Orientate cel = new Celiid7Orientate(message.substring(18));
				//				}
			}
			String info = "�յ�����λ����Ϣ";
			if(DBif){
				TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
				t.addOBDLog(clientId, info, messageStr);
				try {
					PositionInfo.sentByXML(alertStr, dbStr);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public byte[] replyToClient() {
		// TODO Auto-generated method stub
		StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
		ServerAck serverACK = new ServerAck(clientId,bufferId,operationId);
		return serverACK.replyToClient();
	}
	public String getStrForDiv(){
		return this.strForDiv;
	}
	//	public static void main(String args[]){
	//		PositionInfo p = new PositionInfo("0000000861825486344109001500040010100016100004410067c951d901cc00005fa3");
	//		p.DBOperation();
	//	}
	public static void sentByXML(String alertStr, String dbStr) throws FileNotFoundException, IOException{
		String[] alertcharacters = alertStr.split(";");
		String[] dbcharacters = dbStr.split(";");
        JPushClientExample j = new JPushClientExample();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
    	StackTraceElement[] stacks = new Throwable().getStackTrace(); 
		String classname =  stacks[0].getClassName().substring(stacks[0].getClassName().lastIndexOf(".")+1);
		ProtocolPropertiesUtil p = new ProtocolPropertiesUtil();
		String operationId = p.getIdByProtocol(classname);
//        for(int i=0;i<alertcharacters.length;i++){
//	        j.sendMessageToRandomSendNo(operationId + "(" + now + ")", alertcharacters[i]);
//        }
//        for(int i=0;i<dbcharacters.length;i++){
//        	j.sendMessageToRandomSendNo(operationId + "(" + now + ")", dbcharacters[i]);
//        }
		String lat = "";
		String lon = "";
		boolean containsLat = false;
		boolean containsLon = false;
		for(int i=0;i<dbcharacters.length;i++){
			if(dbcharacters[i].contains("γ��:")){
				containsLat = true;
				lat = dbcharacters[i].split(":")[1];
			}
		}
		for(int i=0;i<dbcharacters.length;i++){
			if(dbcharacters[i].contains("����:")){
				containsLon = true;
				lon = dbcharacters[i].split(":")[1];
			}
		}
		if(containsLat==true&&containsLon==true){
			lat = lat.replaceAll("\\.", "");
			lat = lat.replaceAll("��", ".");
			String tempStrPart1 = lat.split("\\.")[1];
			int tempInt1 = Integer.parseInt(tempStrPart1)/60*100;
			lat = lat.split("\\.")[0] + "." + tempInt1;
			lon = lon.replaceAll("\\.", "");
			lon = lon.replaceAll("��", ".");
			String tempStrPart2 = lon.split("\\.")[1];
			int tempInt2 = Integer.parseInt(tempStrPart2)/60*100;
			lon = lon.split("\\.")[0] + "." + tempInt2;
			j.sendMessageToRandomSendNo(operationId + "(" + now + ")", lat + "," + lon);
		}
	}
	private class GPSOrientate{
		private String partOfStr;
		private String dbStr;
		private String gpsDate;
		private String alertStr;
		public GPSOrientate(String str,String dbStr,String gpsDate,String alertStr){
			this.partOfStr = str;
			this.dbStr = dbStr;
			this.gpsDate = gpsDate;
			this.alertStr = alertStr;
			this.print();
		}
		public void print(){
			int gpsSatelliteNum = Integer.valueOf(partOfStr.substring(0,2),16);
			dbStr += "���Ǹ���:" + gpsSatelliteNum + ";";
			strForDiv += MessageUtil.printAndToDivContent("���Ǹ���:" + gpsSatelliteNum, true);
			String longitute = partOfStr.substring(2,5)+ "��" + partOfStr.substring(5,7) + "." + partOfStr.substring(7,10);
			dbStr += "����:" + longitute + ";";
			strForDiv += MessageUtil.printAndToDivContent("����:" + longitute, true);
			String latitude = partOfStr.substring(10,12) + "��" + partOfStr.substring(12,14) + "." + partOfStr.substring(14,18);
			dbStr += "γ��:" + latitude + ";";
			strForDiv += MessageUtil.printAndToDivContent("γ��:" + latitude, true);
			int degree = Integer.valueOf(partOfStr.substring(18,20),16)*2;
			dbStr += "�����:" + degree + "��" + ";";
			strForDiv += MessageUtil.printAndToDivContent("�����:" + degree + "��", true);
			String GPSSpeed = Integer.valueOf(partOfStr.substring(20,22),16).toString();
			dbStr += "GPS�ٶ�:" + GPSSpeed + "km/h" + ";";
			strForDiv += MessageUtil.printAndToDivContent("GPS�ٶ�:" + GPSSpeed + "km/h", true);
			String OBDSpeed = Integer.valueOf(partOfStr.substring(22,24),16).toString();
			dbStr += "OBD�ٶ�:" + OBDSpeed + "km/h" + ";";
			strForDiv += MessageUtil.printAndToDivContent("OBD�ٶ�:" + OBDSpeed + "km/h", true);
			String engineWaterTp = Integer.valueOf(partOfStr.substring(24,26),16).toString();
			dbStr += "������ˮ��:" + engineWaterTp + "���϶�" + ";";
			strForDiv += MessageUtil.printAndToDivContent("������ˮ��:" + engineWaterTp + "���϶�", true);
			if(partOfStr.length()>=30){
				String extrabit = partOfStr.substring(26,30);
				String extraInBinary = Integer.toBinaryString(Integer.valueOf(extrabit,16));
				extraInBinary = String.format("%08d", Integer.parseInt(extraInBinary));
				extraInBinary = MessageUtil.reverseStr(extraInBinary);
				String extraContent = partOfStr.substring(30);
				if(extraInBinary.charAt(0)=='1'&&extraContent.length()>=2){
					String positionNum = Integer.valueOf(extraContent.substring(0,2),16).toString();
					extraContent = extraContent.substring(2);
					dbStr += "�ϴ����򱨾���������:" + positionNum + ";";
					strForDiv += MessageUtil.printAndToDivContent("�ϴ����򱨾���������:" + positionNum, true);
				}
				if(extraInBinary.charAt(1)=='1'&&extraContent.length()>=4){
					String RPM = Integer.valueOf(extraContent.substring(0,4),16).toString();
					extraContent = extraContent.substring(4);
					dbStr += "ת��:" + RPM + "r/min" +";";
					strForDiv += MessageUtil.printAndToDivContent("ת��:" + RPM + "r/min", true);
				}
				if(extraInBinary.charAt(2)=='1'&&extraContent.length()>=12){
					String oilLimit = Integer.valueOf(extraContent.substring(0,4),16).toString();
					extraContent = extraContent.substring(4);
					dbStr += "����ACC���۵��ͺ�:" + oilLimit + "*0.01L" +";";
					strForDiv += MessageUtil.printAndToDivContent("����ACC���۵��ͺ�:" + oilLimit + "*0.01L", true);
					String mileLimit = Integer.valueOf(extraContent.substring(0,8),16).toString();
					dbStr += "����ACC���۵����:" + mileLimit + "m" +";";
					strForDiv += MessageUtil.printAndToDivContent("����ACC���۵����:" + mileLimit + "m", true);
				}
			}
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addPositionData(clientId, dbStr,gpsDate,alertStr);
			try {
				PositionInfo.sentByXML(alertStr, dbStr);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	private class Celiid7Orientate{
//		private String partOfStr;
//		public Celiid7Orientate(String str){
//			this.partOfStr = str;
//			this.print();
//		}
//		public void print(){
//			String GSMInfo = "";
//			String GSMMessage = partOfStr.substring(0,14);
//			for(int i=0;i<7;i++){
//				String temp_GSM = GSMMessage.substring(i*2,(i+1)*2);
//				if(i==0)
//					GSMInfo += "��CellID S:" + Integer.valueOf(temp_GSM,16) + "\t";
//				else
//					GSMInfo += "N" + i + ":" + Integer.valueOf(temp_GSM,16) + "\t";
//			}
//			System.out.println(GSMInfo);String OBDSpeed = Integer.valueOf(partOfStr.substring(14,16),16).toString();
//			System.out.println("OBD�ٶ�:" + OBDSpeed + "km/h");
//			String engineWaterTp = Integer.valueOf(partOfStr.substring(16,18),16).toString();
//			System.out.println("������ˮ��:" + engineWaterTp + "���϶�");
//			String extra = partOfStr.substring(18);
//			System.out.println("����λ����Ϣ:" + extra);
//		}
//	}
	private class CeliidLACOrientate{
		private String partOfStr;
		private String dbStr;
		private String gpsDate;
		private String alertStr;
		public CeliidLACOrientate(String str,String dbStr, String gpsDate,String alertStr){
			this.partOfStr = str;
			this.dbStr = dbStr;
			this.gpsDate = gpsDate;
			this.alertStr = alertStr;
			this.print();
		}
		public void print(){
			String celiidInfo = Integer.valueOf(partOfStr.substring(0,6),16).toString();
			dbStr += "Cellid:" + celiidInfo + ";";
			strForDiv += MessageUtil.printAndToDivContent("Cellid:" + celiidInfo, true);
			String lacInfo = Integer.valueOf(partOfStr.substring(6,10),16).toString();
			dbStr += "lac:" + lacInfo + ";";
			strForDiv += MessageUtil.printAndToDivContent("lac:" + lacInfo, true);
			String mcc = Integer.valueOf(partOfStr.substring(10,14),16).toString();
			dbStr += "�ƶ��û��������Ҵ���:" + mcc + ";";
			strForDiv += MessageUtil.printAndToDivContent("�ƶ��û��������Ҵ���:" + mcc, true);
			String mnc = Integer.valueOf(partOfStr.substring(14,16),16).toString();
			dbStr += "�ƶ�������:" + mnc + ";";
			strForDiv += MessageUtil.printAndToDivContent("�ƶ�������:" + mnc, true);
			String OBDSpeed = Integer.valueOf(partOfStr.substring(16,18),16).toString();
			dbStr += "OBD�ٶ�:" + OBDSpeed + "km/h" + ";";
			strForDiv += MessageUtil.printAndToDivContent("OBD�ٶ�:" + OBDSpeed + "km/h", true);
			String engineWaterTp = Integer.valueOf(partOfStr.substring(18,20),16).toString();
			dbStr += "������ˮ��:" + engineWaterTp + "���϶�" + ";";
			strForDiv += MessageUtil.printAndToDivContent("������ˮ��:" + engineWaterTp + "���϶�", true);
			if(partOfStr.length()>=24){
				String extrabit = partOfStr.substring(20,24);
				String extraInBinary = Integer.toBinaryString(Integer.valueOf(extrabit,16));
				extraInBinary = String.format("%08d", Integer.parseInt(extraInBinary));
				extraInBinary = MessageUtil.reverseStr(extraInBinary);
				String extraContent = partOfStr.substring(24);
				if(extraInBinary.charAt(0)=='1'&&extraContent.length()>=2){
					String positionNum = Integer.valueOf(extraContent.substring(0,2),16).toString();
					extraContent = extraContent.substring(2);
					dbStr += "�ϴ����򱨾���������:" + positionNum + ";";
					strForDiv += MessageUtil.printAndToDivContent("�ϴ����򱨾���������:" + positionNum, true);
				}
				if(extraInBinary.charAt(1)=='1'&&extraContent.length()>=4){
					String RPM = Integer.valueOf(extraContent.substring(0,4),16).toString();
					extraContent = extraContent.substring(4);
					dbStr += "ת��:" + RPM + "r/min" +";";
					strForDiv += MessageUtil.printAndToDivContent("ת��:" + RPM + "r/min", true);
				}
				if(extraInBinary.charAt(2)=='1'&&extraContent.length()>=12){
					String oilLimit = Integer.valueOf(extraContent.substring(0,4),16).toString();
					extraContent = extraContent.substring(4);
					dbStr += "����ACC���۵��ͺ�:" + oilLimit + "*0.01L" +";";
					strForDiv += MessageUtil.printAndToDivContent("����ACC���۵��ͺ�:" + oilLimit + "*0.01L", true);
					String mileLimit = Integer.valueOf(extraContent.substring(0,8),16).toString();
					dbStr += "����ACC���۵����:" + mileLimit + "m" +";";
					strForDiv += MessageUtil.printAndToDivContent("����ACC���۵����:" + mileLimit + "m", true);
				}
			}
			TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
			t.addPositionData(clientId, dbStr, gpsDate,alertStr);
		}
	}
}
