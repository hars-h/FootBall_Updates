package com.apifootball.standings.model;

public class League {
private int leagueID;
private String leagueName;

public League(int leagueID, String leagueName) {
	super();
	this.leagueID = leagueID;
	this.leagueName = leagueName;
}
public int getLeagueID() {
	return leagueID;
}
public void setLeagueID(int leagueID) {
	this.leagueID = leagueID;
}
public String getLeagueName() {
	return leagueName;
}
public void setLeagueName(String leagueName) {
	this.leagueName = leagueName;
}



}
