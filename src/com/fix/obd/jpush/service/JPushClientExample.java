package com.fix.obd.jpush.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fix.obd.jpush.api.DeviceEnum;
import com.fix.obd.jpush.api.ErrorCodeEnum;
import com.fix.obd.jpush.api.IOSExtra;
import com.fix.obd.jpush.api.JPushClient;
import com.fix.obd.jpush.api.MessageResult;

public class JPushClientExample {

	private static final String appKey ="ee73c46290de0ffaafe65a1c";	//�������466f7032ac604e02fb7bda89

	private static final String masterSecret = "621508697148594a45e581a9";//"13ac09b17715bd117163d8a1";//���ÿ��Ӧ�ö���Ӧһ��masterSecret

	private JPushClient jpush = null;

	/**
	 * �������ߵ�ʱ������Ϊ��λ�����֧��10�죨864000�룩��
	 * 0 ��ʾ����Ϣ���������ߡ������û��������Ϸ�������ǰ�������û��������յ�����Ϣ��
	 * �˲������������ʾĬ�ϣ�Ĭ��Ϊ����1���������Ϣ��86400��)��
	 */
	private static long timeToLive =  60 * 60 * 24;  

	public JPushClientExample() {
		/*
		 * Example1: ��ʼ��,Ĭ�Ϸ��͸�android��ios��ͬʱ����������Ϣ���ʱ��
		 * jpush = new JPushClient(masterSecret, appKey, timeToLive);
		 */

		/*		
		 * Example2: ֻ���͸�android
		 * jpush = new JPushClient(masterSecret, appKey, DeviceEnum.Android);
		 */

		/*
		 * Example3: ֻ���͸�IOS
		 * jpush = new JPushClient(masterSecret, appKey, DeviceEnum.IOS);
		 */

		/*
		 * Example4: ֻ���͸�android,ͬʱ����������Ϣ���ʱ��
		 * jpush = new JPushClient(masterSecret, appKey, timeToLive, DeviceEnum.Android);
		 */


		jpush = new JPushClient(masterSecret, appKey, timeToLive);

		/*
		 * �Ƿ�����ssl��ȫ����, ��ѡ
		 * ����������true�� ����false��Ĭ��Ϊ��ssl����
		 */
		//jpush.setEnableSSL(true);


		//���Է�����Ϣ����֪ͨ
		//this.sendMessageToRandomSendNo("����һֻСѼ��ѽ","��ѽ��ѽ��~~~~");
	}

	public void sendMessageToRandomSendNo(String msgTitle, String msgContent,String terminalId) {
		// ��ʵ��ҵ���У����� sendNo ��һ�����Լ���ҵ����Դ����һ���������֡�
		// ������Ҫ���ǣ���ȷ����Ҫ�ظ�ʹ�á�������ο� API �ĵ����˵����
		int sendNo = getRandomSendNo();

		/*
		 * IOS�豸��չ����,
		 * ����badge����������
		 */

		Map<String, Object> extra = new HashMap<String, Object>();
		IOSExtra iosExtra = new IOSExtra(10, "WindowsLogonSound.wav");
		extra.put("ios", iosExtra);

		//�������û�����֪ͨ, ���෽����ο��ĵ�
//		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo,msgTitle, msgContent);
//		MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		MessageResult msgResult  = jpush.sendCustomMessageWithAlias(sendNo, terminalId, msgTitle, msgContent);
		//����ָ��msgId����Ϣ,msgId���Դ�msgResult.getMsgid()��ȡ��
		//MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, 0, extra,msgResult.getMsgid());


		if (null != msgResult) {
			System.out.println("��������������: " + msgResult.toString());
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				System.out.println(String.format("���ͳɹ��� sendNo= %s,messageId= %s",msgResult.getSendno(),msgResult.getMsg_id()));
			} else {
				System.out.println("����ʧ�ܣ� �������=" + msgResult.getErrcode() + ", ������Ϣ=" + msgResult.getErrmsg());
			}
		} else {
			System.out.println("�޷���ȡ����");
		}

	}

	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;

	/**
	 * ���� sendNo ��Ψһ�����б�Ҫ��
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
		return (int) (MIN + Math.random() * (MAX - MIN));
	}
}
