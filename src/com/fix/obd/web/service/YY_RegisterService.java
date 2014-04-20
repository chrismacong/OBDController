package com.fix.obd.web.service;

public interface YY_RegisterService {
	public boolean askRegisterUser(String email,String password,String realname,String idnumber,
			                       String nickname,String tel,String cartype,String obdnumber, String carnumber);
}
