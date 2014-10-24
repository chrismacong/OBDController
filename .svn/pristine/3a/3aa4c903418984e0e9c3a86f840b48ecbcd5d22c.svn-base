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

import com.fix.obd.web.dao.SOSMessageDao;
import com.fix.obd.web.model.SOSMessage;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SOSMessageDaoImpl implements SOSMessageDao{
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<SOSMessage> getAllSOSMessage() {
		// TODO Auto-generated method stub
		String hql = "from SOSMessage";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<SOSMessage> sosmessage_list = query.list();
		return sosmessage_list;
	}

	@Override
	public void addSOSMessage(SOSMessage sosMessage) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(sosMessage);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllSOSMessage() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from sosmessage";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteSOSMessage(SOSMessage sosMessage) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(sosMessage);
		session.beginTransaction().commit();
	}

	@Override
	public void updateSOSMessage(SOSMessage sosMessage) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(sosMessage);
		session.beginTransaction().commit();
	}

	@Override
	public List<SOSMessage> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
