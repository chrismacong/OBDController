package com.fix.obd.web.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	public List<String> getOnlineTerminals();
	public int getTerminalOnlineRefreshMinute();
	public Map getTerminalsByPartOfIdorUsername(String search_str);
}
