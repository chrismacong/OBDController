package com.fix.obd.web.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.fix.obd.web.model.util.TodayTravelReport;
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
		List<TravelInfo> info_list = new ArrayList<TravelInfo>();
		try {
			info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' order by SUBSTR(info,27,12) desc");
			if(info_list.size()>0){
				if(info_list.size()==1)
					return info_list;
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
					String start_time = characters[1].split(";")[1];
					resultStr += "开始时间： " + start_time.substring(0,2) + "-" + start_time.substring(2,4) + "-" + start_time.substring(4,6) + " " + start_time.substring(6,8) + ":" + start_time.substring(8,10) + ":" + start_time.substring(10,12) + "<br/>";
					String stop_time = characters[0].split(";")[1];
					resultStr += "结束时间： " + stop_time.substring(0,2) + "-" + stop_time.substring(2,4) + "-" + stop_time.substring(4,6) + " " + stop_time.substring(6,8) + ":" + stop_time.substring(8,10) + ":" + stop_time.substring(10,12) + "<br/>";
					int COUNT_IF_EXCEED_TIME = 0;
					int COUNT_IF_TIRED = 0;
					int COUNT_BRAKE = 0;
					int COUNT_EMER_BRAKE = 0;
					int COUNT_SPEED_UP = 0;
					int COUNT_EMER_SPEED_UP = 0;
					for(int i=2; i<characters.length; i++){
						String[] temps = characters[i].split(";");
						if(temps[0].equals("电压值"))
							resultStr += temps[0] + "： " + Integer.parseInt(temps[1])*0.1 + "V<br/>";
						else if(temps[0].equals("总油耗"))
							resultStr += temps[0] + "： " + Integer.parseInt(temps[1])*0.01 + "升<br/>";
						else if(temps[0].equals("平均油耗"))
							resultStr += temps[0] + "： " + Integer.parseInt(temps[1])*0.01 + "百公里升<br/>";
						else
							resultStr += temps[0] + "： " + temps[1] + temps[2] + "<br/>";
						if(temps[0].equals("超时时长")){
							if(!temps[1].equals("0"))
								COUNT_IF_EXCEED_TIME++;
						}
						if(temps[0].equals("疲劳驾驶时长")){
							if(!temps[1].equals("0"))
								COUNT_IF_TIRED++;
						}
						if(temps[0].equals("急刹车次数")){
							COUNT_BRAKE = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("紧急刹车次数")){
							COUNT_EMER_BRAKE = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("急加速次数")){
							COUNT_SPEED_UP = Integer.parseInt(temps[1]);
						}
						if(temps[0].equals("紧急加速次数")){
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

	//加速、减速分析
	public Map getBrakesAndSpeedUpsByTravel(String terminalId) {
		List<TravelInfo> totalInfo=getTotalTravelInfo(terminalId);
		HashMap<String, Long[]> map=new HashMap<String,Long[]>();
		if(totalInfo!=null){
			List<String> eachTravel=getEachTravel(totalInfo);
			for(String info:eachTravel){
				String[] a=info.split("@");
				String beginTime=getBeginTime(a);
				String endTime=getEndTime(a);
				String speedUp=getSpeedUp(a);
				String brake=getBrake(a);
				long mill=getTimeDiff(beginTime,endTime);
				long speedUpPerHour=toPerHour(speedUp,mill);
				long brakePerHour=toPerHour(brake,mill);
				map.put(beginTime+"-"+endTime, new Long[]{speedUpPerHour,brakePerHour});
			}
		}
		return map;
	}

	private List<TravelInfo> getTotalTravelInfo(String terminalId) {
		try {
			String hql="from TravelInfo where tid ='"+terminalId+"'";
			return travelInfoDao.findByHQL(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<String> getEachTravel(List<TravelInfo> totalInfo) {
		List<String> eachTravelInfo = new ArrayList<String>();
		for(TravelInfo info:totalInfo){
			eachTravelInfo.add(info.getInfo());
		}
		return eachTravelInfo;
	}

	private String getSpeedUp(String[] list) {
		String speedup=list[7];
		return speedup.split(";")[1];
	}

	private String getBrake(String[] list) {
		String brake=list[5];
		return brake.split(";")[1];
	}

	private String getBeginTime(String[] list) {
		String beginStr=list[1];
		return beginStr.split(";")[1];
	}

	private String getEndTime(String[] list) {
		String endStr=list[0];
		return endStr.split(";")[1];
	}

	private long getTimeDiff(String begin,String end){
		SimpleDateFormat df=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		try {
			Date beginDate=df.parse(begin);
			Date endDate=df.parse(end);
			Calendar cbegin = Calendar.getInstance();
			cbegin.setTime(beginDate);
			Calendar cend = Calendar.getInstance();
			cend.setTime(endDate);
			long diff=cend.getTimeInMillis() - cbegin.getTimeInMillis();
			return diff;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public long toPerHour(String num, long mill) {
		int n=Integer.parseInt(num);
		return n*60*60/mill;
	}

	@Override
	public TodayTravelReport getTodayTravelReport(String terminalId) {
		// TODO Auto-generated method stub
		TodayTravelReport ttr = new TodayTravelReport();
		try {
			Calendar calendar = Calendar.getInstance();
			String year = (calendar.get(Calendar.YEAR)+"").substring(2);
			String month = calendar.get(Calendar.MONTH)+1+"";
			month = month.length()==1?"0"+month:month;
			String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
			day = day.length()==1?"0"+day:day;
			String today_date = year+month+day;
			List<TravelInfo> info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and SUBSTR(info,27,6)='" + today_date + "'");

			int today_distance = 0;
			double today_total_oil= 0.00;
			int today_total_time= 0;
			int today_max_speed= 0;
			int today_brake_times= 0;
			int today_emer_brake_times= 0;
			int today_speedup_times= 0;
			int today_emer_speedup_times= 0;
			int today_travel_times = 0;
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
				today_travel_times = info_list.size();
				for(int i=0;i<info_list.size();i++){
					String infoStr = info_list.get(i).getInfo();
					if(infoStr.indexOf("距离")>-1){
						String temp = infoStr.substring(infoStr.indexOf("距离"));
						temp = temp.split(";")[1];
						today_distance += Integer.parseInt(temp);
					}
					if(infoStr.indexOf("总油耗")>-1){
						String temp = infoStr.substring(infoStr.indexOf("总油耗"));
						temp = temp.split(";")[1];
						today_total_oil += Double.parseDouble(temp)*0.01;
					}
					if(infoStr.indexOf("最大速度")>-1){
						String temp = infoStr.substring(infoStr.indexOf("最大速度"));
						temp = temp.split(";")[1];
						int speed = Integer.parseInt(temp);
						if(speed>today_max_speed)
							today_max_speed = speed;
					}

					if(infoStr.indexOf("急刹车次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急刹车次数"));
						temp = temp.split(";")[1];
						today_brake_times += Integer.parseInt(temp);
					}

					if(infoStr.indexOf("紧急刹车次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("紧急刹车次数"));
						temp = temp.split(";")[1];
						today_emer_brake_times += Integer.parseInt(temp);
					}

					if(infoStr.indexOf("急加速次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急加速次数"));
						temp = temp.split(";")[1];
						today_speedup_times += Integer.parseInt(temp);
					}

					if(infoStr.indexOf("紧急加速次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("紧急加速次数"));
						temp = temp.split(";")[1];
						today_emer_speedup_times += Integer.parseInt(temp);
					}

					String start_date_str = infoStr.split("@")[1].split(";")[1];
					String end_date_str = infoStr.split("@")[0].split(";")[1];
					if(start_date_str.length()==12){
						String start_date_format_str = start_date_str.substring(0,2) + "-" + start_date_str.substring(2,4) + "-" + start_date_str.substring(4,6) + " " + start_date_str.substring(6,8) + ":" + start_date_str.substring(8,10) + ":" + start_date_str.substring(10,12);
						String end_date_format_str = end_date_str.substring(0,2) + "-" + end_date_str.substring(2,4) + "-" + end_date_str.substring(4,6) + " " + end_date_str.substring(6,8) + ":" + end_date_str.substring(8,10) + ":" + end_date_str.substring(10,12);
						SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
						Date start_date = sdf.parse(start_date_format_str);
						Date end_date = sdf.parse(end_date_format_str);
						long s = end_date.getTime() - start_date.getTime();
						today_total_time += s/60000;
					}
				}
			}
			if(today_distance>0)
				ttr.setToday_avg_oil((today_total_oil*1.00/today_distance*100.00) + "");
			else
				ttr.setToday_avg_oil("0");
			ttr.setToday_travel_times(today_travel_times+"");
			ttr.setToday_distance(today_distance+"");
			ttr.setToday_total_oil(today_total_oil+"");
			ttr.setToday_max_speed(today_max_speed+"");
			ttr.setToday_total_time(today_total_time+"");
			if(today_total_time>0)
				ttr.setToday_avg_speed((today_distance*60.00/today_total_time) + "");
			else
				ttr.setToday_avg_speed("0");
			ttr.setToday_brake_times(today_brake_times + "");
			ttr.setToday_emer_brake_times(today_emer_brake_times + "");
			ttr.setToday_speedup_times(today_speedup_times + "");
			ttr.setToday_emer_speedup_times(today_emer_speedup_times + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ttr;
	}

	@Override
	public List<TravelInfo> getTravelInfoBetweenTime(String terminalId,
			String from_time_point, String to_time_point) {
		// TODO Auto-generated method stub
		List<TravelInfo> info_list = new ArrayList<TravelInfo>();
		try {
			info_list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + terminalId + "' and SUBSTR(info,27,12)<='" + to_time_point + "' and SUBSTR(info,27,12)>='" + from_time_point + "'");
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info_list;
	}

	@Override
	public Map getTravelInfoMapByDateForMobileStatistics(String terminalId,
			String datestr) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		try {
			Calendar now = Calendar.getInstance();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df_ed = new SimpleDateFormat("yyMMddHHmmss");
			Date date = df.parse(datestr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String startdate;
			String enddate;
			int days;
			if(calendar.get(Calendar.YEAR)==now.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==now.get(Calendar.MONTH)){
				Calendar start_calendar = calendar;
				start_calendar.set(Calendar.DAY_OF_MONTH, 1);
				startdate = df.format(start_calendar.getTime());
				enddate = datestr;
				days = now.get(Calendar.DAY_OF_MONTH);
			}
			else{
				Calendar start_calendar = calendar;
				start_calendar.set(Calendar.DAY_OF_MONTH, 1);
				startdate = df.format(start_calendar.getTime());
				Calendar end_calendar = calendar;
				end_calendar.set(Calendar.DAY_OF_MONTH, end_calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				enddate = df.format(end_calendar.getTime());
				days = end_calendar.get(Calendar.DAY_OF_MONTH);
			}

			Date date_count = df.parse(startdate);
			Calendar calendar_count = Calendar.getInstance();
			calendar_count.setTime(date_count);
			int[] distance_per_day = new int[days];
			String[] oilspend_per_day = new String[days];
			int[] oilspend_per_day_int = new int[days];
			int[] brake_times_per_day = new int[days];
			int[] speedup_times_per_day = new int[days];
			int[] driving_minutes_per_day = new int[days];
			String[] average_oilspend_per_day = new String[days];
			for(int i=0;i<average_oilspend_per_day.length;i++)
				average_oilspend_per_day[i] = "0";
			String[] average_speed_per_day = new String[days];
			for(int i=0;i<average_speed_per_day.length;i++)
				average_speed_per_day[i] = "0";
			for(int i=1;i<=days;i++){
				calendar_count.set(calendar.DAY_OF_MONTH,i);
				Calendar calendar_start = calendar_count;
				calendar_start.set(Calendar.HOUR_OF_DAY, 0);
				calendar_start.set(Calendar.MINUTE,0);
				calendar_start.set(Calendar.SECOND, 0);
				String date_count_start = df_ed.format(calendar_start.getTime());
				Calendar calendar_end = calendar_count;
				calendar_end.set(Calendar.HOUR, 23);
				calendar_end.set(Calendar.MINUTE,59);
				calendar_end.set(Calendar.SECOND, 59);
				String date_count_end = df_ed.format(calendar_end.getTime());
				
				List<TravelInfo> list = this.getTravelInfoBetweenTime(terminalId, date_count_start, date_count_end);
				System.out.println(list.size());
				System.out.println(date_count_start + "   " + date_count_end);
				for(int j=0;j<list.size();j++){
					String infoStr = list.get(j).getInfo();

					//获取在时间段内每一天的所有驾驶总距离
					if(infoStr.indexOf("距离")>-1){
						String temp = infoStr.substring(infoStr.indexOf("距离"));
						temp = temp.split(";")[1];
						distance_per_day[i-1] += Integer.parseInt(temp);
					}

					//获取在时间段内每一天的所有驾驶总油耗
					if(infoStr.indexOf("总油耗")>-1){
						String temp = infoStr.substring(infoStr.indexOf("总油耗"));
						temp = temp.split(";")[1];
						oilspend_per_day_int[i-1] += Integer.parseInt(temp);
					}

					//获取在时间段内每一天的所有驾驶总急刹车次数
					if(infoStr.indexOf("急刹车次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急刹车次数"));
						temp = temp.split(";")[1];
						brake_times_per_day[i-1] += Integer.parseInt(temp);
					}

					//获取在时间段内每一天的所有驾驶总急加速次数
					if(infoStr.indexOf("急加速次数")>-1){
						String temp = infoStr.substring(infoStr.indexOf("急加速次数"));
						temp = temp.split(";")[1];
						speedup_times_per_day[i-1] += Integer.parseInt(temp);
					}

					//获取在时间段内每一天的所有驾驶总时间（min）
					String start_date_str = infoStr.split("@")[1].split(";")[1];
					String end_date_str = infoStr.split("@")[0].split(";")[1];
					String start_date_format_str = start_date_str.substring(0,2) + "-" + start_date_str.substring(2,4) + "-" + start_date_str.substring(4,6) + " " + start_date_str.substring(6,8) + ":" + start_date_str.substring(8,10) + ":" + start_date_str.substring(10,12);
					String end_date_format_str = end_date_str.substring(0,2) + "-" + end_date_str.substring(2,4) + "-" + end_date_str.substring(4,6) + " " + end_date_str.substring(6,8) + ":" + end_date_str.substring(8,10) + ":" + end_date_str.substring(10,12);
					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
					Date start_date = sdf.parse(start_date_format_str);
					Date end_date = sdf.parse(end_date_format_str);
					long s = end_date.getTime() - start_date.getTime();
					driving_minutes_per_day[i-1] += s/60000;
				}


				DecimalFormat    df2   = new DecimalFormat("######0.00");   
				//获取在时间段内每一天的平均油耗
				if(distance_per_day[i-1]>0)
					average_oilspend_per_day[i-1] =df2.format(((double)oilspend_per_day_int[i-1])/((double)distance_per_day[i-1]));
				
				//获取在时间段内每一天的平均速度
				if(driving_minutes_per_day[i-1]>0)
					average_speed_per_day[i-1] = df2.format(((double)distance_per_day[i-1])/((double)driving_minutes_per_day[i-1])*60.00);
				
				oilspend_per_day[i-1] = df2.format(((double)oilspend_per_day_int[i-1]) * 0.01);
			}
			map.put("distance_per_day", distance_per_day);
			map.put("oilspend_per_day",oilspend_per_day);
			map.put("brake_times_per_day", brake_times_per_day);
			map.put("speedup_times_per_day", speedup_times_per_day);
			map.put("average_oilspend_per_day", average_oilspend_per_day);
			map.put("average_speed_per_day", average_speed_per_day);
			map.put("driving_minutes_per_day", driving_minutes_per_day);
			return map;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}