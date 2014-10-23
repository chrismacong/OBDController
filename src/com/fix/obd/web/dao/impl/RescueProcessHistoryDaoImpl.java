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

import com.fix.obd.web.dao.RescueProcessHistoryDao;
import com.fix.obd.web.model.RescueProcessHistory;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RescueProcessHistoryDaoImpl implements RescueProcessHistoryDao{
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<RescueProcessHistory> getAllRescueProcessHistory() {
		// TODO Auto-generated method stub
		String hql = "from RescueProcessHistory";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<RescueProcessHistory> rescueprocesshistory_list = query.list();
		return rescueprocesshistory_list;
	}

	@Override
	public void addRescueProcessHistory(
			RescueProcessHistory rescueProcessHistory) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(rescueProcessHistory);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllRescueProcessHistory() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from rescueprocesshistory";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteRescueProcessHistory(
			RescueProcessHistory rescueProcessHistory) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(rescueProcessHistory);
		session.beginTransaction().commit();
	}

	@Override
	public void updateRescueProcessHistory(
			RescueProcessHistory rescueProcessHistory) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(rescueProcessHistory);
		session.beginTransaction().commit();
	}

	@Override
	public List<RescueProcessHistory> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
