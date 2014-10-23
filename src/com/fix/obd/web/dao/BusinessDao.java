package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.Business;

public interface BusinessDao {
	public List<Business> getAllBusiness();

	public void addBusiness(Business business);

	public void removeAllBusiness();

	public void deleteBusiness(Business business);

	public void updateBusiness(Business business);

	public List<Business> findByHQL(String hql) throws Exception;
}
