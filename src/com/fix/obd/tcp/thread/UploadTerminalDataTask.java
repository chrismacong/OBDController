package com.fix.obd.tcp.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.protocol.impl.CheckAGPSDataTime;
import com.fix.obd.protocol.impl.ClearBlindSpotData;
import com.fix.obd.protocol.impl.ClearDTC;
import com.fix.obd.protocol.impl.InstallPositionLearning;
import com.fix.obd.protocol.impl.Listen;
import com.fix.obd.protocol.impl.QueryLastItinerary;
import com.fix.obd.protocol.impl.QueryParameters;
import com.fix.obd.protocol.impl.ReadDTC;
import com.fix.obd.protocol.impl.ReadDTCStatus;
import com.fix.obd.protocol.impl.RebootTerminal;
import com.fix.obd.protocol.impl.RequestOBDInfo;
import com.fix.obd.protocol.impl.RequestTravelInfo;
import com.fix.obd.protocol.impl.RestoreFactorySettings;
import com.fix.obd.protocol.impl.RollCall;
import com.fix.obd.protocol.impl.SendAGPSDatatime;
import com.fix.obd.protocol.impl.SetArmOrDisarm;
import com.fix.obd.protocol.impl.SetParameters;
import com.fix.obd.protocol.impl.SetUpdateServerInfo;
import com.fix.obd.util.IdPropertiesUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.ThtApplicationContext;

/**
 * @title UploadTerminalDataTask.java
 * @package com.fix.obd.tcp.thread
 * @description TODO
 * @author youme.ma
 * @date 2013-9-3 下午2:50:02
 * @version V1.0
 */
public class UploadTerminalDataTask implements Runnable {
	private Socket socket;
	private int WAIT_SECOND = 600;
	private boolean ACCEPT_QUERY_PARAMETER = false;
	private boolean ACCEPT_TERMINAL_ACK = false;
	private boolean ACCEPT_DTC = false;
	private boolean ACCEPT_DTC_STATUS = false;
	private boolean ACCEPT_OBD_INFO = false;
	private boolean ACCEPT_TRAVEL_INFO = false;

	/**
	 * 输入流数据
	 */
	private InputStream is;
	/**
	 * 输出流数据
	 */
	private OutputStream os;
	private SocketAddress terminalAddress;

