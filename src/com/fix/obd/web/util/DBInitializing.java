package com.fix.obd.web.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.fix.obd.tcp.impl.TCPDataCommunicator;
import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.model.OnLineTerminal;

public class DBInitializing implements InitializingBean {
	@Resource
	private OnLineTerminalDao onlineTerminalDao;
	public OnLineTerminalDao getOnlineTerminalDao() {
		return onlineTerminalDao;
	}
	public void setOnlineTerminalDao(OnLineTerminalDao onlineTerminalDao) {
		this.onlineTerminalDao = onlineTerminalDao;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void tcpInit() throws Exception{
		TCPThread thread = new TCPThread();
		thread.start();
	}
}
class TCPThread extends Thread{
	public void run(){
		TCPDataCommunicator.getInstance().start();
	}
}