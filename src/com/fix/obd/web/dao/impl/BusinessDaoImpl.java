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

import com.fix.obd.web.dao.BusinessDao;
import com.fix.obd.web.model.Business;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class BusinessDaoImpl implements BusinessDao{
	@Resource 
	private SessionFactory sessionFactory;

	@Override
	public List<Business> getAllBusiness() {
		// TODO Auto-generated method stub
		String hql = "from Business";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<Business> business_list = query.list();
		return business_list;
	}

	@Override
	public void addBusiness(Business business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(business);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllBusiness() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from business";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteBusiness(Business business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(business);
		session.beginTransaction().commit();
	}

	@Override
	public void updateBusiness(Business business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(business);
		session.beginTransaction().commit();
	}

	@Override
	public List<Business> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
