package com.meet.walkamile;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;

public class MainMenu extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Parse.initialize(this, "8vUmX8zsitdoTiC7Ih1q0ewG1C0VKvhVsrVYM0TO", "igAhOYEpx5Tkp7i7LCI74oIExOdBMmc3Ey8nPzFH"); 
        
        ParseAnalytics.trackAppOpened(getIntent());
        
        //ParseObject testObject = new ParseObject("Location");
        //testObject.put("route", "testRoute");
        //testObject.put("lat", 31.54353);
       //testObject.put("lon", 33.6666);
       // testObject.saveInBackground();
        
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
   //     getMenuInflater().inflate(R.menu.main_menu, menu);
    //    return true;
   // }
    
}
