package com.meet.walkamile;

import java.util.ArrayList;

import android.location.Location;

public class Route {
	
	 
		//fileds
	 ArrayList<Location> routes = new ArrayList<Location>();
	 boolean[] hasBeen;
	 
	 public Route(ArrayList<Location> locs) {
		 this.routes = locs;
		 hasBeen = new boolean[routes.size()];
	 }
	 
	 	//check off method
	 public boolean checkOff(Location userPlace)	{
		 for (int i = 0 ; i < routes.size(); i++) {
			 if (routes.get(i).equals(userPlace) && hasBeen[i-1] == true) {
				 hasBeen[i] = true;
				 return true;
			 } else if (hasBeen[i-1] == false) {
				 return false;
			 }
		 }
		 return false;
	}

	public ArrayList<Location> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<Location> routes) {
		this.routes = routes;
	}

}