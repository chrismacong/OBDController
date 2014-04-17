package com.fix.obd.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.TravelExmnationDao;
import com.fix.obd.web.dao.TravelInfoDao;
import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.service.TravelExmnationService;
@Component
public class TravelExmnationServiceImpl implements TravelExmnationService{
	private String lineOfDate;
	@Resource
	private TravelInfoDao travelInfoDao;

	public TravelInfoDao getTravelInfoDao() {
		return travelInfoDao;
	}

	public void setTravelInfoDao(TravelInfoDao travelInfoDao) {
		this.travelInfoDao = travelInfoDao;
	}

	@Resource
	private TravelExmnationDao travelExmnationDao;

	public TravelExmnationDao getTravelExmnationDao() {
		return travelExmnationDao;
	}

	public void setTravelExmnationDao(TravelExmnationDao travelExmnationDao) {
		this.travelExmnationDao = travelExmnationDao;
	}

	private TravelExmnationServiceImpl(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		String monthStr = (""+month).length()==2?""+month:"0"+month;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String dayStr = (""+day).length()==2?""+day:"0"+day;
		lineOfDate = year + "-" + monthStr + "-" + dayStr + " 00:00:00";
	}
	@Override
	public String getTotalDistance(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalDistance = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("����")>-1){
						String temp = infoStr.substring(infoStr.indexOf("����"));
						temp = temp.split(";")[1];
						System.out.println(temp);
						totalDistance += Integer.parseInt(temp);
					}
				}
				return totalDistance + "";
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
	public String getLongestDistance(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int longestDistance = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("����")>-1){
						String temp = infoStr.substring(infoStr.indexOf("����"));
						temp = temp.split(";")[1];
						int distance = Integer.parseInt(temp);
						if(distance>longestDistance)
							longestDistance = distance;
					}
				}
				return longestDistance + "";
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
	public String getMaxSpeed(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int maxSpeed = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("����ٶ�")>-1){
						String temp = infoStr.substring(infoStr.indexOf("����ٶ�"));
						temp = temp.split(";")[1];
						int speed = Integer.parseInt(temp);
						if(speed>maxSpeed)
							maxSpeed = speed;
					}
				}
				return maxSpeed + "";
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
	public String getTotalExceedSeconds(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalExceedSeconds = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("��ʱʱ��")>-1){
						String temp = infoStr.substring(infoStr.indexOf("��ʱʱ��"));
						temp = temp.split(";")[1];
						totalExceedSeconds += Integer.parseInt(temp);
					}
				}
				return totalExceedSeconds + "";
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
	public String getTotalBrakeTimes(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalBrakeTimes = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("��ɲ������")>-1){
						String temp = infoStr.substring(infoStr.indexOf("��ɲ������"));
						temp = temp.split(";")[1];
						totalBrakeTimes += Integer.parseInt(temp);
					}
				}
				return totalBrakeTimes + "";
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
	public String getTotalEmerBrakeTimes(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalEmerBrakeTimes = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("����ɲ������")>-1){
						String temp = infoStr.substring(infoStr.indexOf("����ɲ������"));
						temp = temp.split(";")[1];
						totalEmerBrakeTimes += Integer.parseInt(temp);
					}
				}
				return totalEmerBrakeTimes + "";
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
	public String getTotalSpeedUpTimes(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalSpeedUpTimes = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("�����ٴ���")>-1){
						String temp = infoStr.substring(infoStr.indexOf("�����ٴ���"));
						temp = temp.split(";")[1];
						totalSpeedUpTimes += Integer.parseInt(temp);
					}
				}
				return totalSpeedUpTimes + "";
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
	public String getTotalEmerSpeedUpTimes(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalEmerSpeedUpTimes = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("�������ٴ���")>-1){
						String temp = infoStr.substring(infoStr.indexOf("�������ٴ���"));
						temp = temp.split(";")[1];
						totalEmerSpeedUpTimes += Integer.parseInt(temp);
					}
				}
				return totalEmerSpeedUpTimes + "";
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
	public String getAvgSpeed(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int avgSpeed = 0;
			int totalSpeed = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("ƽ���ٶ�")>-1){
						String temp = infoStr.substring(infoStr.indexOf("ƽ���ٶ�"));
						temp = temp.split(";")[1];
						totalSpeed += Integer.parseInt(temp);
					}
				}
				avgSpeed = totalSpeed/info_list.size();
				return avgSpeed + "";
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
	public String getMaxWaterTmp(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int maxWaterTmp = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("���������ˮ��")>-1){
						String temp = infoStr.substring(infoStr.indexOf("���������ˮ��"));
						temp = temp.split(";")[1];
						int waterTmp = Integer.parseInt(temp);
						if(waterTmp>maxWaterTmp)
							maxWaterTmp = waterTmp;
					}
				}
				return maxWaterTmp + "";
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
	public String getMaxRevolution(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int maxRevolution = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("���������ת��")>-1){
						String temp = infoStr.substring(infoStr.indexOf("���������ת��"));
						temp = temp.split(";")[1];
						int revolution = Integer.parseInt(temp);
						if(revolution>maxRevolution)
							maxRevolution = revolution;
					}
				}
				return maxRevolution + "";
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
	public String getTotalOilExpend(String terminalId) {
		// TODO Auto-generated method stub
		try {
			double totalOilExpend = 0.00;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("���ͺ�")>-1){
						String temp = infoStr.substring(infoStr.indexOf("���ͺ�"));
						temp = temp.split(";")[1];
						totalOilExpend += Double.parseDouble(temp)*0.01;
					}
				}
				return totalOilExpend + "";
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
	public String getAvgOilExpend(String terminalId) {
		// TODO Auto-generated method stub
		try {
			double avgOilExpend = 0.00;
			double totalAvgOilExpend = 0.00;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("ƽ���ͺ�")>-1){
						String temp = infoStr.substring(infoStr.indexOf("ƽ���ͺ�"));
						temp = temp.split(";")[1];
						totalAvgOilExpend += Double.parseDouble(temp)*0.01;
					}
				}
				avgOilExpend = totalAvgOilExpend/info_list.size();
				return avgOilExpend + "";
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
	public String getTotalTiredDrivingMinutes(String terminalId) {
		// TODO Auto-generated method stub
		try {
			int totalTiredDrivingMinutes = 0;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("ƣ�ͼ�ʻʱ��")>-1){
						String temp = infoStr.substring(infoStr.indexOf("ƣ�ͼ�ʻʱ��"));
						temp = temp.split(";")[1];
						totalTiredDrivingMinutes += Integer.parseInt(temp);
					}
				}
				return totalTiredDrivingMinutes + "";
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//	public static void main(String args[]){
	//		TravelExmnationService t = new TravelExmnationServiceImpl();
	//	}

	@Override
	public TravelExmnation exmnationAndRecord(String terminalId) {
		// TODO Auto-generated method stub
		TravelExmnation t = new TravelExmnation();
		t.setTerminalId(terminalId);
		t.setTotalDistance(this.getTotalDistance(terminalId));
		t.setLongestDistance(this.getLongestDistance(terminalId));
		t.setMaxSpeed(this.getMaxSpeed(terminalId));
		t.setTotalExceedSeconds(this.getTotalExceedSeconds(terminalId));
		t.setTotalBrakeTimes(this.getTotalBrakeTimes(terminalId));
		t.setTotalEmerBrakeTimes(this.getTotalEmerBrakeTimes(terminalId));
		t.setTotalSpeedUpTimes(this.getTotalSpeedUpTimes(terminalId));
		t.setTotalEmerSpeedUpTimes(this.getTotalEmerSpeedUpTimes(terminalId));
		t.setAvgSpeed(this.getAvgSpeed(terminalId));
		t.setMaxWaterTmp(this.getMaxWaterTmp(terminalId));
		t.setMaxRevolution(this.getMaxRevolution(terminalId));
		t.setTotalOilExpend(this.getTotalOilExpend(terminalId));
		t.setAvgOilExpend(this.getAvgOilExpend(terminalId));
		t.setTotalTiredDrivingMinutes(this.getTotalTiredDrivingMinutes(terminalId));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		t.setDate(now);
		travelExmnationDao.addTravelExmnation(t);
		return t;
	}

	@Override
	public Map exmnationScoreAmongFriends(String terminalId) {
		// TODO Auto-generated method stub
		final String OIL_TERRIFIC = "����ʡ�ʹ��ˣ��������Ʒǳ���ɫ���Ǻ���Ȧ�еĽ�Լ�ȷ棡";
		final String OIL_GOOD = "���԰����ͺ����ŽϺõĿ��ƣ���������֡�";
		final String OIL_NORMAL = "�����ͺĽϸߣ�Ϊ�˲������ͳ������Ǯ������ʱ����Ƽ�ʻϰ������";

		final String MILE_TERRIFIC = "������飬������·��������ļ�ʻ���������ͣ�";
		final String MILE_GOOD = "���������Žϸߵ���ʻ���룬���г����ͬʱ����Ҫ�����г���ȫŶ~";
		final String MILE_NORMAL = "�����ü�į���쿪������ȥ����ɣ�";

		final String STABILITY_TERRIFIC = "���ļ�ʻ��������һ��ݳ�ƽ�ȣ����������һ����ʦ�ˣ�";
		final String STABILITY_GOOD = "���ļ�ʻ�����ܲ���ƽ���г���һ·ƽ��.";
		final String STABILITY_NORMAL = "̫��ļ����ٺͼ�ɲ��������ѹ��ɽ��~";

		final String SPEED_TERRIFIC = "���Ƿɳ�һ�壬ϲ�����ٶ��磬����Ҫ���˼�ʻ��ȫ�ޣ�";
		final String SPEED_GOOD = "�ٶȲ������ǿ���һ�ȣ���ȫ��һ��";
		final String SPEED_NORMAL = "���İ������Ժ��ڹ������ˣ�";

		final String TIRED_TERRIFIC = "���ܺõؿ����˼ݳ�ʱ�䣬���������";
		final String TIRED_GOOD = "ע��ƣ�ͼ�ʻ��ͣ������һ�ͷ羰�ɣ�";
		final String TIRED_NORMAL = "����������ƣ�ͼ�ʻ�������Ϊ�����İ�ȫ������ؽ����г�";
		Map map = new HashMap();
		try {
			ArrayList<TravelExmnation> listBesidesSelf = new ArrayList<TravelExmnation>();
			List<TravelExmnation> list = travelExmnationDao.findByHQL("from TravelExmnation order by date desc");
			for(int i=0;i<list.size();i++){
				if(!list.get(i).getTerminalId().equals(terminalId)){
					if(list.get(i).getAvgOilExpend()!=null)
						listBesidesSelf.add(list.get(i));
				}
			}
			for(int i=0;i<listBesidesSelf.size();i++){
				for(int j=listBesidesSelf.size()-1;j>i;j--){
					if(listBesidesSelf.get(i).getTerminalId().equals(listBesidesSelf.get(j).getTerminalId()))
						listBesidesSelf.remove(j);
				}
			}
			//			for(int i=0;i<listBesidesSelf.size();i++){
			//				System.out.println(listBesidesSelf.get(i).getDate() + " " + listBesidesSelf.get(i).getTerminalId() + " " + listBesidesSelf.get(i).getTotalDistance());
			//			}
			//�ͺ��ں����е�����
			int oilCount = 0;
			if(this.getAvgOilExpend(terminalId)!=null){
				double myOil = Double.parseDouble(this.getAvgOilExpend(terminalId));
				for(int i=0;i<listBesidesSelf.size();i++){
					double hisOil = Double.parseDouble(listBesidesSelf.get(i).getAvgOilExpend());
					if(myOil<=hisOil)
						oilCount++;
				}
			}
			int oilScore = 100*(oilCount+1)/(listBesidesSelf.size()+1);
			map.put("oil_score", oilScore);
			if(oilScore>=75)
				map.put("oil_text", OIL_TERRIFIC);
			else if(oilScore>=50)
				map.put("oil_text", OIL_GOOD);
			else
				map.put("oil_text", OIL_NORMAL);

			//����ں����е�����
			int mileCount = 0;
			if(this.getTotalBrakeTimes(terminalId)!=null){
				double myMile = Double.parseDouble(this.getTotalDistance(terminalId));
				for(int i=0;i<listBesidesSelf.size();i++){
					double hisMile = Double.parseDouble(listBesidesSelf.get(i).getTotalDistance());
					if(myMile>=hisMile)
						mileCount++;
				}
			}
			int mileScore = 100*(mileCount+1)/(listBesidesSelf.size()+1);
			map.put("mile_score", mileScore);
			if(mileScore>=75)
				map.put("mile_text", MILE_TERRIFIC);
			else if(mileScore>=50)
				map.put("mile_text", MILE_GOOD);
			else
				map.put("mile_text", MILE_NORMAL);

			//�ȶ����ں����е�����
			int stabilityCount = 0;
			/*---ɲ�������ٵ�Ȩֵ---*/
			final int NORMAL_OP = 1;
			/*---��ɲ���������ٵ�Ȩֵ---*/
			final int EXTRA_OP = 2;
			if(this.getTotalBrakeTimes(terminalId)!=null){
				int myStabilily = Integer.parseInt(this.getTotalBrakeTimes(terminalId))*NORMAL_OP + 
						Integer.parseInt(this.getTotalEmerBrakeTimes(terminalId))*EXTRA_OP + 
						Integer.parseInt(this.getTotalSpeedUpTimes(terminalId))*NORMAL_OP + 
						Integer.parseInt(this.getTotalEmerSpeedUpTimes(terminalId))*EXTRA_OP;
				for(int i=0;i<listBesidesSelf.size();i++){
					int hisStabilily = Integer.parseInt(listBesidesSelf.get(i).getTotalBrakeTimes())*NORMAL_OP + 
							Integer.parseInt(listBesidesSelf.get(i).getTotalEmerBrakeTimes())*EXTRA_OP + 
							Integer.parseInt(listBesidesSelf.get(i).getTotalSpeedUpTimes())*NORMAL_OP + 
							Integer.parseInt(listBesidesSelf.get(i).getTotalEmerSpeedUpTimes())*EXTRA_OP;
					if(myStabilily<=hisStabilily)
						stabilityCount++;
				}
			}
			int stabilityScore = 100*(stabilityCount+1)/(listBesidesSelf.size()+1);
			map.put("stability_score", stabilityScore);
			if(stabilityScore>=75)
				map.put("stability_text", STABILITY_TERRIFIC);
			else if(stabilityScore>=50)
				map.put("stability_text", STABILITY_GOOD);
			else
				map.put("stability_text", STABILITY_NORMAL);

			//�ٶ��ں����е�����
			int speedCount = 0;
			if(this.getAvgSpeed(terminalId)!=null){
				double mySpeed = Double.parseDouble(this.getAvgSpeed(terminalId));
				for(int i=0;i<listBesidesSelf.size();i++){
					double hisSpeed = Double.parseDouble(listBesidesSelf.get(i).getAvgSpeed());
					if(mySpeed>=hisSpeed)
						speedCount++;
				}
			}
			int speedScore = 100*(speedCount+1)/(listBesidesSelf.size()+1);
			map.put("speed_score", speedScore);
			if(speedScore>=75)
				map.put("speed_text", SPEED_TERRIFIC);
			else if(speedScore>=50)
				map.put("speed_text", SPEED_GOOD);
			else
				map.put("speed_text", SPEED_NORMAL);

			//ƣ�Ϳ����ں����е�����
			int tiredControlCount = 0;
			if(this.getTotalBrakeTimes(terminalId)!=null){
				int myTiredControl = Integer.parseInt(this.getTotalTiredDrivingMinutes(terminalId));
				for(int i=0;i<listBesidesSelf.size();i++){
					int hisTiredControl = Integer.parseInt(listBesidesSelf.get(i).getTotalTiredDrivingMinutes());
					if(myTiredControl<=hisTiredControl)
						tiredControlCount++;
				}
			}
			int tiredControlScore = 100*(tiredControlCount+1)/(listBesidesSelf.size()+1);
			map.put("tired_control_score", tiredControlScore);
			if(tiredControlScore>=75)
				map.put("tired_control_text", TIRED_TERRIFIC);
			else if(tiredControlScore>=50)
				map.put("tired_control_text", TIRED_GOOD);
			else
				map.put("tired_control_text", TIRED_NORMAL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return map;
	}
}
