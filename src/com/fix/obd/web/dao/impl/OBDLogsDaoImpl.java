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

import com.fix.obd.web.dao.OBDLogsDao;
import com.fix.obd.web.model.OBDLogs;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OBDLogsDaoImpl implements OBDLogsDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<OBDLogs> getAllOBDLogs() {
		// TODO Auto-generated method stub
		String hql = "from OBDLogs";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<OBDLogs> obdlogs_list = query.list();
		return obdlogs_list;
	}

	@Override
	public void addOBDLogs(OBDLogs obdLogs) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(obdLogs);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllOBDLogs() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from obd_logs";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteOBDLogs(OBDLogs obdLogs) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(obdLogs);
		session.beginTransaction().commit();
	}

	@Override
	public void updateOBDLogs(OBDLogs obdLogs) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(obdLogs);
		session.beginTransaction().commit();
	}

	@Override
	public List<OBDLogs> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
