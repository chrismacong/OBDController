package com.fix.obd.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.jpush.service.JPushClientExample;
import com.fix.obd.web.dao.BusinessDao;
import com.fix.obd.web.dao.Personal_BusinessDao;
import com.fix.obd.web.dao.RescueProcessDao;
import com.fix.obd.web.dao.RescueProcessHistoryDao;
import com.fix.obd.web.dao.SOSMessageDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.Business;
import com.fix.obd.web.model.Personal_Business;
import com.fix.obd.web.model.RescueProcess;
import com.fix.obd.web.model.RescueProcessHistory;
import com.fix.obd.web.model.SOSMessage;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.model.util.SOSMessageBroadcastWithExtra;
import com.fix.obd.web.model.util.SOSMessageDealWithExtra;
import com.fix.obd.web.service.SOSBusinessService;
import com.fix.obd.web.util.GeoFilter;
@Component
public class SOSBusinessServiceImpl implements SOSBusinessService {
	@Resource
	private RescueProcessDao rescueProcessDao;
	public RescueProcessDao getRescueProcessDao() {
		return rescueProcessDao;
	}

	public void setRescueProcessDao(RescueProcessDao rescueProcessDao) {
		this.rescueProcessDao = rescueProcessDao;
	}
	@Resource
	private SOSMessageDao sosMessageDao;
	
	public SOSMessageDao getSosMessageDao() {
		return sosMessageDao;
	}
	@Resource
	private YY_UserDao yy_userDao;
	public YY_UserDao getYy_userDao() {
		return yy_userDao;
	}

	public void setYy_userDao(YY_UserDao yy_userDao) {
		this.yy_userDao = yy_userDao;
	}

	public void setSosMessageDao(SOSMessageDao sosMessageDao) {
		this.sosMessageDao = sosMessageDao;
	}

	@Resource
	private BusinessDao businessDao;
	public BusinessDao getBusinessDao() {
		return businessDao;
	}

	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	@Resource
	private RescueProcessHistoryDao rescueProcessHistoryDao;
	public RescueProcessHistoryDao getRescueProcessHistoryDao() {
		return rescueProcessHistoryDao;
	}

	public void setRescueProcessHistoryDao(
			RescueProcessHistoryDao rescueProcessHistoryDao) {
		this.rescueProcessHistoryDao = rescueProcessHistoryDao;
	}

	@Resource
	private Personal_BusinessDao personal_businessDao;
	
	public Personal_BusinessDao getPersonal_businessDao() {
		return personal_businessDao;
	}

	public void setPersonal_businessDao(Personal_BusinessDao personal_businessDao) {
		this.personal_businessDao = personal_businessDao;
	}

