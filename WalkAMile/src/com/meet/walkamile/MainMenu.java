package com.meet.walkamile;

import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class MainMenu extends Activity {

	public static int STATE_RECORD = 0;
	public static int STATE_RUN = 1;
	public static int STATE = -1;
	private RouteManager rm;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Parse.initialize(this, "8vUmX8zsitdoTiC7Ih1q0ewG1C0VKvhVsrVYM0TO", "igAhOYEpx5Tkp7i7LCI74oIExOdBMmc3Ey8nPzFH"); 
        ParseAnalytics.trackAppOpened(getIntent());
        
        rm = new RouteManager(new OnRoute(rm));
        rm.getRouteActivity().setRm(rm);
        
		chooseRoute();
        
        //CHOOSE ROUTE
        Button choose = (Button) findViewById(R.id.button5);
        OnClickListener buttonListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				rm.loadRoutes(10);
				

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainMenu.this);
				alertDialog.setTitle("Route Choosing");
				alertDialog.setMessage("Choose route!");
				
				alertDialog.setPositiveButton("A", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						rm.setCurrentRoute(rm.getLoadedRoutes().get(0));
						Intent a = new Intent(MainMenu.this,OnRoute.class);
						a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(a);
					}
				});
				
				alertDialog.setNegativeButton("B", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						rm.setCurrentRoute(rm.getLoadedRoutes().get(1));
						Intent a = new Intent(MainMenu.this,OnRoute.class);
						a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(a);
					}
				});
				
				alertDialog.show();
			}
        };
        choose.setOnClickListener(buttonListener);
        
        //RECORD
        Button record = (Button) findViewById(R.id.button2);
        OnClickListener buttonListener2 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				//rm.switchRecord();
			}
        };
        record.setOnClickListener(buttonListener2);
    }
    
    public void chooseRoute() {
    	
    	
    	
    }

    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
   //     getMenuInflater().inflate(R.menu.main_menu, menu);
    //    return true;
   // }
    
}
