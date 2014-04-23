package com.fix.obd.web.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OBDDataDao;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.PersonalService;

@Component
public class PersonalServiceImpl implements PersonalService{
	@Resource
	private OBDDataDao obdDataDao;
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	@Resource
	private YY_UserDao userDao;
	
	public YY_UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}
	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}
	public OBDDataDao getObdDataDao() {
		return obdDataDao;
	}
	public void setObdDataDao(OBDDataDao obdDataDao) {
		this.obdDataDao = obdDataDao;
	}
	@Override
	public double getParameterResultByName(String useremail, String parameter_name) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> user_list = userDao.findByHQL("from YY_User where email = '" + useremail + "'");
			if(user_list.size()>0){
				String terminalId = user_list.get(0).getObdnumber();
				List<OBDData> obd_list  = obdDataDao.findByHQL("from OBDData where tid = '" + terminalId + "' order by date desc");
				if(obd_list.size()>0){
					String obd_info = obd_list.get(0).getInfo();
					obd_info = obd_info.substring(obd_info.lastIndexOf(parameter_name));
					obd_info = obd_info.split(";")[1];
					System.out.println(obd_info);
					if("发动机转速".equals(parameter_name)){
						DecimalFormat df = new DecimalFormat("#.00");
						double d = Double.parseDouble(df.format(Double.parseDouble(obd_info)/1000));
						return d;
					}
					else if("电池电压".equals(parameter_name)){
						DecimalFormat df = new DecimalFormat("#.0");
						double d = Double.parseDouble(df.format(Double.parseDouble(obd_info)/10));
						return d;
					}
					else{
						return Integer.parseInt(obd_info);
					}
				}
			}
			return 0.00;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.00;
		}
	}
}
