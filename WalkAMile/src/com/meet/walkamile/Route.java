package com.meet.walkamile;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Route {

	// fields
	ArrayList<Position> positions = new ArrayList<Position>();
	boolean[] hasBeen;
	private String name;
	boolean routeFinished = false;

	public Route(ArrayList<Position> locs, String name) {
		this.positions = locs;
		this.name = name;
		hasBeen = new boolean[positions.size()];
	}

	public Route() {
		hasBeen = new boolean[positions.size()];
	}

	// check off method
	public boolean checkOff(Position userPlace) {
		for (int i = 0; i < positions.size(); i++) {
			if (i == positions.size() - 1) {
				if (positions.get(positions.size()).equals(userPlace)
						&& hasBeen[positions.size() - 1] == true) {
					routeFinished = true;
				}
			}
			if (positions.get(i).equals(userPlace) && hasBeen[i - 1] == true) {
				hasBeen[i] = true;
				return true;
			} else if (hasBeen[i - 1] == false) {
				return false;
			}
		}
		return false;
	}

	/**
	 * public void drawRoute(GoogleMap map) { PolylineOptions rectOptions = new
	 * PolylineOptions(); for (Location loc : locations) { rectOptions.add(new
	 * LatLng(loc.getLatitude(), loc.getLongitude())); }
	 * map.addPolyline(rectOptions);
	 * 
	 * }
	 */

	public void drawRoute(GoogleMap map) {
		PolylineOptions rectOptions = new PolylineOptions();
		for (Position loc : positions) {
			rectOptions.add(loc.getLatAndLon());
		}
		map.addPolyline(rectOptions);

	}

	public boolean isRouteFinished() {
		return routeFinished;
	}

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}