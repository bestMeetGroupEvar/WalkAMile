package com.meet.walkamile;

import java.util.ArrayList;

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
	}

	public void drawRoute(GoogleMap map) {
		PolylineOptions rectOptions = new PolylineOptions();
		for (Location loc : locations) {
			rectOptions.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
		}
		map.addPolyline(rectOptions);

	}

	public boolean isRouteFinished() {
		return routeFinished;
	}

	public void setRoutes(ArrayList<Location> routes) {
		this.routes = routes;
	}

	public ArrayList<Location> getRoutes() {
		return routes;
	}

}