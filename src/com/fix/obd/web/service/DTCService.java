package com.fix.obd.web.service;

import java.util.ArrayList;
import java.util.Map;

import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.OBDDefect;
import com.fix.obd.web.model.util.FaultCodeIterator;

public interface DTCService {
	public boolean isACCOn(String terminalId);
	public DTCDefect getDTCDefect(String terminalId);
	public OBDDefect getOBDDefect(String terminalId);
	public boolean askDTCDefect(String terminalId);
	public boolean askOBDDefect(String terminalId);
	public boolean askClearDTC(String terminalId);
	public ArrayList<FaultCodeIterator> getFaultCodeIteratorList(String info);
	public Map askDTCDefectInRespond(String terminalId);
}