	@Override
	public List<SOSMessageBroadcastWithExtra> getBroadcastSOSMessage(String bid) {
		// TODO Auto-generated method stub
		List<SOSMessageBroadcastWithExtra> list = new ArrayList<SOSMessageBroadcastWithExtra>();
		try {
			List<Business> business_list = businessDao.findByHQL("from Business where bid = " + bid);
			if(business_list.size()==0){
				return list;
			}
			Business business = business_list.get(0);
			List<RescueProcess> rescueprocess_list = rescueProcessDao.findByHQL("from RescueProcess where bid = " + bid + " and status = 'broadcast'");
			for(RescueProcess rp:rescueprocess_list){
				SOSMessageBroadcastWithExtra  messageWithExtra = new SOSMessageBroadcastWithExtra();
				SOSMessage sosMessage = rp.getSosmessage();
				messageWithExtra.setRid(rp.getRid());
				messageWithExtra.setDate(sosMessage.getDate());
				messageWithExtra.setInfo(sosMessage.getInfo());
				messageWithExtra.setLatitude(sosMessage.getLatitude());
				messageWithExtra.setLongitude(sosMessage.getLongitude());
				messageWithExtra.setObd_err(sosMessage.getObd_err());
				messageWithExtra.setObd_err_description(sosMessage.getObd_err_description());
				String business_lat_str = business.getLatitude().substring(0,2) + "." + business.getLatitude().substring(2);
				String business_lng_str = business.getLongitute().substring(0,3) + "." + business.getLongitute().substring(3);
				String user_lat_str = sosMessage.getLatitude().substring(0,2) + "." + sosMessage.getLatitude().substring(2);
				String user_lng_str = sosMessage.getLongitude().substring(0,3) + "." + sosMessage.getLongitude().substring(3);
				messageWithExtra.setDistance(GeoFilter.GetDistance(Double.parseDouble(business_lat_str), Double.parseDouble(business_lng_str), Double.parseDouble(user_lat_str), Double.parseDouble(user_lng_str)));
				messageWithExtra.setCartype(sosMessage.getYy_user().getCartype());
				list.add(messageWithExtra);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean giveUpBroadcastSOSMessage(String rid) {
		// TODO Auto-generated method stub
		try {
			List<RescueProcess> rp_list = rescueProcessDao.findByHQL("from RescueProcess where rid = " + rid);
			if(rp_list.size()>0){
				RescueProcess rp = rp_list.get(0);
				rp.setStatus("failed");
				rescueProcessDao.updateRescueProcess(rp);
				RescueProcessHistory rph = new RescueProcessHistory();
				SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
				Date date = new Date();
				rph.setDate(df.format(date));
				rph.setRescueProcess(rp);
				rph.setStatus("failed");
				rescueProcessHistoryDao.addRescueProcessHistory(rph);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean grabBroadcastSOSMessage(String rid) {
		// TODO Auto-generated method stub
		try {
			List<RescueProcess> rp_list = rescueProcessDao.findByHQL("from RescueProcess where rid = " + rid);
			if(rp_list.size()>0){
				RescueProcess rp = rp_list.get(0);
				rp.setStatus("grabbed");
				rescueProcessDao.updateRescueProcess(rp);
				RescueProcessHistory rph = new RescueProcessHistory();
				SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
				Date date = new Date();
				rph.setDate(df.format(date));
				rph.setRescueProcess(rp);
				rph.setStatus("grabbed");
				rescueProcessHistoryDao.addRescueProcessHistory(rph);
				
				
				//JPush����������Ϣ
				YY_User user = rp.getSosmessage().getYy_user();
				String terminalId = user.getObdnumber();
				Business business = rp.getBusiness();
				SOSMessage sosMessage = rp.getSosmessage();
				String business_lat_str = business.getLatitude().substring(0,2) + "." + business.getLatitude().substring(2);
				String business_lng_str = business.getLongitute().substring(0,3) + "." + business.getLongitute().substring(3);
				String user_lat_str = sosMessage.getLatitude().substring(0,2) + "." + sosMessage.getLatitude().substring(2);
				String user_lng_str = sosMessage.getLongitude().substring(0,3) + "." + sosMessage.getLongitude().substring(3);
				double distance = GeoFilter.GetDistance(Double.parseDouble(business_lat_str), Double.parseDouble(business_lng_str), Double.parseDouble(user_lat_str), Double.parseDouble(user_lng_str));
				//��Ϣ��ʽ mid;bid;bname;distance;���۴��;�������
				int appraise_mark = 4; //���۷֣���Ҫ����
				int popular_mark = 3; //�����֣���Ҫ����
				
				String message = sosMessage.getMid() + ";" + business.getBid() + ";" + business.getBname() + ";" + distance + ";" + appraise_mark + ";" + popular_mark;
				JPushClientExample j = new JPushClientExample();
				j.sendMessageToRandomSendNo("Grab", message, terminalId);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<SOSMessageDealWithExtra> getDealSOSMessage(String bid) {
		// TODO Auto-generated method stub
		List<SOSMessageDealWithExtra> list = new ArrayList<SOSMessageDealWithExtra>();
		try {
			List<Business> business_list = businessDao.findByHQL("from Business where bid = " + bid);
			if(business_list.size()==0){
				return list;
			}
			Business business = business_list.get(0);
			List<RescueProcess> rescueprocess_list = rescueProcessDao.findByHQL("from RescueProcess where bid = " + bid + " and status = 'deal'");
			for(RescueProcess rp:rescueprocess_list){
				SOSMessageDealWithExtra  messageWithExtra = new SOSMessageDealWithExtra();
				SOSMessage sosMessage = rp.getSosmessage();
				messageWithExtra.setRid(rp.getRid());
				messageWithExtra.setDate(sosMessage.getDate());
				messageWithExtra.setInfo(sosMessage.getInfo());
				messageWithExtra.setLatitude(sosMessage.getLatitude());
				messageWithExtra.setLongitude(sosMessage.getLongitude());
				messageWithExtra.setObd_err(sosMessage.getObd_err());
				messageWithExtra.setObd_err_description(sosMessage.getObd_err_description());
				String business_lat_str = business.getLatitude().substring(0,2) + "." + business.getLatitude().substring(2);
				String business_lng_str = business.getLongitute().substring(0,3) + "." + business.getLongitute().substring(3);
				String user_lat_str = sosMessage.getLatitude().substring(0,2) + "." + sosMessage.getLatitude().substring(2);
				String user_lng_str = sosMessage.getLongitude().substring(0,3) + "." + sosMessage.getLongitude().substring(3);
				messageWithExtra.setDistance(GeoFilter.GetDistance(Double.parseDouble(business_lat_str), Double.parseDouble(business_lng_str), Double.parseDouble(user_lat_str), Double.parseDouble(user_lng_str)));
				YY_User user = sosMessage.getYy_user();
				messageWithExtra.setCartype(user.getCartype());
				messageWithExtra.setUsername(user.getRealname());
				messageWithExtra.setTel(user.getTel());
				messageWithExtra.setCarnum(user.getCarnumber());
				List<Personal_Business> pb_list = personal_businessDao.findByHQL("from Personal_Business where business_id = " + business.getBid() + " and personal_id = " + user.getId());
				if(pb_list.size()==0)
					messageWithExtra.setMemberOrNot(false);
				else
					messageWithExtra.setMemberOrNot(true);
				list.add(messageWithExtra);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
