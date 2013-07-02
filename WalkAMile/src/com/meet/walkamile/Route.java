package com.meet.walkamile;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Route {

	// fields
	ArrayList<Position> positions = new ArrayList<Position>();
	ArrayList<Position> stops = new ArrayList<Position>();
	boolean[] hasBeen;
	private String name;
	boolean routeFinished = false;
	private double timeInMinutes;
	ExerciseManager em;

	public Route(ArrayList<Position> locs, String name) {
		this.positions = locs;
		this.name = name;
		this.em=new ExerciseManager();
	}
	public Route() {
	}

	// adding stops
	public void addStop(Position curPos, GoogleMap map) {

		this.stops.add(curPos);
		Exercise e=em.randomExercise();
		String name=e.getNameOfExercise();
		String times=String.valueOf(e.getNumberOfReps())+"times";
		map.addMarker(new MarkerOptions()
        .position(new LatLng(curPos.getLatitude(), curPos.getLongitude()))
        .title(name)
        .snippet(times)
        );

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

	// Route's distance

	public double sumKM() {
		double sum = 0;
		for (int i = 1; i < positions.size(); i++) {
			sum += positions.get(i).kilometersDistanceTo(positions.get(i - 1));
		}
		return sum;
	}

	// Route's average speed

	public double averageSpeed() {
		double speed = 0;
		double sumSpeed = 0;
		for (int i = 1; i < positions.size(); i++) {
			speed = positions.get(i).metersDistanceTo(positions.get(i - 1));
			speed /= ((positions.get(i).getTime() - positions.get(i - 1)
					.getTime()) / 1000);
			sumSpeed += speed;
		}
		return (sumSpeed / (positions.size() - 1));
	}
//duration
	
	public double sumTimeHour(){
		double sumTime=0;
		double time;
		for (int i = 1; i < positions.size(); i++){
		time = ((positions.get(i).getTime()-positions.get(i-1).getTime())/1000/60/60);
		sumTime+=time;
	}
		return sumTime;
	}

	// Route's calories
	// Calorie(Kcal) ＝ BMR X Mets/24 X hour

	public int sumCalories(){
		double speed=this.averageSpeed()*2.777777777778;
		return (int)(1700*((speed*1.64)/24)*this.sumTimeHour());
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

	public double getTimeInMinutes() {
		return timeInMinutes;
	}

	public void setTimeInMinutes(double timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

}