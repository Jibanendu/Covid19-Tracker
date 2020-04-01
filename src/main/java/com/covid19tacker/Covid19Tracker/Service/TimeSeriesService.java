package com.covid19tacker.Covid19Tracker.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeSeriesService {
	   @Autowired
	   private RestTemplateBuilder restTemplate;

	public List<Map<String, String>> getTimeSeries() {
		try {
			int count=0;
		
			  
	           HttpHeaders headers = new HttpHeaders();
	           headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	           HttpEntity<String> entity = new HttpEntity<>(headers);
	           ResponseEntity<Resource> response = restTemplate.build()
	                                                         .exchange("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv", HttpMethod.GET, entity, Resource.class);
	           InputStream is = response.getBody().getInputStream();
	           InputStreamReader isReader = new InputStreamReader(is);
	           BufferedReader reader = new BufferedReader(isReader);
	           
	           String str;
	           
	           List<Map<String,String>> finalData = new ArrayList<Map<String,String>>();
        	   Map<String,String> columnNames= new LinkedHashMap<>();
			   List<String> dates = new ArrayList<>();

	           while((str = reader.readLine())!= null){
				  
	        	  if(count==0)
	        	  {
	        		  String[] strArr = str.split(",");
	        		  
	        		  String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
	        		  
	        		  Pattern pattern = Pattern.compile(regex);
	        		   
	        		  for(int i=0;i<strArr.length;i++)
	        		  {
	        		      Matcher matcher = pattern.matcher(strArr[i]);
	        		      if(!matcher.matches())
	        		      {
	        		    	  columnNames.put(strArr[i], String.valueOf(i));
	        		      }
	        		      else
	        		      {
	        		    	  dates.add(strArr[i]);
	        		      }
	        		      //System.out.println(columnNames +" : "+ matcher.matches());
	        		      

	        		} 
	        		  columnNames.put("Date", "");
	        		  
        		      columnNames.entrySet().forEach(entry->{
      		    	    System.out.println(entry.getKey() + " " + entry.getValue());  
      		    	 });
	        	  }
	        	  else if(count==1)
	        	  {	
	        		  for(int j=0;j<=dates.size();j++)
	        		  {
	        			  String[] strArr = str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		        		  Map<String,String> mp = new LinkedHashMap<>();
		        		  mp.putAll(columnNames);
		        		  Set<String> set= mp.keySet();
		        		  List<String> keyOfMp= set.stream().collect(Collectors.toList());
		       		     
		        		  for(int i=0;i<keyOfMp.size();i++)
		        		  {
		        			  if("Date".equals(keyOfMp.get(i)) && strArr.length>=(mp.size()+j+1))
		        			  {
		        				  System.out.println(keyOfMp.get(i)+"--"+dates.get(j));
		        				  mp.put(keyOfMp.get(i), dates.get(j));
		        				  int sum = mp.size()+j;
		        				  if("3/29/20".equals(dates.get(j)))
		        				  {
		        					  System.out.println(sum);
			        				  System.out.println("Values --"+strArr[sum-1]);  
			        				  mp.put("Values", strArr[sum-1]);
		        				  }
		        				  else
		        				  mp.put("Values", strArr[sum-1]);
		        			  }
		        			  else 
		        			  {
		        			  System.out.println(keyOfMp.get(i)+"--"+strArr[i]);
		        			  mp.put(keyOfMp.get(i), strArr[i]);
		        			  }
		        		  } 
		        		  finalData.add(mp);
	        		  }
	        		  
	        	  }
	        	  count++;
	           }
	           return finalData;
	       }catch (Exception e){
	           e.printStackTrace();
	           return null;
	       }
		
		
	}

}
