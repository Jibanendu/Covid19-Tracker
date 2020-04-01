package com.covid19tacker.Covid19Tracker.Extract;

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

public class CasesExtract {
	   
	public  List<Map<String, String>> getCases(RestTemplateBuilder restTemplate,String searchString) throws IOException {
		
		String URI="";
		 if(searchString =="Confirm")
			 URI="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
		 else if(searchString == "Death")
			 URI="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Resource> response = restTemplate.build()
                                                      .exchange(URI, HttpMethod.GET, entity, Resource.class);
        InputStream is = response.getBody().getInputStream();
        InputStreamReader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);
        String str;
        int count = 0; 
        List<Map<String,String>> finalValue = new ArrayList<>();
        List<String> liHeaders = new ArrayList<>(); 
        List<String> liDates = new ArrayList<>(); 
        while((str = reader.readLine())!= null){
        	//System.out.println(str);
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
   		        	//System.out.println("Columns:"+strArr[i]);
   		        	liHeaders.add(strArr[i]);
   		         }
   		         else
   		         {
   		        	//System.out.println("Dates:"+strArr[i]);
   		        	liDates.add(strArr[i]);
   		         }
   		       }
        	}
        	else
        	{
        		System.out.println("Row count"+count);
        		String[] strArrCols = str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        		System.out.println("Dates");
        		 int countDate =0;
        		  for(int j=liHeaders.size();j<strArrCols.length;j++)
        		   {
        			  Map<String,String> mpValues = new LinkedHashMap<String,String>(); 
        			  for(int i=0;i<liHeaders.size();i++)
              		{
              		System.out.println(liHeaders.get(i)+"--"+strArrCols[i]);
              		mpValues.put(liHeaders.get(i), strArrCols[i]);
              		}
              		
        			  System.out.println(liDates.get(countDate)+"--"+strArrCols[j]);
        			  mpValues.put("Date", liDates.get(countDate));
        			  mpValues.put("ConfirmValues",strArrCols[j]);
        			  countDate++;
        			  finalValue.add(mpValues);
        		   }
        		  
        		  
        	}
        	count++;
        }
		return finalValue;
	}

}
