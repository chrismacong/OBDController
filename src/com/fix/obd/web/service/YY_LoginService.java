package com.fix.obd.web.service;

public interface YY_LoginService {
	public boolean askCheckUser(String email,String password);
	public String getRoleName(String email);
	public String getTerminalIdByEmail(String email);
	public boolean askCheckUserByTel(String tel,String password);
	public String getEmail(String tel);
}
