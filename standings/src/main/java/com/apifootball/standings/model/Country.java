package com.apifootball.standings.model;

public class Country {
private int countryID;
private String countryName;

/**
 * @return the countryID
 */
public int getCountryID() {
	return countryID;
}
/**
 * @param countryID the countryID to set
 */
public void setCountryID(int countryID) {
	this.countryID = countryID;
}
/**
 * @return the countryName
 */
public String getCountryName() {
	return countryName;
}
/**
 * @param countryName the countryName to set
 */
public void setCountryName(String countryName) {
	this.countryName = countryName;
}

public Country(int countryID, String countryName) {
	super();
	this.countryID = countryID;
	this.countryName = countryName;
}


}
