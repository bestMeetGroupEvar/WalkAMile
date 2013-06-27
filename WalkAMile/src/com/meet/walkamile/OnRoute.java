package com.meet.walkamile;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class OnRoute extends Activity implements LocationListener {

	private GoogleMap mMap;
	private Location currentLocation;
	private Marker character;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_on_route);
		 
		 mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

                
        //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.));
		 
			getCurrentLocation();

			character = mMap.addMarker(new MarkerOptions()
		        .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
		        
		 
		updateMap();
	}
	 public void updateMap (){
		 CameraPosition cameraPosition = new CameraPosition.Builder()
		    .target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))      // Sets the center of the map to Mountain View
		    .zoom(25)                   // Sets the zoom
		    .bearing(90)                // Sets the orientation of the camera to east
		    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
		    .build();                   // Creates a CameraPosition from the builder
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		character.setPosition(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()) );
	
	 }
	public void getCurrentLocation() {
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		ArrayList<String> providers = (ArrayList<String>) locationManager.getAllProviders();
		for (String s : providers) {
			if (isBetterLocation(locationManager.getLastKnownLocation(s), currentLocation)) {
				currentLocation = locationManager.getLastKnownLocation(s);
			}
			locationManager.requestLocationUpdates(s, 0, 0, this);
		}
	}
	
	public void onLocationChanged(Location location) {
		if (isBetterLocation(location, currentLocation)) {
			currentLocation = location;
			updateMap();
		}
	}
	
	public boolean isBetterLocation(Location loc,Location currentLocation) {
	 if( loc==null){
		 return false;
	 }
	 if(currentLocation==null)
		 return true;
	 long timedelta=loc.getTime()-currentLocation.getTime();
	 boolean isNewer=timedelta> 10;
	 int accdelta= (int) (loc.getAccuracy()-currentLocation.getAccuracy());
	 boolean isMoreAcc=accdelta<0;
	 
	 if(isMoreAcc)
		 return true;
	 else if(isNewer)
		 return true;
	 
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

}