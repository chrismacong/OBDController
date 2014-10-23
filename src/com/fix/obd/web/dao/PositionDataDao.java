package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.PositionData;

public interface PositionDataDao {
	public List<PositionData> getAllPositionData();

	public void addPositionData(PositionData positionData);

	public void removeAllPositionData();

	public void deletePositionData(PositionData positionData);

	public void updatePositionData(PositionData positionData);

	public List<PositionData> findByHQL(String hql) throws Exception;
}
