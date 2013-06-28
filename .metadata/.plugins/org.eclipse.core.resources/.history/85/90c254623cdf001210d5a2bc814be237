package com.meet.walkamile;

import java.util.ArrayList;

<<<<<<< HEAD
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;


public class Route {
	
	 
		//fileds
	 private ArrayList<Position> Positions = new ArrayList<Position>();
	 private boolean[] hasBeen;
	 private String name;
	 
	 public Route(ArrayList<Position> locs,String name) {
		 this.Positions = locs;
		 hasBeen = new boolean[Positions.size()];
		 this.name = name;
	 }
	 
	 	//check off method
	 public boolean checkOff(Position userPlace)	{
		 for (int i = 0 ; i < Positions.size(); i++) {
			 if (Positions.get(i).equals(userPlace) && hasBeen[i-1] == true) {
				 hasBeen[i] = true;
				 return true;
			 } else if (hasBeen[i-1] == false) {
				 return false;
			 }
		 }
		 return false;
=======
import android.location.Location;

public class Route {

	// fields
	ArrayList<Location> routes = new ArrayList<Location>();
	boolean[] hasBeen;
	boolean routeFinished = false;

	public Route(ArrayList<Location> locs) {
		this.routes = locs;
		hasBeen = new boolean[routes.size()];
	}

	// check off method
	public boolean checkOff(Location userPlace) {
		for (int i = 0; i < routes.size(); i++) {
			if (i == routes.size() - 1) {
				if (routes.get(routes.size()).equals(userPlace)
						&& hasBeen[routes.size() - 1] == true) {
					routeFinished = true;
				}
			}
			if (routes.get(i).equals(userPlace) && hasBeen[i - 1] == true) {
				hasBeen[i] = true;
				return true;
			} else if (hasBeen[i - 1] == false) {
				return false;
			}
		}
		return false;
>>>>>>> f8edc543f3bebd2065b128fb8e5c3852517522ec
	}

	public void drawRoute(GoogleMap map) {
		PolylineOptions rectOptions = new PolylineOptions();
<<<<<<< HEAD
		for (Position loc:Positions) {
=======
		for (Location loc : locations) {
>>>>>>> f8edc543f3bebd2065b128fb8e5c3852517522ec
			rectOptions.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
		}
		map.addPolyline(rectOptions);

	}

	public boolean isRouteFinished() {
		return routeFinished;
	}

<<<<<<< HEAD
	public ArrayList<Position> getPositions() {
		return Positions;
	}

	public void setPositions(ArrayList<Position> routes) {
		this.Positions = routes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
=======
	public void setRoutes(ArrayList<Location> routes) {
		this.routes = routes;
	}

	public ArrayList<Location> getRoutes() {
		return routes;
>>>>>>> f8edc543f3bebd2065b128fb8e5c3852517522ec
	}

}