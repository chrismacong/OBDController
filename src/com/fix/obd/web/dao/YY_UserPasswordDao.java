package com.fix.obd.web.dao;

import com.fix.obd.web.model.YY_UserPassword;

public interface YY_UserPasswordDao {
	public void add(YY_UserPassword userPassword);
	public String getPasswordRegetArray(String email);
	public void delete(String email);
	

}
