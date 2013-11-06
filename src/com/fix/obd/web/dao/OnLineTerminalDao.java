package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.OnLineTerminal;

public interface OnLineTerminalDao {
	public List<OnLineTerminal> getAllOnLineTerminal();

	public void addOnLineTerminal(OnLineTerminal onlineTerminal);

	public void removeAllOnLineTerminal();

	public void deleteOnLineTerminal(OnLineTerminal onlineTerminal);

	public void updateOnLineTerminal(OnLineTerminal onlineTerminal);

	public List<OnLineTerminal> findByHQL(String hql) throws Exception;
}
