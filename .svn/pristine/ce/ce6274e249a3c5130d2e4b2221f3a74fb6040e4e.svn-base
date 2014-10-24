package com.fix.obd.util;

import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttSimpleCallback;

public class SubscribeClient {
	private final static String CONNECTION_STRING = "tcp://192.168.1.60:1883";
	private final static boolean CLEAN_START = true;
	private final static short KEEP_ALIVE = 30;//低耗网络，但是又需要及时获取数据，心跳30s
	private final static String CLIENT_ID = "client1";
	private final static String[] TOPICS = {
		"Test/TestTopics/Topic1",
		"Test/TestTopics/Topic2",
		"Test/TestTopics/Topic3",
		"tokudu/client1"
	};
	private final static int[] QOS_VALUES = {0, 0, 2, 0};

	//////////////////
	private MqttClient mqttClient = null;

	public SubscribeClient(String i){
		try {
			mqttClient = new MqttClient(CONNECTION_STRING);
			SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();
			mqttClient.registerSimpleHandler(simpleCallbackHandler);//注册接收消息方法
			mqttClient.connect(CLIENT_ID+i, CLEAN_START, KEEP_ALIVE);
			mqttClient.subscribe(TOPICS, QOS_VALUES);//订阅接主题

			/**
			 * 完成订阅后，可以增加心跳，保持网络通畅，也可以发布自己的消息
			 */

			mqttClient.publish("PUBLISHER_TOPIC", "keepalive".getBytes(), QOS_VALUES[0], true);

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 简单回调函数，处理client接收到的主题消息
	 * @author pig
	 *
	 */
	class SimpleCallbackHandler implements MqttSimpleCallback{

		/**
		 * 当客户机和broker意外断开时触发
		 * 可以再此处理重新订阅
		 */
		@Override
		public void connectionLost() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("客户机和broker已经断开");
		}

		/**
		 * 客户端订阅消息后，该方法负责回调接收处理消息
		 */
		@Override
		public void publishArrived(String topicName, byte[] payload, int Qos, boolean retained) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("订阅主题: " + topicName);
			System.out.println("消息数据: " + new String(payload));
			System.out.println("消息级别(0,1,2): " + Qos);
			System.out.println("是否是实时发送的消息(false=实时，true=服务器上保留的最后消息): " + retained);
		}

	}

	/**
	 * 高级回调
	 * @author pig
	 *
	 */
	class AdvancedCallbackHandler implements MqttSimpleCallback{

		@Override
		public void connectionLost() throws Exception {
			// TODO Auto-generated method stub

		}

		@Override
		public void publishArrived(String arg0, byte[] arg1, int arg2,
				boolean arg3) throws Exception {
			// TODO Auto-generated method stub

		}

	}

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new SubscribeClient("" + i);
//
//	}

}