package com.fix.obd.web.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.fix.obd.tcp.impl.TCPDataCommunicator;
import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.dao.YY_RoleAuthorityDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;

public class DBInitializing implements InitializingBean {
	@Resource
	private OnLineTerminalDao onlineTerminalDao;
	public OnLineTerminalDao getOnlineTerminalDao() {
		return onlineTerminalDao;
	}
	public void setOnlineTerminalDao(OnLineTerminalDao onlineTerminalDao) {
		this.onlineTerminalDao = onlineTerminalDao;
	}
	@Resource 
	private YY_UserDao yy_UserDao;
	
	public YY_UserDao getYy_UserDao() {
		return yy_UserDao;
	}
	public void setYy_UserDao(YY_UserDao yy_UserDao) {
		this.yy_UserDao = yy_UserDao;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void tcpInit() throws Exception{
		YY_User user = new YY_User();
		user.setCarnumber("Nil");
		user.setCartype("Nil");
		user.setEmail("obd_admin@163.com");
		user.setIdnumber("Nil");
		user.setNickname("Nil");
		user.setObdnumber("Nil");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setRealname("admin");
		user.setRole("manager");
		user.setTel("Nil");
		yy_UserDao.addUser(user);
		TCPThread thread = new TCPThread();
		thread.start();
	}
}
class TCPThread extends Thread{
	public void run(){
		TCPDataCommunicator.getInstance().start();
	}
}