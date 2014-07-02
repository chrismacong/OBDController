package com.fix.obd.web.model.util;

public class TodayTravelReport {
	private String today_distance;
	private String today_total_oil;
	private String today_avg_oil;
	private String today_avg_speed;
	private String today_total_time;
	private String today_brake_times;
	private String today_emer_brake_times;
	private String today_speedup_times;
	private String today_emer_speedup_times;
	private String today_max_speed;
	private String today_travel_times;
	

	public String buildReportStr(){
		return this.today_distance + ";" + 
				today_total_oil + ";" + 
				today_avg_oil + ";" + 
				today_avg_speed + ";" + 
				today_total_time + ";" + 
				today_brake_times + ";" + 
				today_emer_brake_times + ";" + 
				today_speedup_times + ";" + 
				today_emer_speedup_times + ";" + 
				today_max_speed + ";" + 
				today_travel_times; 
	}
	public String getToday_travel_times() {
		return today_travel_times;
	}
	public void setToday_travel_times(String today_travel_times) {
		this.today_travel_times = today_travel_times;
	}
	public String getToday_distance() {
		return today_distance;
	}
	public void setToday_distance(String today_distance) {
		this.today_distance = today_distance;
	}
	public String getToday_total_oil() {
		return today_total_oil;
	}
	public void setToday_total_oil(String today_total_oil) {
		this.today_total_oil = today_total_oil;
	}
	public String getToday_avg_oil() {
		return today_avg_oil;
	}
	public void setToday_avg_oil(String today_avg_oil) {
		this.today_avg_oil = today_avg_oil;
	}
	public String getToday_avg_speed() {
		return today_avg_speed;
	}
	public void setToday_avg_speed(String today_avg_speed) {
		this.today_avg_speed = today_avg_speed;
	}
	public String getToday_total_time() {
		return today_total_time;
	}
	public void setToday_total_time(String today_total_time) {
		this.today_total_time = today_total_time;
	}
	public String getToday_brake_times() {
		return today_brake_times;
	}
	public void setToday_brake_times(String today_brake_times) {
		this.today_brake_times = today_brake_times;
	}
	public String getToday_emer_brake_times() {
		return today_emer_brake_times;
	}
	public void setToday_emer_brake_times(String today_emer_brake_times) {
		this.today_emer_brake_times = today_emer_brake_times;
	}
	public String getToday_speedup_times() {
		return today_speedup_times;
	}
	public void setToday_speedup_times(String today_speedup_times) {
		this.today_speedup_times = today_speedup_times;
	}
	public String getToday_emer_speedup_times() {
		return today_emer_speedup_times;
	}
	public void setToday_emer_speedup_times(String today_emer_speedup_times) {
		this.today_emer_speedup_times = today_emer_speedup_times;
	}
	public String getToday_max_speed() {
		return today_max_speed;
	}
	public void setToday_max_speed(String today_max_speed) {
		this.today_max_speed = today_max_speed;
	}
	
}
