package com.fix.obd.web.service;

import java.util.List;
import java.util.Map;

import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.TravelInfo;

public interface TravelExmnationService {
	String getTotalDistance(List<TravelInfo> list);
	String getLongestDistance(List<TravelInfo> list);
	String getMaxSpeed(List<TravelInfo> list);
	String getTotalExceedSeconds(List<TravelInfo> list);
	String getTotalBrakeTimes(List<TravelInfo> list);
	String getTotalEmerBrakeTimes(List<TravelInfo> list);
	String getTotalSpeedUpTimes(List<TravelInfo> list);
	String getTotalEmerSpeedUpTimes(List<TravelInfo> list);
	String getAvgSpeed(List<TravelInfo> list);
	String getMaxWaterTmp(List<TravelInfo> list);
	String getMaxRevolution(List<TravelInfo> list);
	String getTotalOilExpend(List<TravelInfo> list);
	String getAvgOilExpend(List<TravelInfo> list);
	String getTotalTiredDrivingMinutes(List<TravelInfo> list);
	public TravelExmnation exmnationAndRecord(String terminalId);
	public Map exmnationScoreAmongFriends(String terminalId);
}	
