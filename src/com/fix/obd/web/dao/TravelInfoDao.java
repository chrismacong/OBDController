package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.TravelInfo;

public interface TravelInfoDao {
	public List<TravelInfo> getAllTravelInfo();

	public void addTravelInfo(TravelInfo travelInfo);

	public void removeAllTravelInfo();

	public void deleteTravelInfo(TravelInfo travelInfo);

	public void updateTravelInfo(TravelInfo travelInfo);

	public List<TravelInfo> findByHQL(String hql) throws Exception;
}
