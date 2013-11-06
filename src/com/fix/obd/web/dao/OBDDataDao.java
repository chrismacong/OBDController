package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.OBDData;

public interface OBDDataDao {
	public List<OBDData> getAllOBDData();

	public void addOBDData(OBDData obdData);

	public void removeAllOBDData();

	public void deleteOBDData(OBDData obdData);

	public void updateOBDData(OBDData obdData);

	public List<OBDData> findByHQL(String hql) throws Exception;
}
