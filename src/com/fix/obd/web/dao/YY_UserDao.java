package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.YY_User;

public interface YY_UserDao {
	public List<YY_User> getAllUser();

	public boolean addUser(YY_User User);

	public void removeAllUser();

	public void deleteUser(YY_User User);

	public void updateUser(YY_User User);

	public List<YY_User> findByHQL(String hql) throws Exception;
	
	public String getRoleNameByEmail(String email);
	
	public String getEmailByTel(String tel);

}
