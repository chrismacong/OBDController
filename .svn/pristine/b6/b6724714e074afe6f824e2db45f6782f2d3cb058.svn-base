package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.service.UpdateConfigService;
@Component
public class UpdateConfigServiceImpl implements UpdateConfigService{
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}

	@Override
	public boolean askUpdate(String terminalId, String index,
			String serverPort, String serverIp, String serverAPN) {
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
				String message = "";
				message += "0" + index;
				if(index.equals("5"))
					message += "00000000";
				else{
					message += MessageUtil.frontCompWithZore((Integer.toHexString(Integer.parseInt(serverPort))),4);
					String server_ip_str = "";
					if(index.equals("1")||index.equals("2")){
						System.out.println(serverIp);
						String[] ips = serverIp.split("\\.");
						for(int i=0;i<ips.length;i++){
							server_ip_str += MessageUtil.frontCompWithZore(Integer.toHexString(Integer.parseInt(ips[i])),2);
						}
					}
					else{
						char[] chars = serverIp.toCharArray();
						for(int i=0;i<chars.length;i++){
							int ascii_int = (int)chars[i];
							String ascii_hex = Integer.toHexString(ascii_int);
							server_ip_str += MessageUtil.frontCompWithZore(ascii_hex, 2);
						}
					}
					String server_ip_length = MessageUtil.frontCompWithZore(Integer.toHexString(server_ip_str.length()/2),2);
					String server_apn_str = "";
					char[] chars2 = serverAPN.toCharArray();
					for(int i=0;i<chars2.length;i++){
						int ascii_int = (int)chars2[i];
						String ascii_hex = Integer.toHexString(ascii_int);
						server_apn_str += MessageUtil.frontCompWithZore(ascii_hex, 2);
					}
					String server_apn_length = MessageUtil.frontCompWithZore(Integer.toHexString(server_apn_str.length()/2),2);
					message += server_ip_length + server_ip_str + server_apn_length + server_apn_str;
				}
				boolean result = u.SentSetUpdateServerInfo(terminalId, bufferId, message);
				return result;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
