package com.meet.walkamile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PickARoute extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_aroute);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pick_aroute, menu);
		return true;
	}

}
