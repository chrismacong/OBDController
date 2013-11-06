package com.fix.obd.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.fix.obd.tcp.impl.TCPDataCommunicator;

/**
 * @title DataReceiverServlet.java
 * @package com.fix.obd.servlet
 * @description 用于启动TCP服务端的Servlet
 * @author youme.ma
 * @date 2013-8-16 下午3:08:34
 * @version V1.0
 */
public class DataCommunicatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化：启动TCP服务端
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
		TCPDataCommunicator.getInstance().start();
	}

	/**
	 * 销毁：停止TCP服务端
	 */
	public void destroy() {
		super.destroy();
		TCPDataCommunicator.getInstance().stop();
	}
}
