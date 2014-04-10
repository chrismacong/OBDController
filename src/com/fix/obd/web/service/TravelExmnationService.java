package com.fix.obd.web.service;

import java.util.Map;

import com.fix.obd.web.model.TravelExmnation;

public interface TravelExmnationService {
	String getTotalDistance(String terminalId);
	String getLongestDistance(String terminalId);
	String getMaxSpeed(String terminalId);
	String getTotalExceedSeconds(String terminalId);
	String getTotalBrakeTimes(String terminalId);
	String getTotalEmerBrakeTimes(String terminalId);
	String getTotalSpeedUpTimes(String terminalId);
	String getTotalEmerSpeedUpTimes(String terminalId);
	String getAvgSpeed(String terminalId);
	String getMaxWaterTmp(String terminalId);
	String getMaxRevolution(String terminalId);
	String getTotalOilExpend(String terminalId);
	String getAvgOilExpend(String terminalId);
	String getTotalTiredDrivingMinutes(String terminalId);
	public TravelExmnation exmnationAndRecord(String terminalId);
	public Map exmnationScoreAmongFriends(String terminalId);
}	
