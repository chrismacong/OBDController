package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.YY_RoleAuthorityDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.YY_RoleAuthorityService;

@Component
public class YY_RoleAuthorityServiceImpl implements YY_RoleAuthorityService{

	@Resource
	private YY_RoleAuthorityDao roleAuthorityDao;

	public YY_RoleAuthorityDao getRoleAuthorityDao() {
		return roleAuthorityDao;
	}

	public void setRoleAuthorityDao(YY_RoleAuthorityDao roleAuthorityDao) {
		this.roleAuthorityDao = roleAuthorityDao;
	}

	@Override
	public List<String> getAuthorityList(String rolename) {
		return roleAuthorityDao.getAuthorityList(rolename);
		
	}
}
