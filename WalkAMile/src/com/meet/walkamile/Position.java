package com.meet.walkamile;

import com.google.android.gms.maps.model.LatLng;

public class Position {
	
	private LatLng latAndLon;
	private long time;
	
	public Position() {
		setTime(System.currentTimeMillis());
	}
	
	public Position(double lat, double lon) {
		latAndLon = new LatLng(lat, lon);
		setTime(System.currentTimeMillis());
	}
	
	public Position(LatLng latlan) {
		latAndLon = latlan;
		setTime(System.currentTimeMillis());
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
	
	public Double metersDistanceTo(Position pos) {
		int R = 6371;
		double dLat = deg2rad(pos.getLatitude()-getLatitude());
		double dLon = deg2rad(pos.getLongitude()-getLongitude());
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(getLatitude())) * Math.cos(deg2rad(pos.getLatitude())) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		return d * 1000;
	
	}
	
	public Double kilometersDistanceTo(Position pos) {
		int R = 6371;
		double dLat = deg2rad(pos.getLatitude()-getLatitude());
		double dLon = deg2rad(pos.getLongitude()-getLongitude());
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(getLatitude())) * Math.cos(deg2rad(pos.getLatitude())) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		return d;
	
	}
	
	public double deg2rad(double deg) {
		  return deg * (Math.PI/180);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
