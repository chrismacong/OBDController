package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.RescueProcess;

public interface RescueProcessDao {
	public List<RescueProcess> getAllRescueProcess();

	public void addRescueProcess(RescueProcess rescueProcess);

	public void removeAllRescueProcess();

	public void deleteRescueProcess(RescueProcess rescueProcess);

	public void updateRescueProcess(RescueProcess rescueProcess);

	public List<RescueProcess> findByHQL(String hql) throws Exception;
}
