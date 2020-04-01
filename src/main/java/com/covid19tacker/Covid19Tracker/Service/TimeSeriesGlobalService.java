package com.covid19tacker.Covid19Tracker.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.covid19tacker.Covid19Tracker.Extract.CasesExtract;

@Service
public class TimeSeriesGlobalService {
	   @Autowired
	   private  RestTemplateBuilder restTemplate;
	   
	public List<Map<String, String>> getData() throws IOException {
		
		CasesExtract ce = new CasesExtract();
		
		List<Map<String, String>> liConfirmedCases=ce.getCases(restTemplate,"Confirm");
		List<Map<String,String>>  liDeathCases = ce.getCases(restTemplate,"Death");
		
		System.out.println(liConfirmedCases.size()+"=="+liDeathCases.size());
		
		
		return liConfirmedCases;
		
	}

}
