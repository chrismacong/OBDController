package com.fix.obd.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.model.OnLineTerminal;
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
	
}
