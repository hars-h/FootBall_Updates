package com.apifootball.standings.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apifootball.standings.consumer.CountryConsume;
import com.apifootball.standings.consumer.LeagueConsume;
import com.apifootball.standings.consumer.TeamConsume;
import com.apifootball.standings.model.Country;
import com.apifootball.standings.model.League;
import com.apifootball.standings.model.Team;
import com.google.gson.Gson;

@RestController
public class FootballApiController {
	ArrayList<Country> clist = new ArrayList<Country>();
	Map<Integer ,ArrayList<League>> lm = new HashMap<Integer ,ArrayList<League>>();
	Map<Integer ,ArrayList<Team>> tm = new HashMap<Integer ,ArrayList<Team>>();
	
	
	
	@RequestMapping(value="/country",method=RequestMethod.GET)
	public ResponseEntity<Object> getCountry() {
		if(clist.isEmpty()) {
		CountryConsume cc = new CountryConsume();
		clist = cc.consumeCountry();
		}
		System.out.println(clist);
		String cjson = new Gson().toJson(clist);
		return new ResponseEntity<>(cjson,HttpStatus.OK);
	}
	
	@RequestMapping(value="/country/{cid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getLeague(@PathVariable("cid") int cid) {
		ArrayList<League> llist = new ArrayList<League>();
		if(lm.containsKey(cid)) 
		{
		 System.out.println("Returning from previous");
		}
		else {
		LeagueConsume lc = new LeagueConsume();
		llist=lc.consumeLeague(cid);
		lm.put(cid, llist);
		}
		String ljson = new Gson().toJson(lm.get(cid));
		return new ResponseEntity<>(ljson,HttpStatus.OK);
	}
	
	@RequestMapping(value="/league/{lid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getTeam(@PathVariable("lid") int lid) {
		ArrayList<Team> tlist = new ArrayList<Team>();
		if(tm.containsKey(lid)) 
		{
		 System.out.println("Returning from previous");
		}
		else {
		TeamConsume tc = new TeamConsume();
		tlist=tc.consumeTeam(lid);
		tm.put(lid, tlist);
		}
		String tjson = new Gson().toJson(tm.get(lid));
		return new ResponseEntity<>(tjson,HttpStatus.OK);
	}
}
