package com.fix.obd.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OBDDataDao;
import com.fix.obd.web.dao.PositionDataDao;
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.service.PositionInfoService;
@Component
public class PositionInfoServiceImpl implements PositionInfoService{
	@Resource
	private PositionDataDao positionDataDao;
	public PositionDataDao getPositionDataDao() {
		return positionDataDao;
	}
	public void setPositionDataDao(PositionDataDao positionDataDao) {
		this.positionDataDao = positionDataDao;
	}
	@Override
	public PositionData getLatestPositionInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<PositionData> position_list = positionDataDao.findByHQL("from PositionData where tid = '" + terminalId + "'");
			if(position_list.size()>0){
				PositionData latest_data = position_list.get(0);
				if(position_list.size()>1){
					for(int i=1;i<position_list.size();i++){
						if(position_list.get(i).getDate().compareTo(latest_data.getDate())>0)
							latest_data = position_list.get(i);
					}
				}
				return latest_data;
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
	public List<PositionData> getLatest10PostionInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<PositionData> returnData = new ArrayList<PositionData>();
			List<PositionData> tempData = new ArrayList<PositionData>();
			List<PositionData> position_list = positionDataDao.findByHQL("from PositionData where tid = '" + terminalId + "' order by date desc");
			for(int i=0;i<(position_list.size()>10?10:position_list.size());i++){
				tempData.add(position_list.get(i));
			}
			for(int i=tempData.size()-1;i>=0;i--)
				returnData.add(tempData.get(i));
			return returnData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int getGraphRefreshMinute() {
		// TODO Auto-generated method stub
		try {
			Properties p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
			p.load(is);  
			is.close();
			return Integer.parseInt(p.getProperty("graph_refresh_millisecond"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int getPositionDataRefreshMinute() {
		// TODO Auto-generated method stub
		try {
			Properties p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
			p.load(is);  
			is.close();
			return Integer.parseInt(p.getProperty("position_info_refresh_millisecond"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<PositionData> getLatest10GpsPositionInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<PositionData> returnData = new ArrayList<PositionData>();
			List<PositionData> tempData = new ArrayList<PositionData>();
			List<PositionData> position_list = new ArrayList<PositionData>();
			position_list = positionDataDao.findByHQL("from PositionData where tid = '" + terminalId + "' order by date desc");
			List<PositionData> gps_list = new ArrayList<PositionData>();
			for(int i=0;i<position_list.size();i++){
				if(position_list.get(i).getInfo().contains("GPS×´Ì¬:GPS¶¨Î»"))
					gps_list.add(position_list.get(i));
			}
			for(int i=0;i<(gps_list.size()>10?10:gps_list.size());i++){
				tempData.add(gps_list.get(i));
			}
			for(int i=tempData.size()-1;i>=0;i--)
				returnData.add(tempData.get(i));
			return returnData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
