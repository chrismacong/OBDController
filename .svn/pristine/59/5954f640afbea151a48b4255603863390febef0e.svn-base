package com.fix.obd.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.YY_UserPasswordDao;
import com.fix.obd.web.model.YY_UserPassword;
import com.fix.obd.web.service.YY_UserPasswordService;

@Component
public class YY_UserPasswordServiceImpl implements YY_UserPasswordService{
	@Resource
	private YY_UserPasswordDao userPasswordDao;

	public YY_UserPasswordDao getUserPasswordDao() {
		return userPasswordDao;
	}

	public void setUserPasswordDao(YY_UserPasswordDao userPasswordDao) {
		this.userPasswordDao = userPasswordDao;
	}
	
	@Override
	public void addRecord(String email, String array){
		YY_UserPassword userPassword = new YY_UserPassword();
		userPassword.setEmail(email);
		userPassword.setPasswordregetarray(array);
		userPasswordDao.add(userPassword);
	}
	
	@Override
	public boolean checkArray(String email, String array){
		String properArray = userPasswordDao.getPasswordRegetArray(email);
		if(properArray==null){
			return false;
		}
		if(properArray.equals(array)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void delete(String email){
		userPasswordDao.delete(email);
	}

}
