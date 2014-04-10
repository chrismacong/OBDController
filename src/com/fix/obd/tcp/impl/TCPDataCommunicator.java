package com.fix.obd.tcp.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.tcp.ITCPDataCommunicator;
import com.fix.obd.tcp.thread.UploadTerminalDataTask;

/**
 * @title TCPDataReceiver.java
 * @package com.fix.obd.tcp
 * @description OBDЭ�����ݽ�����������ģʽ
 * @author youme.ma
 * @date 2013-8-16 ����3:08:34
 * @version V1.0
 */
public class TCPDataCommunicator implements ITCPDataCommunicator {

	/**
	 * ���캯��
	 */
	private TCPDataCommunicator() {
	}

	private static TCPDataCommunicator instance = null;

	/**
	 * ����ģʽ���������
	 */
	public static TCPDataCommunicator getInstance() {
		if (null == instance)
			instance = new TCPDataCommunicator();
		return instance;
	}

	/**
	 * ����˿�
	 */
	private int serverPort = 4567;
	/**
	 * ���������
	 */
	private int maxConnNum = 1000;
	/**
	 * ���ӳ�ʱʱ��
	 */
	private int timeOut = 60 * 3000;

	/**
	 * ����״̬
	 */
	private int status = 0;

	/**
	 * �����socket
	 */
	private ServerSocket serverSocket = null;

	/**
	 * �ն�socket
	 */
	private Socket socket = null;

	/**
	 * ��־����
	 */
	private Logger logger = Logger.getLogger(TCPDataCommunicator.class);

	@Override
	public void start() {
		try {
			serverSocket = new ServerSocket(serverPort);
			logger.info("�ն����ݽ��շ�����������");
			status = 1;

			while (true) {
				// ��ȡ�ն�����
				socket = serverSocket.accept();

				new Thread(new UploadTerminalDataTask(socket)).start();

			}

		} catch (IOException e) {
			logger.error("�����˿�[" + serverPort + "]ʧ�ܡ�", e);
		}

	}

	@Override
	public void stop() {
		logger.info("�ն����ݽ��շ������رա�");
		serverSocket = null;
		status = 0;
	}

	@Override
	public int getServerPort() {
		return this.serverPort;
	}

	@Override
	public int getMaxConnNum() {
		return this.maxConnNum;
	}

	@Override
	public int getTimeout() {
		return this.timeOut;
	}

	@Override
	public int getStatus() {
		return this.status;
	}
}
