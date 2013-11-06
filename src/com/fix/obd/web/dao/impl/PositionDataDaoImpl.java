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

import com.fix.obd.web.dao.PositionDataDao;
import com.fix.obd.web.model.PositionData;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class PositionDataDaoImpl implements PositionDataDao{
	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<PositionData> getAllPositionData() {
		// TODO Auto-generated method stub
		String hql = "from PositionData";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<PositionData> positiondata_list = query.list();
		return positiondata_list;
	}

	@Override
	public void addPositionData(PositionData positionData) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(positionData);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllPositionData() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from position_data";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deletePositionData(PositionData positionData) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(positionData);
		session.beginTransaction().commit();
	}

	@Override
	public void updatePositionData(PositionData positionData) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(positionData);
		session.beginTransaction().commit();
	}

	@Override
	public List<PositionData> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}
}
