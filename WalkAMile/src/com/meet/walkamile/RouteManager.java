package com.meet.walkamile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RouteManager {
	
	private ArrayList<Route> loadedRoutes;
	private Route currentRoute;
	private Route recording;
	private boolean record = false;
	private OnRoute routeActivity;
	private double speed = 0.0;
	private DecimalFormat df=new DecimalFormat("0.0");
	
	
	public RouteManager(OnRoute r) {
		loadedRoutes = new ArrayList<Route>();
		recording = new Route();
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
		ArrayList<ParseObject> list = null;
		try {
			list = (ArrayList<ParseObject>) query.find();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		for (ParseObject po:list) {
			loadedRoutes.add(new Route(null,String.valueOf(po.get("name"))));
		}
		
		
		ParseQuery<ParseObject> query2= ParseQuery.getQuery("Position");
		ArrayList<ParseObject> thelist = null;
		final ArrayList<Position> locs = new ArrayList<Position>();
		
		for (int i = 0 ; i < loadedRoutes.size(); i++) {
			Route r = loadedRoutes.get(i);
			if (r != null) {
				locs.clear();
				query2.whereEqualTo("route", r.getName());
				try {
					thelist = (ArrayList<ParseObject>) query2.find();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				for (ParseObject po:thelist) {
					locs.add(new Position(Double.valueOf(String.valueOf(po.get("lat"))), Double.valueOf(String.valueOf(po.get("lon")))));			
					AlertDialog alertDialog = new AlertDialog.Builder(routeActivity).create();
					alertDialog.setMessage(po.getObjectId());
					alertDialog.show();
				}
				Position pos = locs.get(locs.size()-1);
				locs.set(locs.size()-1, locs.get(locs.size()-2));
				locs.set(locs.size()-2, pos);
				r.setPositions((ArrayList<Position>) locs.clone());
			}
		}

	}
	
	public void record(Position pos,GoogleMap map) {
		if (recording.getPositions().get(recording.getPositions().size()-1).metersDistanceTo(pos) > 30 || recording.getPositions().get(recording.getPositions().size()-1).metersDistanceTo(pos) <= 3)
			return;
		
		speed = recording.getPositions().get(recording.getPositions().size()-1).metersDistanceTo(pos);
		speed /= ((pos.getTime()-recording.getPositions().get(recording.getPositions().size()-1).getTime())/1000);
		
		TextView textView = (TextView) routeActivity.findViewById(R.id.speed);
		textView.setText(df.format(speed)+" M/S");
		
		recording.getPositions().add(pos);
        
		if (recording.getPositions().size() >= 2) {
			PolylineOptions rectOptions = new PolylineOptions();
			for (int i = 0; i < recording.getPositions().size(); i++) {
				rectOptions.add(recording.getPositions().get(i).getLatAndLon());
			}
			map.addPolyline(rectOptions);
		}
	}

	public boolean isRecord() {
		return record;
	}
	
	public void switchRecord(GoogleMap map) {
		if (record) {
			loadedRoutes.add(recording);
			currentRoute = loadedRoutes.get(loadedRoutes.size()-1);
			recording = new Route();
		}
		
		record = !record;
		if (record) {
			currentRoute = null;
			map.clear();
			AlertDialog alertDialog = new AlertDialog.Builder(routeActivity).create();
			alertDialog.setMessage("Recording!");
			alertDialog.show();
			recording.getPositions().add(routeActivity.getPos());
		} else {
			recording.getPositions().add(routeActivity.getPos());
			routeActivity.drawRoute();
			reciveName();
		}
		
	}
	
	public void reciveName() {
		AlertDialog.Builder alert = new AlertDialog.Builder(routeActivity);

		alert.setTitle("Recording Post");
		alert.setMessage("Choose a route name:");

		// Set an EditText view to get user input 
		final EditText input = new EditText(routeActivity);
		alert.setView(input);

		alert.setPositiveButton("Save Recording", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			String value = input.getText().toString();
			loadedRoutes.get(loadedRoutes.size()-1).setName(value);
			saveRoute(loadedRoutes.get(loadedRoutes.size()-1));
		  }
		});

		alert.setNegativeButton("Delete Recording", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  recording = new Route();
			  loadedRoutes.remove(loadedRoutes.size()-1);
			  currentRoute = null;
		  }
		});

		alert.show();
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

	public OnRoute getRouteActivity() {
		return routeActivity;
	}

	public ArrayList<Route> getLoadedRoutes() {
		return loadedRoutes;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Route getRecording() {
		return recording;
	}

}
