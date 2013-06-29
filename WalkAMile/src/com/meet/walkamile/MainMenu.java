package com.meet.walkamile;

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
        //Parse.initialize(this, "8vUmX8zsitdoTiC7Ih1q0ewG1C0VKvhVsrVYM0TO", "igAhOYEpx5Tkp7i7LCI74oIExOdBMmc3Ey8nPzFH"); 
        //ParseAnalytics.trackAppOpened(getIntent());
        
        //rm = new RouteManager(new OnRoute(rm));
        //rm.getRouteActivity().setRm(rm);
        
    }
    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
   //     getMenuInflater().inflate(R.menu.main_menu, menu);
    //    return true;
   // }
    
}
