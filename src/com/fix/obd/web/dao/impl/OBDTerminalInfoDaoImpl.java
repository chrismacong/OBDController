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

import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.model.OBDTerminalInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OBDTerminalInfoDaoImpl implements OBDTerminalInfoDao{
	@Resource 
	private SessionFactory sessionFactory;

	@Override
	public List<OBDTerminalInfo> getAllOBDTerminalInfo() {
		// TODO Auto-generated method stub
		String hql = "from OBDTerminalInfo";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<OBDTerminalInfo> obdt_list = query.list();
		return obdt_list;
	}

	@Override
	public void addOBDTerminalInfo(OBDTerminalInfo obdt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(obdt);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllOBDTerminalInfo() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from obd_terminal_info";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteOBDTerminalInfo(OBDTerminalInfo obdt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(obdt);
		session.beginTransaction().commit();
	}

	@Override
	public void updateOBDTerminalInfo(OBDTerminalInfo obdt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(obdt);
		session.beginTransaction().commit();
	}

	@Override
	public List<OBDTerminalInfo> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
