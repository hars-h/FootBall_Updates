package com.apifootball.standings.consumer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;

import com.apifootball.standings.model.Country;

public class CountryConsume {

	public ArrayList<Country> consumeCountry(){
		ArrayList<Country> CountryList = new ArrayList<Country>();
		String inLine ="";
		try {
			URL url = new URL("https://apifootball.com/api/?action=get_countries&APIkey=ea1ec56fe0b87b52d5c42109d0ccb0fde947aa6025e2ef22f1173a6d7342e3c4");
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responsecode = conn.getResponseCode();
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode : "+ responsecode);
			else {
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					inLine+=sc.nextLine();
				}
				sc.close();
			}
			System.out.println(" Inline is : "+inLine);
			JSONArray cArray = new JSONArray(inLine);
			System.out.println(cArray);
			for(int i=0; i<cArray.length();i++) {
				JSONObject obj = cArray.getJSONObject(i);
				int countryID=obj.getInt("country_id");
				String countryName=obj.getString("country_name");
				Country country= new Country(countryID,countryName);
				CountryList.add(country);
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return CountryList;
	}
	
}
