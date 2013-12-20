package com.fix.obd.web.service;

import java.util.List;

import com.fix.obd.web.model.OBDData;

public interface OBDInfoService {
	public OBDData getLatestOBDData(String terminalId);
	public int getOBDDataRefreshMinute();
	public boolean askOBDInfo(String terminalId);
	public List<OBDData> getLatest10OBDInfo(String terminalId);
}
