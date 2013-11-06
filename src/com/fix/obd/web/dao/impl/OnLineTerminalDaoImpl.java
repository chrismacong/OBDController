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

import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.model.OnLineTerminal;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OnLineTerminalDaoImpl implements OnLineTerminalDao{
	@Resource 
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<OnLineTerminal> getAllOnLineTerminal() {
		// TODO Auto-generated method stub
		String hql = "from OnLineTerminal";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<OnLineTerminal> onlineTerminal_list = query.list();
		return onlineTerminal_list;
	}

	@Override
	public void addOnLineTerminal(OnLineTerminal onlineTerminal) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(onlineTerminal);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllOnLineTerminal() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from online_terminal";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteOnLineTerminal(OnLineTerminal onlineTerminal) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(onlineTerminal);
		session.beginTransaction().commit();
	}

	@Override
	public void updateOnLineTerminal(OnLineTerminal onlineTerminal) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(onlineTerminal);
		session.beginTransaction().commit();
	}

	@Override
	public List<OnLineTerminal> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
