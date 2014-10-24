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

import com.fix.obd.web.dao.TravelExmnationDao;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.TravelExmnation;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class TravelExmnationDaoImpl implements TravelExmnationDao{
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<TravelExmnation> getAllTravelExmnation() {
		// TODO Auto-generated method stub
		String hql = "from TravelExmnation";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<TravelExmnation> travelExmnation_list = query.list();
		return travelExmnation_list;
	}

	@Override
	public void addTravelExmnation(TravelExmnation travelExmnation) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(travelExmnation);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllTravelExmnation() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from travel_exmnation";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteTravelExmnation(TravelExmnation travelExmnation) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(travelExmnation);
		session.beginTransaction().commit();
	}

	@Override
	public void updateTravelExmnation(TravelExmnation travelExmnation) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(travelExmnation);
		session.beginTransaction().commit();
	}

	@Override
	public List<TravelExmnation> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
