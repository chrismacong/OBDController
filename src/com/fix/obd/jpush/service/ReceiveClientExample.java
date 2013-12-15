package com.fix.obd.jpush.service;
import java.util.List;

import cn.jpush.api.JPushClient;
import cn.jpush.api.receive.ReceiveResult;


public class ReceiveClientExample {

	private static final String appKey ="3af172b794896c3e1de43fe7";	//�������466f7032ac604e02fb7bda89

	private static final String masterSecret = "57c45646c772983eb0e7c455";//"13ac09b17715bd117163d8a1";//����
	
	
	public static void main(String[] args) {
		JPushClient JPushClient = new JPushClient(masterSecret, appKey);
		
		String msgId = "1236722141";
		
		String[] msgIds = {"1236722141","910981248","911034889"};
		
		//��ȡһ��
		ReceiveResult receiveResult =  JPushClient.getReceived(msgId);
		if(receiveResult == null){
			System.out.println("��ȡreceive ����ʧ�ܣ�"+receiveResult);
		}else{
			//gson toJson ֮��NULLֵ���ֶλᱻ���˵�
			System.out.println("received result:"+receiveResult.toString());
		}
	
	
		// ��ȡ����
		List<ReceiveResult> receiveResults = JPushClient.getReceiveds(msgIds);
		if(receiveResults == null ){
			System.out.println("��ȡreceive ����ʧ�ܣ�");
		}else{
			System.out.println("�ɹ���ȡ�ˣ�"+receiveResults);
		}
	
	}
}
