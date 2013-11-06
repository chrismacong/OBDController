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

import com.fix.obd.web.dao.OBDDefectDao;
import com.fix.obd.web.model.OBDDefect;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OBDDefectDaoImpl implements OBDDefectDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<OBDDefect> getAllOBDDefect() {
		// TODO Auto-generated method stub
		String hql = "from OBDDefect";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<OBDDefect> obddefect_list = query.list();
		return obddefect_list;
	}

	@Override
	public void addOBDDefect(OBDDefect OBDDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(OBDDefect);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllOBDDefect() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from obd_defect";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteOBDDefect(OBDDefect OBDDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(OBDDefect);
		session.beginTransaction().commit();
	}

	@Override
	public void updateOBDDefect(OBDDefect OBDDefect) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(OBDDefect);
		session.beginTransaction().commit();
	}

	@Override
	public List<OBDDefect> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
