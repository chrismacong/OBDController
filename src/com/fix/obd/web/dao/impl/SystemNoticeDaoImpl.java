package com.fix.obd.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fix.obd.web.dao.SystemNoticeDao;
import com.fix.obd.web.model.SystemNotice;

public class SystemNoticeDaoImpl implements SystemNoticeDao{

	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<SystemNotice> getAllSystemNotice() {
		// TODO Auto-generated method stub
		String hql = "from SystemNotice";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<SystemNotice> notice_list = query.list();
		return notice_list;
	}

	@Override
	public void addSystemNotice(SystemNotice systemNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(systemNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllSystemNotice() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from systemnotice";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteSystemNotice(SystemNotice systemNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(systemNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void updateSystemNotice(SystemNotice systemNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(systemNotice);
		session.beginTransaction().commit();
	}

	@Override
	public List<SystemNotice> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
