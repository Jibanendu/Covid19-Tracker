package com.covid19tacker.Covid19Tracker.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.covid19tacker.Covid19Tracker.model.StatsModel;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Service
public class Covid19Service {

	public List<StatsModel> getDailyTrendData(String countryValue) throws IOException {
		OkHttpClient client = new OkHttpClient();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String currentDate = df.format(dateobj);
		System.out.println(currentDate);
		
		Request request = new Request.Builder()
				.url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/history_by_country_and_date.php?country="+countryValue+"&date="+currentDate)
				.get()
				.addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
				.addHeader("x-rapidapi-key", "e9ad48a024msh6428467e9bee21dp1e1c4bjsn03bd4b9a75ab")
				.build();
			Response response = client.newCall(request).execute();
			String jsonData = response.body().string();
			System.out.println(jsonData);
			
			
			JSONObject jobject = new JSONObject(jsonData);
			String country = jobject.getString("country");
			
			JSONArray jArray = jobject.getJSONArray("stat_by_country");
			System.out.println(jArray.length());
			
			List<StatsModel> liData = new ArrayList<>();
			
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject jsonObject = jArray.getJSONObject(i);
				
				String region = "";
				if(!jsonObject.isNull("region")){
					region = jsonObject.getString("region");
				} else {
					region = null;
				}
				
				StatsModel s = new StatsModel(); 
				s.setId(jsonObject.getInt("id"));
				s.setCountry_name(jsonObject.getString("country_name"));
				s.setTotal_cases(jsonObject.getString("total_cases"));
				s.setNew_cases(jsonObject.getString("new_cases"));
				s.setActive_cases(jsonObject.getString("active_cases"));
				s.setTotal_deaths(jsonObject.getString("total_deaths"));
				s.setNew_deaths(jsonObject.getString("new_deaths")!= null ? jsonObject.getString("new_deaths"): "0");
				s.setTotal_recovered(jsonObject.getString("total_recovered"));
				s.setSerious_critical(jsonObject.getString("serious_critical"));
				s.setRegion(region);
				s.setTotal_cases(jsonObject.getString("total_cases_per1m"));
				s.setRecord_date(jsonObject.getString("record_date"));
				
				
				liData.add(s);
				
			}
			
		return liData;
	}


	

}
