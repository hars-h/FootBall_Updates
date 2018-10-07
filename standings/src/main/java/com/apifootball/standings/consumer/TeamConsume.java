package com.apifootball.standings.consumer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apifootball.standings.model.Team;

public class TeamConsume {

	public ArrayList<Team> consumeTeam(int lid){
		ArrayList<Team> teamList = new ArrayList<Team>();
		String inLine ="";
		try {
			URL url = new URL("https://apifootball.com/api/?action=get_standings&league_id="+lid+"&APIkey=ea1ec56fe0b87b52d5c42109d0ccb0fde947aa6025e2ef22f1173a6d7342e3c4");
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
				JSONArray tArray = new JSONArray(inLine);
				
				for(int i=0;i<tArray.length();i++) {
				JSONObject obj = tArray.getJSONObject(i);
				String tname = obj.getString("team_name");
				int olp = obj.getInt("overall_league_position");
				Team t=new Team(tname,olp);
				teamList.add(t);
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return teamList;
	}
}
