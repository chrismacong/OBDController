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

import com.fix.obd.web.dao.TravelInfoDao;
import com.fix.obd.web.model.TravelInfo;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class TravelInfoDaoImpl implements TravelInfoDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<TravelInfo> getAllTravelInfo() {
		// TODO Auto-generated method stub
		String hql = "from TravelInfo";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<TravelInfo> travelinfo_list = query.list();
		return travelinfo_list;
	}

	@Override
	public void addTravelInfo(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(travelInfo);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllTravelInfo() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from travel_info";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteTravelInfo(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(travelInfo);
		session.beginTransaction().commit();
	}

	@Override
	public void updateTravelInfo(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(travelInfo);
		session.beginTransaction().commit();
	}

	@Override
	public List<TravelInfo> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
