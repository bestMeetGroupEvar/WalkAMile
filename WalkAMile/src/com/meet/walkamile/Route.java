package com.meet.walkamile;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import android.location.Location;

public class Route {
	
	 
		//fileds
	 ArrayList<Location> locations = new ArrayList<Location>();
	 boolean[] hasBeen;
	 
	 public Route(ArrayList<Location> locs) {
		 this.locations = locs;
		 hasBeen = new boolean[locations.size()];
	 }
	 
	 	//check off method
	 public boolean checkOff(Location userPlace)	{
		 for (int i = 0 ; i < locations.size(); i++) {
			 if (locations.get(i).equals(userPlace) && hasBeen[i-1] == true) {
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
		for (Location loc:locations) {
			rectOptions.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
		}
		map.addPolyline(rectOptions);
	}

	public ArrayList<Location> getlocations() {
		return locations;
	}

	public void setlocations(ArrayList<Location> routes) {
		this.locations = routes;
	}

}