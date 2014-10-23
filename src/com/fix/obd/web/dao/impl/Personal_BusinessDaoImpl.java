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

import com.fix.obd.web.dao.Personal_BusinessDao;
import com.fix.obd.web.model.Business;
import com.fix.obd.web.model.Personal_Business;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class Personal_BusinessDaoImpl implements Personal_BusinessDao{
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<Personal_Business> getAllPersonal_Business() {
		// TODO Auto-generated method stub
		String hql = "from Personal_Business";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<Personal_Business> personal_business_list = query.list();
		return personal_business_list;
	}

	@Override
	public void addPersonal_Business(Personal_Business personal_business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(personal_business);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllPersonal_Business() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from personal_business";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deletePersonal_Business(Personal_Business personal_business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(personal_business);
		session.beginTransaction().commit();
	}

	@Override
	public void updatePersonal_Business(Personal_Business personal_business) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(personal_business);
		session.beginTransaction().commit();
	}

	@Override
	public List<Personal_Business> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
	
}
