package com.fix.obd.web.service;

import java.util.List;

public interface MainService {
	public List<String> getOnlineTerminals();
	public int getTerminalOnlineRefreshMinute();
}
