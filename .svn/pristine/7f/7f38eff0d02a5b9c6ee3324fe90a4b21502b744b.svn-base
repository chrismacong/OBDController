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

import com.fix.obd.web.dao.ParameterResponseDao;
import com.fix.obd.web.model.ParameterResponse;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ParameterResponseDaoImpl implements ParameterResponseDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<ParameterResponse> getAllParameterResponse() {
		// TODO Auto-generated method stub
		String hql = "from ParameterResponse";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<ParameterResponse> parameter_response_list = query.list();
		return parameter_response_list;
	}

	@Override
	public void addParameterResponse(ParameterResponse parameterResponse) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(parameterResponse);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllParameterResponse() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
		String sql ="delete from parameter_response";
		Query  query=session.createSQLQuery(sql);
		query.executeUpdate();
		ts.commit();
	}

	@Override
	public void deleteParameterResponse(ParameterResponse parameterResponse) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(parameterResponse);
		session.beginTransaction().commit();
	}

	@Override
	public void updateParameterResponse(ParameterResponse parameterResponse) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(parameterResponse);
		session.beginTransaction().commit();
	}

	@Override
	public List<ParameterResponse> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
	

}
