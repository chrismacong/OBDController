package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.OBDDefect;

public interface OBDDefectDao {
	public List<OBDDefect> getAllOBDDefect();

	public void addOBDDefect(OBDDefect OBDDefect);

	public void removeAllOBDDefect();

	public void deleteOBDDefect(OBDDefect OBDDefect);

	public void updateOBDDefect(OBDDefect OBDDefect);

	public List<OBDDefect> findByHQL(String hql) throws Exception;
}
