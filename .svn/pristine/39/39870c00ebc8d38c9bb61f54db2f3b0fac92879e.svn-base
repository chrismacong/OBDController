package com.fix.obd.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.model.util.TodayTravelReport;

public interface TravelInfoService {
	public TravelInfo getTravelInfo(String terminalId);
	public boolean askLastestTravelInfo(String terminalId);
	public boolean askTravelInfo(String terminalId,String index);
	public List<TravelInfo> reviewTravelInfo(String terminalId);
	public Map getTravelInfoHtmlByStartTime(String terminalId, String starttime);
	public Map getBrakesAndSpeedUpsByTravel(String terminalId);
	public TodayTravelReport getTodayTravelReport(String terminalId);
	public List<TravelInfo> getTravelInfoBetweenTime(String terminalId, String from_time_point, String to_time_point);
	public Map getTravelInfoMapByDateForMobileStatistics(String terminalId, String datestr);
}
