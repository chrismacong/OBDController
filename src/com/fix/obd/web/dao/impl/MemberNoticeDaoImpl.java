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

import com.fix.obd.web.dao.MemberNoticeDao;
import com.fix.obd.web.model.MemberNotice;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MemberNoticeDaoImpl implements MemberNoticeDao{
	
	@Resource 
	private SessionFactory sessionFactory;
	
	@Override
	public List<MemberNotice> getAllMemberNotice() {
		// TODO Auto-generated method stub
		String hql = "from MemberNotice";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<MemberNotice> notice_list = query.list();
		return notice_list;
	}

	@Override
	public void addMemberNotice(MemberNotice memberNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(memberNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void removeAllMemberNotice() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from membernotice";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteMemberNotice(MemberNotice memberNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(memberNotice);
		session.beginTransaction().commit();
	}

	@Override
	public void updateMemberNotice(MemberNotice memberNotice) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(memberNotice);
		session.beginTransaction().commit();
	}

	@Override
	public List<MemberNotice> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

}
