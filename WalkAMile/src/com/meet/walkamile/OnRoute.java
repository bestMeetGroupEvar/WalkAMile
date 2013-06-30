package com.meet.walkamile;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OnRoute extends Activity implements LocationListener {

	private GoogleMap mMap;
	private Location currentLocation;
	private RouteManager rm;
	private int count;


	public OnRoute() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_on_route);
		
		rm = new RouteManager(this);
		setRm(rm);
        
        Parse.initialize(this, "8vUmX8zsitdoTiC7Ih1q0ewG1C0VKvhVsrVYM0TO", "igAhOYEpx5Tkp7i7LCI74oIExOdBMmc3Ey8nPzFH"); 
        ParseAnalytics.trackAppOpened(getIntent());
		
        Button record = (Button) findViewById(R.id.button1);
        OnClickListener buttonListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				rm.switchRecord(mMap);
			}
        };
        record.setOnClickListener(buttonListener);
        
		
		 mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		getCurrentLocation();
		updateMap();
	}

	public void updateMap() {
		if (currentLocation != null) { 
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(currentLocation.getLatitude(),
							currentLocation.getLongitude())) // Sets the center of
																// the map to
																// Mountain View
					.zoom(25) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
		
			/*character.setPosition(new LatLng(currentLocation.getLatitude(),
					currentLocation.getLongitude()));*/
			
			if (rm.isRecord()) {
				count++;
				if (count == 3) {
					rm.record(new Position(currentLocation.getLatitude(),currentLocation.getLongitude()),mMap);
					count = 0;
				}
			}
			
			drawRoute();
			

		}
	 
	}
	
	public Position getPos() {
		return new Position(currentLocation.getLatitude(),currentLocation.getLongitude());
	}
	
	public void getCurrentLocation() {
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		ArrayList<String> providers = (ArrayList<String>) locationManager
				.getAllProviders();
		for (String s : providers) {
			if (isBetterLocation(locationManager.getLastKnownLocation(s),
					currentLocation)) {
				currentLocation = locationManager.getLastKnownLocation(s);
			}
			locationManager.requestLocationUpdates(s, 0, 0, this);
		}
	}

	public void drawRoute() {
		
		if (rm.getCurrentRoute() != null) {
			rm.getCurrentRoute().drawRoute(mMap);
		}
	}

	public void onLocationChanged(Location location) {
		if (isBetterLocation(location, currentLocation)) {
			currentLocation = location;
			updateMap();
		}
	}

	public boolean isBetterLocation(Location loc, Location currentLocation) {
		if (loc == null) {
			return false;
		}
		if (currentLocation == null)
			return true;

		long timeDelta = loc.getTime() - currentLocation.getTime();
		// boolean isSignificantlyNewer = timeDelta > 10;
		// boolean isSignificantlyOlder = timeDelta < -10;
		boolean isNewer = timeDelta > 0;

		// /if (isSignificantlyNewer) {
		// return true;
		//
		// } else if (isSignificantlyOlder) {
		// return false;
		// }

		int accuracyDelta = (int) (loc.getAccuracy() - currentLocation
				.getAccuracy());
		// boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		// boolean isSignificantlyLessAccurate = accuracyDelta > 100;

		//boolean isFromSameProvider = isFromSpaceProvider(loc,
		//currentLocation);

		if (isMoreAccurate) {
			return true;
		} else if (isNewer) {
			return true;
		}
		return false;
	}

	public boolean isFromSpaceProvider(Location loc1, Location loc2) {
	 if (loc1.getProvider().equals(loc2.getProvider())) {
	 return true;
	}
	  return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.on_route, menu);
		return true;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public void setRm(RouteManager rm) {
		this.rm = rm;
	}

}