	public UploadTerminalDataTask(Socket socket) {
		this.socket = socket;
		terminalAddress = socket.getRemoteSocketAddress();
		try {
			Properties p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
			p.load(is);  
			is.close();
			this.WAIT_SECOND = Integer.parseInt(p.getProperty("request_wait_second"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final int BUFFERSIZE = 512;
	/**
	 * 日志对象
	 */
	private Logger logger = Logger.getLogger(UploadTerminalDataTask.class);
	public boolean SentQueryParameters(String terminalId, String bufferId, String characterSentence){
		try {
			os = socket.getOutputStream();
			QueryParameters q = new QueryParameters(terminalId, bufferId, characterSentence);
			boolean opTemp = q.DBOperation(true);
			byte[] responseBytes = q.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_QUERY_PARAMETER = false;
			int waitSec = 0;
			while(ACCEPT_QUERY_PARAMETER==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<参数查询>等待终端返回参数的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_QUERY_PARAMETER = false;
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentSetParameters(String terminalId, String bufferId, String characterSentence){
		try{
			os = socket.getOutputStream();
			SetParameters s = new SetParameters(terminalId, bufferId, characterSentence);
			boolean opTemp = s.DBOperation(true);
			byte[] responseBytes = s.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<参数设置>等待终端返回Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentReadDTC(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			ReadDTC r = new ReadDTC(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_DTC = false;
			int waitSec = 0;
			while(ACCEPT_DTC==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<查询故障码>等待终端返回故障码的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_DTC = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentReadDTCStatus(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			ReadDTCStatus r = new ReadDTCStatus(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_DTC_STATUS = false;
			int waitSec = 0;
			while(ACCEPT_DTC_STATUS==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<查询故障状态>等待终端返回故障状态的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_DTC_STATUS = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentClearDTC(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			ClearDTC c = new ClearDTC(terminalId, bufferId);
			boolean opTemp = c.DBOperation(true);
			byte[] responseBytes = c.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<清除故障码>等待终端返回Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentRequestOBDInfo(String terminalId, String bufferId) {
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			RequestOBDInfo r = new RequestOBDInfo(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_OBD_INFO = false;
			int waitSec = 0;
			while(ACCEPT_OBD_INFO==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<请求OBD数据>等待终端返回OBD数据的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_OBD_INFO = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentQueryLastItinerary(String terminalId, String bufferId) {
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			QueryLastItinerary r = new QueryLastItinerary(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TRAVEL_INFO = false;
			int waitSec = 0;
			while(ACCEPT_TRAVEL_INFO==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<请求最近一次行程信息>等待终端返回行程信息的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TRAVEL_INFO = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentRequestTravelInfo(String terminalId, String bufferId,String index) {
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			RequestTravelInfo r = new RequestTravelInfo(terminalId, bufferId, index);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TRAVEL_INFO = false;
			int waitSec = 0;
			while(ACCEPT_TRAVEL_INFO==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<请求某次行程信息>等待终端返回行程信息的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TRAVEL_INFO = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentSetUpdateServerInfo(String terminalId, String bufferId,
			String message) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			SetUpdateServerInfo s = new SetUpdateServerInfo(terminalId, bufferId, message);
			boolean opTemp = s.DBOperation(true);
			byte[] responseBytes = s.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<配置升级服务器>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}

	public boolean SentListen(String terminalId, String bufferId, String message) {
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			Listen l = new Listen(terminalId, bufferId, message);
			boolean opTemp = l.DBOperation(true);
			byte[] responseBytes = l.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<监听>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentInstallPositionLearning(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			InstallPositionLearning i = new InstallPositionLearning(terminalId, bufferId);
			boolean opTemp = i.DBOperation(true);
			byte[] responseBytes = i.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<位置学习>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentRebootTerminal(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			RebootTerminal r = new RebootTerminal(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<重启终端>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentClearBlindSpotData(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			ClearBlindSpotData c = new ClearBlindSpotData(terminalId, bufferId);
			boolean opTemp = c.DBOperation(true);
			byte[] responseBytes = c.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<清除盲点数据>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentSetArmOrDisarm(String terminalId,String bufferId,String message){
		try{
			os = socket.getOutputStream();
			SetArmOrDisarm s = new SetArmOrDisarm(terminalId, bufferId, message);
			boolean opTemp = s.DBOperation(true);
			byte[] responseBytes = s.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<设置布防撤防>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public boolean SentRestoreFactorySettings(String terminalId,String bufferId){
		try{
			os = socket.getOutputStream();
			RestoreFactorySettings r = new RestoreFactorySettings(terminalId, bufferId);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
			ACCEPT_TERMINAL_ACK = false;
			int waitSec = 0;
			while(ACCEPT_TERMINAL_ACK==false){
				Thread.sleep(1000);
				waitSec++;
				logger.info("<恢复出厂设置>等待终端Ack的时间:" + waitSec + "s");
				if(waitSec>WAIT_SECOND){
					return false;
				}
			}
			ACCEPT_TERMINAL_ACK = false;
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	public void SentSendAGPSDatatime(String terminalId,String bufferId,String characters[]){
		try{
			os = socket.getOutputStream();
			SendAGPSDatatime s = new SendAGPSDatatime(terminalId, bufferId,characters);
			boolean opTemp = s.DBOperation(true);
			byte[] responseBytes = s.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SentCheckAGPSDataTime(String terminalId,String bufferId,String message){
		try{
			os = socket.getOutputStream();
			CheckAGPSDataTime c = new CheckAGPSDataTime(terminalId, bufferId,message);
			boolean opTemp = c.DBOperation(true);
			byte[] responseBytes = c.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SentRollCall(String terminalId, String bufferId, String message) {
		// TODO Auto-generated method stub
		try{
			os = socket.getOutputStream();
			RollCall r = new RollCall(terminalId, bufferId, message);
			boolean opTemp = r.DBOperation(true);
			byte[] responseBytes = r.replyToClient();
			if(responseBytes!=null&&opTemp)
				writeToOutputStream(responseBytes);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private synchronized void writeToOutputStream(byte[] message){
		try {
			this.os.write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			MessageUtil.printAndToDivContent("终端连接地址：" + terminalAddress, true);
			ThreadMap.threadNameMap.put(terminalAddress.toString().split(":")[0], this);
			logger.info("HashMap size:" + ThreadMap.threadNameMap.size());
			is = socket.getInputStream();
			os = socket.getOutputStream();

			@SuppressWarnings("unused")
			int recvMsgSize;

			byte[] receiveBuf = new byte[BUFFERSIZE];
			while ((recvMsgSize = is.read(receiveBuf)) != -1) {
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < receiveBuf.length; i++) {
					stringBuilder.append(String.format("%02x", receiveBuf[i]));
				}
				receiveBuf = new byte[BUFFERSIZE];
				MessageUtil.printAndToDivContent("读取终端参数：" + stringBuilder, true);
				String str = stringBuilder.toString();
				str = str.substring(str.indexOf("aa")+2);
				String clientId = str.substring(0,20);
				TerminalServerService t = (TerminalServerService) ThtApplicationContext.getBean("terminalServerServiceImpl");
				t.updateTerminalInfo(clientId, terminalAddress.toString().substring(1));
				str = str.substring(0,str.lastIndexOf("aa"));
				str = str.replaceAll("aaaaa", "%aaaa");
				String[] messages = str.split("aaaa");
				for(int i=0;i<messages.length;i++){
					messages[i] = messages[i].replaceAll("%", "a");
					System.out.println("Message:" + messages[i]);
					try {
						String operationId = messages[i].substring(26,30);
						IdPropertiesUtil p = new IdPropertiesUtil();
						String propertyName = p.getProtocolById(operationId);
						if(propertyName!=null&&!"".equals(propertyName)){
							Constructor con = Class.forName("com.fix.obd.protocol.impl." + propertyName).getConstructor(String.class);
							ODBProtocol odbProtocol = (ODBProtocol) con.newInstance(messages[i]);
							boolean opTemp = odbProtocol.DBOperation(true);
							byte[] responseBytes = odbProtocol.replyToClient();
							if(responseBytes!=null&&opTemp)
								writeToOutputStream(responseBytes);
							if(propertyName.equals("ParameterQueryResponse"))
								this.ACCEPT_QUERY_PARAMETER = true;
							if(propertyName.equals("TerminalAck"))
								this.ACCEPT_TERMINAL_ACK = true;
							if(propertyName.equals("UploadDTC"))
								this.ACCEPT_DTC =true;
							if(propertyName.equals("DTCStatus"))
								this.ACCEPT_DTC_STATUS = true;
							if(propertyName.equals("UploadOBDInfo")||propertyName.equals("UploadOBDInfo_CWKJ"))
								this.ACCEPT_OBD_INFO = true;
							if(propertyName.equals("UploadTravelInfo"))
								this.ACCEPT_TRAVEL_INFO = true;

						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
