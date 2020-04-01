package com.covid19tacker.Covid19Tracker.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19tacker.Covid19Tracker.Service.Covid19Service;
import com.covid19tacker.Covid19Tracker.Service.TimeSeriesGlobalService;
import com.covid19tacker.Covid19Tracker.Service.TimeSeriesService;
import com.covid19tacker.Covid19Tracker.model.StatsModel;


@RestController
public class Covid19Controller {

	@Autowired
	Covid19Service service;
	
	@Autowired
	TimeSeriesService timeservice;
	
	@Autowired
	TimeSeriesGlobalService timeseriesGlobal;
	
	@GetMapping("/")
	public String statusCheck()
	{
		return "Services are working fine";
	}
	
	@GetMapping("/dailyTrendData")
	public List<StatsModel> getData(@RequestParam String country) throws IOException
	{
		List<StatsModel> liValue = service.getDailyTrendData(country);
		return liValue;	
	}
	@GetMapping("/timeseries")
	public List<Map<String, String>> getDataFromGoogle()
	{
		return timeservice.getTimeSeries();
	}
	
	@GetMapping("timeseries/global")
	public List<Map<String, String>> getTrendDataGlobal()
	{
		try {
			return timeseriesGlobal.getData();
		} catch (IOException e) {
		return null;
		}
	}
}
