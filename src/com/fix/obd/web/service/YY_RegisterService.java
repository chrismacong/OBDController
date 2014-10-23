package com.fix.obd.web.service;

public interface YY_RegisterService {
	public boolean askRegisterUser(String email,String password,String realname,String idnumber,
			                       String nickname,String tel,String cartype,String obdnumber, String carnumber);
    public boolean askRegisterEmail(String email);
    public boolean askRegisterTel(String tel);
    public boolean askBusinessRegisterByName(String bname);
    public boolean askRegisterBusinessUser(String email, String password, String user_tel, String bName, 
    								String address, String longitute, String latitude, String tel,
    								String baktel, String introduction, String bmemberInfo, String bphotoPath);
}
