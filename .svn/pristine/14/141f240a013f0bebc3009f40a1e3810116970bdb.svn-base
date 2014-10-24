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

import com.fix.obd.web.dao.RescueProcessDao;
import com.fix.obd.web.model.RescueProcess;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RescueProcessDaoImpl implements RescueProcessDao{
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<RescueProcess> getAllRescueProcess() {
		// TODO Auto-generated method stub
		String hql = "from RescueProcess";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<RescueProcess> rescueprocess_list = query.list();
		return rescueprocess_list;
	}

	@Override
	public void addRescueProcess(RescueProcess rescueProcess) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(rescueProcess);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllRescueProcess() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from rescueprocess";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteRescueProcess(RescueProcess rescueProcess) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(rescueProcess);
		session.beginTransaction().commit();
	}

	@Override
	public void updateRescueProcess(RescueProcess rescueProcess) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(rescueProcess);
		session.beginTransaction().commit();
	}

	@Override
	public List<RescueProcess> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
