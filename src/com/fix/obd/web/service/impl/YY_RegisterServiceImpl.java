package com.fix.obd.web.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.YY_RegisterService;

@Component
public class YY_RegisterServiceImpl implements YY_RegisterService{
    
	@Resource
	private YY_UserDao userDao;
	public YY_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public boolean askRegisterUser(String email, String password,
			String realname, String idnumber, String nickname, String tel,
			String cartype, String obdnumber, String carnumber) {
		// TODO Auto-generated method stub
		YY_User newUser = new YY_User();
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setRealname(realname);
		newUser.setIdnumber(idnumber);
		newUser.setNickname(nickname);
		newUser.setTel(tel);
		newUser.setCartype(cartype);
		newUser.setObdnumber(obdnumber);
		newUser.setCarnumber(carnumber);
		newUser.setRole("member");
		if(userDao.addUser(newUser)){
			return true;
		}
		return false;
	}

	@Override
	public boolean askRegisterEmail(String email) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userList = userDao.findByHQL("from YY_User where email='"+email+"'");
		    if(userList.size()==0){
		    	return true;          //emailδע��
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;        //ע����email�Ѿ�����    
		
	}

	@Override
	public boolean askRegisterTel(String tel) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userList = userDao.findByHQL("from YY_User where tel='"+tel+"'");
		    if(userList.size()==0){
		    	return true;          //emailδע��
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;        //ע����email�Ѿ�����    
	}

}
