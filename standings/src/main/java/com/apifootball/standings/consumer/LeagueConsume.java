package com.apifootball.standings.consumer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apifootball.standings.model.League;

public class LeagueConsume {

	public ArrayList<League> consumeLeague(int cid){
		ArrayList<League> leagueList = new ArrayList<League>();
		String inLine ="";
		try {
			URL url = new URL("https://apifootball.com/api/?action=get_leagues&country_id="+cid+"&APIkey=ea1ec56fe0b87b52d5c42109d0ccb0fde947aa6025e2ef22f1173a6d7342e3c4");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int ResponseCode = conn.getResponseCode();
			if(ResponseCode!=200)
				throw new RuntimeException("HttpResponseCode : "+ResponseCode);
			else {
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					inLine+=sc.nextLine();
				}
				sc.close();
				JSONArray lArray = new JSONArray(inLine);
				
				for(int i=0;i<lArray.length();i++) {
				JSONObject obj = lArray.getJSONObject(i);
				int lid = obj.getInt("league_id");
				String lname = obj.getString("league_name");
				League l=new League(lid,lname);
				leagueList.add(l);
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return leagueList;
	}
}
