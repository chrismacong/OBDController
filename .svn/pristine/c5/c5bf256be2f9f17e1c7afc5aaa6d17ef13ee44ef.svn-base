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
 * @description OBD协议数据接收器，单例模式
 * @author youme.ma
 * @date 2013-8-16 下午3:08:34
 * @version V1.0
 */
public class TCPDataCommunicator implements ITCPDataCommunicator {

	/**
	 * 构造函数
	 */
	private TCPDataCommunicator() {
	}

	private static TCPDataCommunicator instance = null;

	/**
	 * 单例模式创建服务端
	 */
	public static TCPDataCommunicator getInstance() {
		if (null == instance)
			instance = new TCPDataCommunicator();
		return instance;
	}

	/**
	 * 服务端口
	 */
	private int serverPort = 4567;
	/**
	 * 最大连接数
	 */
	private int maxConnNum = 1000;
	/**
	 * 连接超时时长
	 */
	private int timeOut = 60 * 1000 * 10;

	/**
	 * 运行状态
	 */
	private int status = 0;

	/**
	 * 服务端socket
	 */
	private ServerSocket serverSocket = null;

	/**
	 * 终端socket
	 */
	private Socket socket = null;

	/**
	 * 日志对象
	 */
	private Logger logger = Logger.getLogger(TCPDataCommunicator.class);

	@Override
	public void start() {
		try {
			serverSocket = new ServerSocket(serverPort);
			logger.info("Server start successfully!");
			status = 1;

			while (true) {
				// 获取终端连接
				socket = serverSocket.accept();

				new Thread(new UploadTerminalDataTask(socket)).start();

			}

		} catch (IOException e) {
			logger.error("Failed starting port [" + serverPort + "].", e);
		}

	}

	@Override
	public void stop() {
		logger.info("The server connection server is down.");
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
