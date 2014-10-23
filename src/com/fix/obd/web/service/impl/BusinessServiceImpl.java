package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.BusinessDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.Business;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.BusinessService;
@Component
public class BusinessServiceImpl implements BusinessService{
	@Resource
	private BusinessDao businessDao;
	public BusinessDao getBusinessDao() {
		return businessDao;
	}

	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	@Resource
	private YY_UserDao yy_userDao;
	

	public YY_UserDao getYy_userDao() {
		return yy_userDao;
	}

	public void setYy_userDao(YY_UserDao yy_userDao) {
		this.yy_userDao = yy_userDao;
	}

	@Override
	public boolean updateBusiness(Business business) {
		// TODO Auto-generated method stub
		try {
			businessDao.updateBusiness(business);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Business getBusinessByBid(int bid) {
		// TODO Auto-generated method stub
		try {
			List<Business> business_list = businessDao.findByHQL("from Business where bid = " + bid);
			if(business_list.size()>0){
				return business_list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String checkBusinessUser(String email, String password) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> userlist = yy_userDao.findByHQL("from YY_User where email = '" + email + "'");
			if(userlist.size()==1){
				YY_User user = userlist.get(0);
				if(user.getPassword().equals(password)){
					return "2";
				}
				else
					return "1";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

}
