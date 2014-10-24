package com.fix.obd.web.service;

import java.util.List;
import java.util.Map;

import com.fix.obd.web.model.PositionData;


public interface PositionInfoService {
	public PositionData getLatestPositionInfo(String terminalId);
	public List<PositionData> getLatest10PostionInfo(String terminalId);
	public int getGraphRefreshMinute();
	public int getPositionDataRefreshMinute();
	public List<PositionData> getLatest10GpsPositionInfo(String terminalId);
	public Map getStartandStopByGPS(String terminalId, String start_time, String stop_time);
	public Map getPointsForHotArea(String terminalId);
	public List<PositionData> getPositionDataBetweenTime(String terminalId, String start_time, String stop_time);
}
