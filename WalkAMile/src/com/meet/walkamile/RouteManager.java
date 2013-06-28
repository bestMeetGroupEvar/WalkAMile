package com.meet.walkamile;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RouteManager {
	
	private ArrayList<Route> loadedRoutes;
	private Route currentRoute;
	private ArrayList<Position> recording;
	private boolean record = false;
	private OnRoute routeActivity;
	
	public RouteManager(OnRoute r) {
		loadedRoutes = new ArrayList<Route>();
		recording = new ArrayList<Position>();
		routeActivity = r;
	}
	
	public void saveRoute(Route route) {
		ParseObject rot = new ParseObject("Route");
		rot.put("name", route.getName());
		rot.saveInBackground();
		for (Position loc:route.getPositions()) {
		   ParseObject Position = new ParseObject("Position");
		   Position.put("route", route.getName());
		   Position.put("lat", loc.getLatitude());
		   Position.put("lon", loc.getLongitude());
		   Position.saveInBackground();
		}
	}
	
	public void loadRoutes(int amount) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Route");
		query.setLimit(amount);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		    public void done(List<ParseObject> routes, ParseException e) {
				for (int i = 0; i < routes.size(); i++)
					loadedRoutes.add(new Route(null,String.valueOf(routes.get(i).get("name"))));
		    }
		});
		
		final ArrayList<Position> locs = new ArrayList<Position>();
		for (Route r: loadedRoutes) {
			locs.clear();
			query = ParseQuery.getQuery("Position");
			query.whereEqualTo("route", r.getName());
			query.findInBackground(new FindCallback<ParseObject>() {
				@Override
			    public void done(List<ParseObject> locas, ParseException e) {
					for (int i = 0; i < locas.size(); i++)
						locs.add(new Position(Double.valueOf(String.valueOf(locas.get(i).get("lat"))), Double.valueOf(String.valueOf(locas.get(i).get("lon")))));
			    }
			});
			r.setPositions(locs);
		}
	}
	
	public void record(Position pos) {
		recording.add(pos);
	}

	public boolean isRecord() {
		return record;
	}
	
	public void switchRecord() {
		if (record) {
			loadedRoutes.add(new Route(recording, "test"));
			currentRoute = loadedRoutes.get(loadedRoutes.size()-1);
			recording = new ArrayList<Position>();
		}
		
		record = !record;
		if (record) {
			AlertDialog alertDialog = new AlertDialog.Builder(routeActivity).create();
			alertDialog.setMessage("Recording!");
			alertDialog.show();
			recording.add(routeActivity.getPos());
		} else {
			AlertDialog alertDialog = new AlertDialog.Builder(routeActivity).create();
			alertDialog.setMessage("Stopped!");
			alertDialog.show();
			recording.add(routeActivity.getPos());
			routeActivity.drawRoute();
		}
		
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

}
