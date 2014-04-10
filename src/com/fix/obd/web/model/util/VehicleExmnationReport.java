package com.fix.obd.web.model.util;

import java.util.ArrayList;

public class VehicleExmnationReport {
	private int vehicle_exm_score;
	private String vehicle_exm_main_solution;
	private String vehicle_errors;
	public int getVehicle_exm_score() {
		return vehicle_exm_score;
	}
	public void setVehicle_exm_score(int vehicle_exm_score) {
		this.vehicle_exm_score = vehicle_exm_score;
	}
	public String getVehicle_exm_main_solution() {
		return vehicle_exm_main_solution;
	}
	public void setVehicle_exm_main_solution(String vehicle_exm_main_solution) {
		this.vehicle_exm_main_solution = vehicle_exm_main_solution;
	}
	public String getVehicle_errors() {
		return vehicle_errors;
	}
	public void setVehicle_errors(String vehicle_errors) {
		this.vehicle_errors = vehicle_errors;
	}
}
