package com.fix.obd.web.service;

import java.util.List;

import com.fix.obd.web.model.PositionData;


public interface PositionInfoService {
	public PositionData getLatestPositionInfo(String terminalId);
	public List<PositionData> getLatest10PostionInfo(String terminalId);
	public int getGraphRefreshMinute();
	public int getPositionDataRefreshMinute();
	public List<PositionData> getLatest10GpsPositionInfo(String terminalId);
}
