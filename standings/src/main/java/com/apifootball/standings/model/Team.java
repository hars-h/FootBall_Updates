package com.apifootball.standings.model;

public class Team {
private String teamName;
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public int getOlp() {
	return olp;
}
public void setOlp(int olp) {
	this.olp = olp;
}
private int olp;

public Team(String teamName, int olp) {
	super();
	this.teamName = teamName;
	this.olp = olp;
}
}
