package com.meet.walkamile;

import com.google.android.gms.maps.model.LatLng;

public class Position {
	
	private LatLng latAndLon;
	
	public Position() {
	}
	
	public Position(double lat, double lon) {
		latAndLon = new LatLng(lat, lon);
	}
	
	public Position(LatLng latlan) {
		latAndLon = latlan;
	}

	public LatLng getLatAndLon() {
		return latAndLon;
	}

	public void setLatAndLon(LatLng latAndLon) {
		this.latAndLon = latAndLon;
	}
	
	public double getLatitude() {
		return latAndLon.latitude;
	}
	
	public double getLongitude() {
		return latAndLon.longitude;
	}

}
