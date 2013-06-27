package com.meet.walkamile;

import java.util.ArrayList;

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
	}
	 
	public void drawRoute(GoogleMap map) {
		PolylineOptions rectOptions = new PolylineOptions();
		for (Position loc:Positions) {
			rectOptions.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
		}
		map.addPolyline(rectOptions);
	}

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
	}

}