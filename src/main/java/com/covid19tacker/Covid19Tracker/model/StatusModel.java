package com.covid19tacker.Covid19Tracker.model;

public class StatusModel {
	
	String country; 
	String state; 
	String latitude; 
	String longitude; 
	String date; 
	String confirmed; 
	String death;
	
	
	public StatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public StatusModel(String country, String state, String latitude, String longitude, String date, String confirmed,
			String death) {
		super();
		this.country = country;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.confirmed = confirmed;
		this.death = death;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getConfirmed() {
		return confirmed;
	}


	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}


	public String getDeath() {
		return death;
	}


	public void setDeath(String death) {
		this.death = death;
	}


	@Override
	public String toString() {
		return "StatusModel [country=" + country + ", state=" + state + ", latitude=" + latitude + ", longitude="
				+ longitude + ", date=" + date + ", confirmed=" + confirmed + ", death=" + death + "]";
	} 
	
	

}
