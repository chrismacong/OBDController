package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.YY_EditPasswordService;

@Component
public class YY_EditPasswordServiceImpl implements YY_EditPasswordService{
	@Resource
	private YY_UserDao userDao;
	public YY_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public boolean askEditPassword(String email, String password) {
		// TODO Auto-generated method stub
		
		try {
			List<YY_User> userlist = userDao.findByHQL("from YY_User where email = '" + email + "'");
			if(userlist.size()==1){
				YY_User user = userlist.get(0);
				user.setPassword(password);
				userDao.updateUser(user);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
