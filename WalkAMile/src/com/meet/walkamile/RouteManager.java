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
	private ArrayList<Position> recording;
	private boolean record = false;
	private OnRoute routeActivity;
	private double speed = 0.0;
	private DecimalFormat df=new DecimalFormat("0.0");
	
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
	
	public void record(Position pos,GoogleMap map) {
		if (recording.get(recording.size()-1).metersDistanceTo(pos) > 35 || recording.get(recording.size()-1).metersDistanceTo(pos) <= 3)
			return;
		
		speed = recording.get(recording.size()-1).metersDistanceTo(pos);
		speed /= ((pos.getTime()-recording.get(recording.size()-1).getTime())/1000);
		
		TextView textView = (TextView) routeActivity.findViewById(R.id.speed);
		textView.setText(df.format(speed)+" M/S");
		
		recording.add(pos);
        
		if (recording.size() >= 2) {
			PolylineOptions rectOptions = new PolylineOptions();
			for (int i = 0; i < recording.size(); i++) {
				rectOptions.add(recording.get(i).getLatAndLon());
			}
			map.addPolyline(rectOptions);
		}
	}

	public boolean isRecord() {
		return record;
	}
	
	public void switchRecord(GoogleMap map) {
		if (record) {
			loadedRoutes.add(new Route(recording, "test"));
			currentRoute = loadedRoutes.get(loadedRoutes.size()-1);
			recording = new ArrayList<Position>();
		}
		
		record = !record;
		if (record) {
			currentRoute = null;
			map.clear();
			AlertDialog alertDialog = new AlertDialog.Builder(routeActivity).create();
			alertDialog.setMessage("Recording!");
			alertDialog.show();
			recording.add(routeActivity.getPos());
		} else {
			recording.add(routeActivity.getPos());
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
			  recording = new ArrayList<Position>();
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

}