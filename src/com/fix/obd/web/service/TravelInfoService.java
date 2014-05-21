package com.fix.obd.web.service;

import java.util.List;

import com.fix.obd.web.model.TravelInfo;

public interface TravelInfoService {
	public TravelInfo getTravelInfo(String terminalId);
	public boolean askLastestTravelInfo(String terminalId);
	public boolean askTravelInfo(String terminalId,String index);
	public List<TravelInfo> reviewTravelInfo(String terminalId);
}
