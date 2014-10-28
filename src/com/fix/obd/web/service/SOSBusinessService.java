package com.fix.obd.web.service;

import java.util.List;

import com.fix.obd.web.model.util.SOSMessageBroadcastWithExtra;
import com.fix.obd.web.model.util.SOSMessageDealWithExtra;
import com.fix.obd.web.model.util.SOSMessageFailedWithExtra;

public interface SOSBusinessService {
	public List<SOSMessageBroadcastWithExtra> getBroadcastSOSMessage(String bid);
	public boolean giveUpBroadcastSOSMessage(String rid);
	public boolean grabBroadcastSOSMessage(String rid);
	public List<SOSMessageDealWithExtra> getDealSOSMessage(String bid);
	public List<SOSMessageBroadcastWithExtra> getGrabbedSOSMessage(String bid);
	public List<SOSMessageDealWithExtra> getCompletedSOSMessage(String bid);
	public List<SOSMessageFailedWithExtra> getFailedSOSMessage(String bid);
}
