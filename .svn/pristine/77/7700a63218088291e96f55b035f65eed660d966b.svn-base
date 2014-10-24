package com.fix.obd.web.service;

public interface TerminalServerService {
	public void updateTerminalHeartBeat(String clientId,String bufferId);
	public void updateTerminalInfo(String clientId,String clientIp);
	public void addOBDLog(String clientId,String info,String message);
	public void addOBDData(String clientId,String info, String obdTime);
	public void addPositionData(String clientId, String info, String gpsDate, String alertStr);
	public void addParameterResponse(String clientId,String info);
	public void updateOBDDefect(String clientId, String info);
	public void updateDTCDefect(String clientId, String info);
	public String updateTravelInfo(String clientId, String info);
}
