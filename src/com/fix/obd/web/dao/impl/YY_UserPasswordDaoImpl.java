package com.fix.obd.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fix.obd.web.dao.YY_UserPasswordDao;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.model.YY_UserPassword;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class YY_UserPasswordDaoImpl implements YY_UserPasswordDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public void add(YY_UserPassword userPassword) {
		// TODO Auto-generated method stub
		String hql = "from YY_UserPassword where email='"+userPassword.getEmail()+"'";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_UserPassword> list = query.list();
		if(list.size()==0){                         //数据库里没有email对应的记录
			Session session = this.sessionFactory.getCurrentSession();
			session.save(userPassword);
			session.beginTransaction().commit();
		}else if(list.size()==1){                                      //数据库里已经有email对应的记录，将passwordregetarray修改一下
			Session session = this.sessionFactory.getCurrentSession();
			YY_UserPassword temp = (YY_UserPassword)list.get(0);
			temp.setPasswordregetarray(userPassword.getPasswordregetarray());
			session.update(temp);              //更新数据库
			session.beginTransaction().commit();
		}
	}

	@Override
	public String getPasswordRegetArray(String email) {
		// TODO Auto-generated method stub
		String hql = "from YY_UserPassword where email='"+email+"'";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_UserPassword> list = query.list();
		if(list.size()==0){
			return null;
		}else{
			return list.get(0).getPasswordregetarray();
		}
	}

	@Override
	public void delete(String email) {
		// TODO Auto-generated method stub
		String hql = "from YY_UserPassword where email='"+email+"'";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_UserPassword> list = query.list();
		Session session = this.sessionFactory.getCurrentSession();
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				session.delete((YY_UserPassword)list.get(i));
				session.beginTransaction().commit();
			}
		}
	}

}
