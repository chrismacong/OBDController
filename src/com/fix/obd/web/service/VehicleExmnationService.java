package com.fix.obd.web.service;

import java.util.ArrayList;

import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.util.FaultCodeIterator;
import com.fix.obd.web.model.util.VehicleExmnationReport;

public interface VehicleExmnationService {
	public VehicleExmnationReport getVehicleExmnationReport(String terminalId);
	public boolean askDTCDefect(String terminalId);
	public DTCDefect getDTCDefect(String terminalId);
	public ArrayList<FaultCodeIterator> getFaultCodeIteratorList(String info);
}
