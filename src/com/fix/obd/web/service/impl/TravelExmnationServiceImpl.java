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
	public String getTotalDistance(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalDistance = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("距离")>-1){
						String temp = infoStr.substring(infoStr.indexOf("距离"));
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
	public String getLongestDistance(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int longestDistance = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("距离")>-1){
						String temp = infoStr.substring(infoStr.indexOf("距离"));
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
	public String getMaxSpeed(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int maxSpeed = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("最大速度")>-1){
						String temp = infoStr.substring(infoStr.indexOf("最大速度"));
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
	public String getTotalExceedSeconds(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalExceedSeconds = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("超时时长")>-1){
						String temp = infoStr.substring(infoStr.indexOf("超时时长"));
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
	public String getTotalBrakeTimes(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalBrakeTimes = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("急刹车次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急刹车次数"));
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
	public String getTotalEmerBrakeTimes(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalEmerBrakeTimes = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("紧急刹车次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("紧急刹车次数"));
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
	public String getTotalSpeedUpTimes(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalSpeedUpTimes = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("急加速次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急加速次数"));
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
	public String getTotalEmerSpeedUpTimes(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalEmerSpeedUpTimes = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("紧急加速次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("紧急加速次数"));
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
	public String getAvgSpeed(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int avgSpeed = 0;
			int totalSpeed = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("平均速度")>-1){
						String temp = infoStr.substring(infoStr.indexOf("平均速度"));
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
	public String getMaxWaterTmp(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int maxWaterTmp = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("发动机最高水温")>-1){
						String temp = infoStr.substring(infoStr.indexOf("发动机最高水温"));
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
	public String getMaxRevolution(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int maxRevolution = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("发动机最高转速")>-1){
						String temp = infoStr.substring(infoStr.indexOf("发动机最高转速"));
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
	public String getTotalOilExpend(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			double totalOilExpend = 0.00;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("总油耗")>-1){
						String temp = infoStr.substring(infoStr.indexOf("总油耗"));
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
	public String getAvgOilExpend(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			if(info_list.size()>0){
				double avgOilExpend = Double.parseDouble(this.getTotalOilExpend(info_list))/Double.parseDouble(this.getTotalDistance(info_list))*100.00;
				return avgOilExpend + "";
			}
			else
				return "0.00";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTotalTiredDrivingMinutes(List<TravelInfo> info_list) {
		// TODO Auto-generated method stub
		try {
			int totalTiredDrivingMinutes = 0;
			if(info_list.size()>0){
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("疲劳驾驶时长")>-1){
						String temp = infoStr.substring(infoStr.indexOf("疲劳驾驶时长"));
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
		try {
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
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
			}
			
			
			t.setTerminalId(terminalId);
			t.setTotalDistance(this.getTotalDistance(info_list));
			t.setLongestDistance(this.getLongestDistance(info_list));
			t.setMaxSpeed(this.getMaxSpeed(info_list));
			t.setTotalExceedSeconds(this.getTotalExceedSeconds(info_list));
			t.setTotalBrakeTimes(this.getTotalBrakeTimes(info_list));
			t.setTotalEmerBrakeTimes(this.getTotalEmerBrakeTimes(info_list));
			t.setTotalSpeedUpTimes(this.getTotalSpeedUpTimes(info_list));
			t.setTotalEmerSpeedUpTimes(this.getTotalEmerSpeedUpTimes(info_list));
			t.setAvgSpeed(this.getAvgSpeed(info_list));
			t.setMaxWaterTmp(this.getMaxWaterTmp(info_list));
			t.setMaxRevolution(this.getMaxRevolution(info_list));
			t.setTotalOilExpend(this.getTotalOilExpend(info_list));
			t.setAvgOilExpend(this.getAvgOilExpend(info_list));
			t.setTotalTiredDrivingMinutes(this.getTotalTiredDrivingMinutes(info_list));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			t.setDate(now);
			travelExmnationDao.addTravelExmnation(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Map exmnationScoreAmongFriends(String terminalId) {
		// TODO Auto-generated method stub
		final String OIL_TERRIFIC = "您是省油达人，油量控制非常出色，是好友圈中的节约先锋！";
		final String OIL_GOOD = "您对爱车油耗有着较好的控制，请继续保持。";
		final String OIL_NORMAL = "您的油耗较高，为了不让汽油抽干您的钱包，是时候改善驾驶习惯啦！";

		final String MILE_TERRIFIC = "读万卷书，行万里路。您最近的驾驶距离出类拔萃！";
		final String MILE_GOOD = "您近期有着较高的行驶距离，旅行出差的同时，不要忘了行车安全哦~";
		final String MILE_NORMAL = "爱车好寂寞，快开着它出去兜风吧！";

		final String STABILITY_TERRIFIC = "您的驾驶技术高人一筹，驾车平稳，可以尊称您一声大师了！";
		final String STABILITY_GOOD = "您的驾驶技术很不错，平稳行车，一路平安.";
		final String STABILITY_NORMAL = "太多的急加速和急刹车，爱车压力山大啊~";

		final String SPEED_TERRIFIC = "您是飞车一族，喜爱高速兜风，但不要忘了驾驶安全噢！";
		final String SPEED_GOOD = "速度不是你的强项，稳一稳，安全第一。";
		final String SPEED_NORMAL = "您的爱车可以和乌龟赛跑了！";

		final String TIRED_TERRIFIC = "您很好地控制了驾车时间，请继续保持";
		final String TIRED_GOOD = "注意疲劳驾驶，停下来赏一赏风景吧！";
		final String TIRED_NORMAL = "您经常出现疲劳驾驶的情况，为了您的安全，请务必健康行车";
		Map map = new HashMap();
		try {
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and date > '" + lineOfDate + "'");
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
			}
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
			//油耗在好友中的排名
			int oilCount = 0;
			if(this.getAvgOilExpend(info_list)!=null){
				double myOil = Double.parseDouble(this.getAvgOilExpend(info_list));
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

			//里程在好友中的排名
			int mileCount = 0;
			if(this.getTotalBrakeTimes(info_list)!=null){
				double myMile = Double.parseDouble(this.getTotalDistance(info_list));
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

			//稳定性在好友中的排名
			int stabilityCount = 0;
			/*---刹车，加速的权值---*/
			final int NORMAL_OP = 1;
			/*---急刹车，急加速的权值---*/
			final int EXTRA_OP = 2;
			if(this.getTotalBrakeTimes(info_list)!=null){
				int myStabilily = Integer.parseInt(this.getTotalBrakeTimes(info_list))*NORMAL_OP + 
						Integer.parseInt(this.getTotalEmerBrakeTimes(info_list))*EXTRA_OP + 
						Integer.parseInt(this.getTotalSpeedUpTimes(info_list))*NORMAL_OP + 
						Integer.parseInt(this.getTotalEmerSpeedUpTimes(info_list))*EXTRA_OP;
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

			//速度在好友中的排名
			int speedCount = 0;
			if(this.getAvgSpeed(info_list)!=null){
				double mySpeed = Double.parseDouble(this.getAvgSpeed(info_list));
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

			//疲劳控制在好友中的排名
			int tiredControlCount = 0;
			if(this.getTotalBrakeTimes(info_list)!=null){
				int myTiredControl = Integer.parseInt(this.getTotalTiredDrivingMinutes(info_list));
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
