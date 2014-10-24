package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.CharacterXMLUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.ParameterResponseDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.ParameterResponse;
import com.fix.obd.web.model.util.CharacterIterator;
import com.fix.obd.web.service.CharacterService;
@Component
public class CharacterServiceImpl implements CharacterService {
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	@Resource
	private ParameterResponseDao parameterResponseDao;
	public ParameterResponseDao getParameterResponseDao() {
		return parameterResponseDao;
	}

	public void setParameterResponseDao(ParameterResponseDao parameterResponseDao) {
		this.parameterResponseDao = parameterResponseDao;
	}

	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}

	@Override
	public List<CharacterIterator> getAllCharacterIdAndName() {
		// TODO Auto-generated method stub
		CharacterXMLUtil cha = new CharacterXMLUtil(getClass().getClassLoader().getResource("OBD_Character.xml").getPath());
		return cha.parse();
	}

	@Override
	public boolean haveSentParameters(String terminalId, String characterSentence) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + terminalId + "'");
			if(list.size()>0){
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				boolean result = u.SentQueryParameters(terminalId, bufferId, characterSentence);
				return result;
			}
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String[] getParameterList(String terminalId) {
		// TODO Auto-generated method stub

		CharacterXMLUtil cha = new CharacterXMLUtil(getClass().getClassLoader().getResource("OBD_Character.xml").getPath());
		List<CharacterIterator> clist = cha.parse();
		String[] answer = new String[clist.size()];
		for(int i=0;i<answer.length;i++){
			answer[i] = "";
		}
		try {
			List<ParameterResponse> list = parameterResponseDao.findByHQL("from ParameterResponse where tid = '" + terminalId + "'");
			if(list.size()>0){
				String parameterAndAnswer[] = list.get(0).getInfo().substring(0,list.get(0).getInfo().lastIndexOf(";")).split(";");
				for(int i=0;i<clist.size();i++){
					String charactername = clist.get(i).getCname();
					for(int j=0;j<parameterAndAnswer.length;j+=3){
						if(charactername.equals(parameterAndAnswer[j]))
							answer[i] = parameterAndAnswer[j+1];
					}
				}
			}
			return answer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return answer;
		}
	}

	@Override
	public boolean haveSetParameters(String terminalId, String characterSentence) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + terminalId + "'");
			if(list.size()>0){
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				boolean result = u.SentSetParameters(terminalId, bufferId, characterSentence);
				return result;
			}
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
