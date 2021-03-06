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

import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class YY_UserDaoImpl implements YY_UserDao{

	@Resource 
	private SessionFactory sessionFactory;
	@Override
	public List<YY_User> getAllUser() {
		// TODO Auto-generated method stub
		String hql = "from YY_User";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_User> user_list = query.list();
		return user_list;
	}

	@Override
	public boolean addUser(YY_User User) {
		// TODO Auto-generated method stub
		List<YY_User> list = getAllUser();
		boolean success = true;      //添加user成功的标志
		for(int i=0;i<list.size();i++){
			System.out.println(i+list.get(i).getEmail());
			System.out.println("new user email:"+User.getEmail());
			if(User.getEmail().equals(list.get(i).getEmail())){
				success = false;
				break;
			}
		}
		if(success==true){
			Session session = this.sessionFactory.getCurrentSession();
			session.save(User);
			session.beginTransaction().commit();
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public void removeAllUser() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
        String sql ="delete from YY_User";
        Query  query=session.createSQLQuery(sql);
        query.executeUpdate();
        ts.commit();
	}

	@Override
	public void deleteUser(YY_User User) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(User);
		session.beginTransaction().commit();
	}

	@Override
	public void updateUser(YY_User User) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(User);
		session.beginTransaction().commit();
	}

	@Override
	public List<YY_User> findByHQL(String hql) throws Exception {
		// TODO Auto-generated method stub
		try{
			Query queryObject = this.sessionFactory.getCurrentSession().createQuery(hql);
			return queryObject.list();
		}catch(Exception ex){
			throw ex;
		}
	}

	@Override
	public String getRoleNameByEmail(String email) {
		// TODO Auto-generated method stub
		String hql = "from YY_User where email='"+email+"'";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_User> user_list = query.list();
		if(user_list.size()==0){
			return null;
		}else{
			return user_list.get(0).getRole();
		}
	}
	@Override
	public String getEmailByTel(String tel) {
		// TODO Auto-generated method stub
		String hql = "from YY_User where tel='"+tel+"'";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<YY_User> user_list = query.list();
		if(user_list.size()==0){
			return null;
		}else{
			return user_list.get(0).getEmail();
		}
	}

}
