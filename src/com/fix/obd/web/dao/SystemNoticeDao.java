package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.SystemNotice;

public interface SystemNoticeDao {
	public List<SystemNotice> getAllSystemNotice();

	public void addSystemNotice(SystemNotice systemNotice);

	public void removeAllSystemNotice();

	public void deleteSystemNotice(SystemNotice systemNotice);

	public void updateSystemNotice(SystemNotice systemNotice);

	public List<SystemNotice> findByHQL(String hql) throws Exception;
}
