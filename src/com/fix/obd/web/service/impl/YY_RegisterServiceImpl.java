package com.fix.obd.web.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.BusinessDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.Business;
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
	@Resource
	private BusinessDao business_dao;
	
	public BusinessDao getBusiness_dao() {
		return business_dao;
	}

	public void setBusiness_dao(BusinessDao business_dao) {
		this.business_dao = business_dao;
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
		    	return true;          //email未注册
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;        //注册用email已经存在    
		
	}

	@Override
	public boolean askRegisterTel(String tel) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userList = userDao.findByHQL("from YY_User where tel='"+tel+"'");
		    if(userList.size()==0){
		    	return true;          //email未注册
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;        //注册用email已经存在    
	}

	@Override
	public boolean askRegisterBusinessUser(String email, String password, String user_tel,
			String bName, String address, String longitute, String latitude,
			String tel, String baktel, String introduction, String bmemberInfo,
			String bphotoPath) {
		// TODO Auto-generated method stub
		YY_User new_business_user = new YY_User();
		new_business_user.setEmail(email);
		new_business_user.setPassword(password);
		new_business_user.setRole("business");
		new_business_user.setRealname("Nil");
		new_business_user.setNickname("Nil");
		new_business_user.setCarnumber("Nil");
		new_business_user.setCartype("Nil");
		new_business_user.setIdnumber("Nil");
		new_business_user.setObdnumber("Nil");
		new_business_user.setTel(user_tel);
		if(userDao.addUser(new_business_user)){
			YY_User that_user_been_added = this.getBusinessUserByEmail(email);
			Business business = new Business();
			business.setUser(that_user_been_added);
			business.setAddress(address);
			business.setBaktel(baktel);
			business.setTel(tel);
			business.setBmemberinfo(bmemberInfo);
			business.setBname(bName);
			business.setBphotopath(bphotoPath);
			business.setIntroduction(introduction);
			business.setLatitude(latitude);
			business.setLongitute(longitute);
			business_dao.addBusiness(business);
			return true;
		}
		return false;
	}

	@Override
	public boolean askBusinessRegisterByName(String bname) {
		// TODO Auto-generated method stub
		try {
			List<Business> business_list = business_dao.findByHQL("from Business where bname='"+bname+"'");
		    if(business_list.size()==0){
		    	return true;          //bname未注册
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;        //注册用bname已经存在    
	}
	
	private YY_User getBusinessUserByEmail(String email){
		try {
			List<YY_User> userList = userDao.findByHQL("from YY_User where email='"+email+"'");
		    if(userList.size()>0){
		    	return userList.get(0);          
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;        //注册用email已经存在    
	}

}
