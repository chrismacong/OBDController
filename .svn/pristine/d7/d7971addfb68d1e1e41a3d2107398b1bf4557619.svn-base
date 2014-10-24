package com.fix.obd.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.OnLineTerminal;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.MainService;

@Component
public class MainServiceImpl implements MainService{
	@Resource
	private OnLineTerminalDao onlineTerminalDao;

	public OnLineTerminalDao getOnlineTerminalDao() {
		return onlineTerminalDao;
	}

	public void setOnlineTerminalDao(OnLineTerminalDao onlineTerminalDao) {
		this.onlineTerminalDao = onlineTerminalDao;
	}
	
	@Resource
	private YY_UserDao userDao;

	public YY_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<String> getOnlineTerminals() {
		// TODO Auto-generated method stub
		try {
			List<OnLineTerminal> terminal_list = onlineTerminalDao.getAllOnLineTerminal();
			List<String> onlineId_list = new ArrayList<String>();
			for(int i=0;i<terminal_list.size();i++){
				OnLineTerminal terminal = terminal_list.get(i);
				String terminal_last_heart_beat = terminal.getLastheartbeat();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date last_heart_beat = df.parse(terminal_last_heart_beat);
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(last_heart_beat);
				Calendar cal2 = Calendar.getInstance();
				long sl=cal1.getTimeInMillis();
				long el=cal2.getTimeInMillis();
				long ei=el-sl;
				int minutes = (int)(ei/(1000*60));
				Properties p = new Properties();
				InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
				p.load(is);  
				is.close();
				if(minutes<=Integer.parseInt(p.getProperty("wait_minute")))
					onlineId_list.add(terminal.getTerminalId());
			}
			return onlineId_list;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTerminalOnlineRefreshMinute() {
		// TODO Auto-generated method stub
		try {
			Properties p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
			p.load(is);  
			is.close();
			return Integer.parseInt(p.getProperty("terminal_online_refresh_millisecond"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Map getTerminalsByPartOfIdorUsername(String search_str) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		try {
			List<OnLineTerminal> terminal_list = onlineTerminalDao.findByHQL("from OnLineTerminal where terminalId like '%" + search_str + "%'");
			List<YY_User> user_list = userDao.findByHQL("from YY_User where realname like '%" + search_str + "%'");
			String terminals = "";
			for(int i=0;i<terminal_list.size();i++){
				terminals += terminal_list.get(i).getTerminalId() + ",";
			}
			for(int i=0;i<user_list.size();i++){
				boolean exist = false;
				for(int j=0;j<terminal_list.size();j++){
					if(terminal_list.get(j).getTerminalId().equals(user_list.get(i).getObdnumber()))
						exist = true;
				}
				if(exist==false){
					terminals += user_list.get(i).getObdnumber() + ",";
					List<OnLineTerminal> this_terminal_list = onlineTerminalDao.findByHQL("from OnLineTerminal where terminalId = '" + user_list.get(i).getObdnumber() + "'");
					if(this_terminal_list.size()>0){
						terminal_list.add(this_terminal_list.get(0));
					}
				}
			}
			if(terminals.lastIndexOf(",")>0)
				terminals = terminals.substring(0,terminals.lastIndexOf(","));
			String online_or_offline = "";
			for(int i=0;i<terminal_list.size();i++){
				OnLineTerminal terminal = terminal_list.get(i);
				String terminal_last_heart_beat = terminal.getLastheartbeat();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date last_heart_beat = df.parse(terminal_last_heart_beat);
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(last_heart_beat);
				Calendar cal2 = Calendar.getInstance();
				long sl=cal1.getTimeInMillis();
				long el=cal2.getTimeInMillis();
				long ei=el-sl;
				int minutes = (int)(ei/(1000*60));
				Properties p = new Properties();
				InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
				p.load(is);  
				is.close();
				if(minutes<=Integer.parseInt(p.getProperty("wait_minute")))
					online_or_offline += "1,";
				else
					online_or_offline += "0,";
			}
			if(online_or_offline.lastIndexOf(",")>0)
				online_or_offline = online_or_offline.substring(0,online_or_offline.lastIndexOf(","));
			map.put("terminals", terminals);
			map.put("online_or_offline", online_or_offline);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}
