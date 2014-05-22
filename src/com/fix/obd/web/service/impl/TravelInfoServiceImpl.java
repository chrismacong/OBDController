package com.fix.obd.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.TravelInfoDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.service.TravelInfoService;

@Component
public class TravelInfoServiceImpl implements TravelInfoService{
	@Resource
	private TravelInfoDao travelInfoDao;
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}

	public TravelInfoDao getTravelInfoDao() {
		return travelInfoDao;
	}

	public void setTravelInfoDao(TravelInfoDao travelInfoDao) {
		this.travelInfoDao = travelInfoDao;
	}

	@Override
	public TravelInfo getTravelInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' order by date desc");
			if(info_list.size()>0){
				return info_list.get(0);
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean askLastestTravelInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + MessageUtil.frontCompWithZore(terminalId, 20) + "'");
			if(list.size()>0){
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				boolean result = u.SentQueryLastItinerary(terminalId, bufferId);
				return result;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean askTravelInfo(String terminalId, String index) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + MessageUtil.frontCompWithZore(terminalId, 20) + "'");
			if(list.size()>0){
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				boolean result = u.SentRequestTravelInfo(terminalId, bufferId, index);
				return result;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TravelInfo> reviewTravelInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' order by SUBSTR(info,27,12) desc");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size()-1;i++){
					for(int j=info_list.size()-1;j>i;j--){
						TravelInfo info1 = info_list.get(i);
						TravelInfo info2 = info_list.get(j);
						String info1_str = info1.getInfo().split("@")[0] + info1.getInfo().split("@")[1];
						String info2_str = info2.getInfo().split("@")[0] + info2.getInfo().split("@")[1];
						if(info1_str.equals(info2_str))
							info_list.remove(j);
					}
				}
				if(info_list.size()>10){
					List<TravelInfo> info_list_in_10 = new ArrayList<TravelInfo>();
					for(int i=0;i<10;i++)
						info_list_in_10.add(info_list.get(i));
					return info_list_in_10;
				}
				return info_list;
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map getTravelInfoHtmlByStartTime(String terminalId, String starttime) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		if(starttime.length()==17){
			String start_time_none_format = starttime.substring(0,2) + starttime.substring(3,5) + starttime.substring(6,8) + starttime.substring(9,11) + starttime.substring(12,14) + starttime.substring(15);
			try {
				List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and SUBSTR(info,27,12)='" + start_time_none_format + "'");
				if(info_list.size()>0){
					TravelInfo info = info_list.get(0);
					String infoStr = info.getInfo().substring(0,info.getInfo().lastIndexOf("@"));
					String[] characters = infoStr.split("@");
					String resultStr = "";
					String start_time = characters[0].split(";")[1];
					resultStr += "��ʼʱ�䣺 " + start_time.substring(0,2) + "-" + start_time.substring(2,4) + "-" + start_time.substring(4,6) + " " + start_time.substring(6,8) + ":" + start_time.substring(8,10) + ":" + start_time.substring(10,12) + "<br/>";
					String stop_time = characters[1].split(";")[1];
					resultStr += "����ʱ�䣺 " + stop_time.substring(0,2) + "-" + stop_time.substring(2,4) + "-" + stop_time.substring(4,6) + " " + stop_time.substring(6,8) + ":" + stop_time.substring(8,10) + ":" + stop_time.substring(10,12) + "<br/>";
					int COUNT_IF_EXCEED_TIME = 0;
					int COUNT_IF_TIRED = 0;
					int COUNT_BRAKE = 0;
					int COUNT_EMER_BRAKE = 0;
					int COUNT_SPEED_UP = 0;
					int COUNT_EMER_SPEED_UP = 0;
					for(int i=2; i<characters.length; i++){
						String[] temps = characters[i].split(";");
						if(temps[0].equals("��ѹֵ"))
							resultStr += temps[0] + "�� " + Integer.parseInt(temps[1])*0.1 + "V<br/>";
						else if(temps[0].equals("���ͺ�"))
							resultStr += temps[0] + "�� " + Integer.parseInt(temps[1])*0.01 + "��<br/>";
						else if(temps[0].equals("ƽ���ͺ�"))
							resultStr += temps[0] + "�� " + Integer.parseInt(temps[1])*0.01 + "�ٹ�����<br/>";
						else
							resultStr += temps[0] + "�� " + temps[1] + temps[2] + "<br/>";
						if(temps[0].equals("��ʱʱ��")){
							if(!temps[1].equals("0"))
								COUNT_IF_EXCEED_TIME++;
						}
						if(temps[0].equals("ƣ�ͼ�ʻʱ��")){
							if(!temps[1].equals("0"))
								COUNT_IF_TIRED++;
						}
						if(temps[0].equals("��ɲ������")){
							COUNT_BRAKE = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("����ɲ������")){
							COUNT_EMER_BRAKE = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("�����ٴ���")){
							COUNT_SPEED_UP = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("�������ٴ���")){
							COUNT_EMER_SPEED_UP = Integer.parseInt(temps[1]);
						}
					}
					int score = 100 - COUNT_IF_EXCEED_TIME * 10 -
							COUNT_IF_TIRED * 10 - 
							COUNT_BRAKE * 2 - 
							COUNT_EMER_BRAKE * 10 - 
							COUNT_SPEED_UP * 2 - 
							COUNT_EMER_SPEED_UP * 10;
					score = (score<0)?0:score;
					map.put("resultStr", resultStr);
					map.put("score", score);
					return map;
				}
				else
					return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
