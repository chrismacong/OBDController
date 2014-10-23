package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.Personal_Business;

public interface Personal_BusinessDao {
	public List<Personal_Business> getAllPersonal_Business();

	public void addPersonal_Business(Personal_Business personal_business);

	public void removeAllPersonal_Business();

	public void deletePersonal_Business(Personal_Business personal_business);

	public void updatePersonal_Business(Personal_Business personal_business);

	public List<Personal_Business> findByHQL(String hql) throws Exception;
}
