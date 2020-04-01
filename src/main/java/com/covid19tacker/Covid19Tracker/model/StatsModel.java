package com.covid19tacker.Covid19Tracker.model;

public class StatsModel {
	int id;
	String country_name;
	String total_cases;
	String new_cases;
	String active_cases;
	String total_deaths;
	String new_deaths;
	String total_recovered;
	String serious_critical;
	String region;
	String total_cases_per1m;
	String record_date;
	
	public StatsModel() {
	}

	public StatsModel(int id, String country_name, String total_cases, String new_cases, String active_cases, String total_deaths,
			String new_deaths, String total_recovered, String serious_critical, String region, String total_cases_per1m,
			String record_date) {
		super();
		this.id = id;
		this.country_name = country_name;
		this.total_cases = total_cases;
		this.new_cases = new_cases;
		this.active_cases = active_cases;
		this.total_deaths = total_deaths;
		this.new_deaths = new_deaths;
		this.total_recovered = total_recovered;
		this.serious_critical = serious_critical;
		this.region = region;
		this.total_cases_per1m = total_cases_per1m;
		this.record_date = record_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getTotal_cases() {
		return total_cases;
	}

	public void setTotal_cases(String total_cases) {
		this.total_cases = total_cases;
	}

	public String getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(String new_cases) {
		this.new_cases = new_cases;
	}

	public String getActive_cases() {
		return active_cases;
	}

	public void setActive_cases(String active_cases) {
		this.active_cases = active_cases;
	}

	public String getTotal_deaths() {
		return total_deaths;
	}

	public void setTotal_deaths(String total_deaths) {
		this.total_deaths = total_deaths;
	}

	public String getNew_deaths() {
		return new_deaths;
	}

	public void setNew_deaths(String new_deaths) {
		this.new_deaths = new_deaths;
	}

	public String getTotal_recovered() {
		return total_recovered;
	}

	public void setTotal_recovered(String total_recovered) {
		this.total_recovered = total_recovered;
	}

	public String getSerious_critical() {
		return serious_critical;
	}

	public void setSerious_critical(String serious_critical) {
		this.serious_critical = serious_critical;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTotal_cases_per1m() {
		return total_cases_per1m;
	}

	public void setTotal_cases_per1m(String total_cases_per1m) {
		this.total_cases_per1m = total_cases_per1m;
	}

	public String getRecord_date() {
		return record_date;
	}

	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	

}
