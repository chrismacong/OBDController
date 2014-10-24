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

import com.fix.obd.web.dao.DTCDefectDao;
import com.fix.obd.web.model.DTCDefect;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DTCDefectDaoImpl implements DTCDefectDao{
	@Resource 
	private SessionFactory sessionFactory;

	@Override
	public List<DTCDefect> getAllDTCDefect() {
		// TODO Auto-generated method stub
		String hql = "from DTCDefect";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<DTCDefect> dtcDefect_list = query.list();
		return dtcDefect_list;
	}

	@Override
	public void addDTCDefect(DTCDefect DTCDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(DTCDefect);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllDTCDefect() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from dtc_Defect";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteDTCDefect(DTCDefect DTCDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(DTCDefect);
		session.beginTransaction().commit();
	}

	@Override
	public void updateDTCDefect(DTCDefect DTCDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(DTCDefect);
		session.beginTransaction().commit();
	}

	@Override
	public List<DTCDefect> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
