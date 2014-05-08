package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.YY_LoginService;

@Component
public class YY_LoginServiceImpl implements YY_LoginService{

	@Resource
	private YY_UserDao userDao;
	public YY_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public boolean askCheckUser(String email, String password) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userlist = userDao.findByHQL("from YY_User where email = '" + email + "'");
			if(userlist.size()==1){
				YY_User user = userlist.get(0);
				if(user.getPassword().equals(password)){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getRoleName(String email) {
		// TODO Auto-generated method stub
		return userDao.getRoleNameByEmail(email);
	}
	
	@Override
	public String getTerminalIdByEmail(String email) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> user_list = userDao.findByHQL("from YY_User where email = '" + email + "'");
			if(user_list.size()>0)
				return user_list.get(0).getObdnumber();
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean askCheckUserByTel(String tel, String password) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userlist = userDao.findByHQL("from YY_User where tel = '" + tel + "'");
			if(userlist.size()==1){
				YY_User user = userlist.get(0);
				if(user.getPassword().equals(password)){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getEmail(String tel) {
		// TODO Auto-generated method stub
		return userDao.getEmailByTel(tel);
	}


}
