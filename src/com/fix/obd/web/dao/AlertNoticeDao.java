package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.AlertNotice;

public interface AlertNoticeDao {
	public List<AlertNotice> getAllAlertNotice();

	public void addAlertNotice(AlertNotice alertNotice);

	public void removeAllAlertNotice();

	public void deleteAlertNotice(AlertNotice alertNotice);

	public void updateAlertNotice(AlertNotice alertNotice);

	public List<AlertNotice> findByHQL(String hql) throws Exception;
}
