package com.fix.obd.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.fix.obd.tcp.impl.TCPDataCommunicator;

/**
 * @title DataReceiverServlet.java
 * @package com.fix.obd.servlet
 * @description ��������TCP����˵�Servlet
 * @author youme.ma
 * @date 2013-8-16 ����3:08:34
 * @version V1.0
 */
public class DataCommunicatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ʼ��������TCP�����
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
		TCPDataCommunicator.getInstance().start();
	}

	/**
	 * ���٣�ֹͣTCP�����
	 */
	public void destroy() {
		super.destroy();
		TCPDataCommunicator.getInstance().stop();
	}
}
