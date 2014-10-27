package com.fix.obd.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fix.obd.web.dao.AlertNoticeDao;
import com.fix.obd.web.model.AlertNotice;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AlertNoticeDaoImpl implements AlertNoticeDao{

	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<AlertNotice> getAllAlertNotice() {
		// TODO Auto-generated method stub
		String hql = "from AlertNotice";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<AlertNotice> notice_list = query.list();
		return notice_list;
	}

	@Override
	public void addAlertNotice(AlertNotice alertNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(alertNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllAlertNotice() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from alertnotice";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteAlertNotice(AlertNotice alertNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(alertNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void updateAlertNotice(AlertNotice alertNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(alertNotice);
		session.beginTransaction().commit();
	}

	@Override
	public List<AlertNotice> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
