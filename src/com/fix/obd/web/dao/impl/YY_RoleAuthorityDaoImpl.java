package com.fix.obd.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fix.obd.web.dao.YY_RoleAuthorityDao;
import com.fix.obd.web.model.YY_RoleAuthority;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class YY_RoleAuthorityDaoImpl implements YY_RoleAuthorityDao{

	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<String> getAuthorityList(String rolename){
		String hql = "from YY_RoleAuthority where rolename='"+rolename+"'";
//		ApplicationContext ac = new ClassPathXmlApplicationContext("../springMVC-servlet.xml");
//		sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_RoleAuthority> roleAuthorityList = query.list();
		List<String> authorityList = new ArrayList<String>();
		for(int i=0;i<roleAuthorityList.size();i++){
			authorityList.add(roleAuthorityList.get(i).getAuthorityid());
		}
		return authorityList;
	}


}
