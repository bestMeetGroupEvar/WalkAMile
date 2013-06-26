package com.meet.walkamile;

import java.util.ArrayList;

import android.location.Location;

public class Route {
	
	
		//fileds
	 ArrayList<Location> routes = new ArrayList<Location>();
	 ArrayList<Boolean> hasBeen= new ArrayList<Boolean>();
	 	
	 	//check off method
	 public boolean setLocation (Location userPlace)	{
		 int i=0;
			while (routes!=null && routes.get(i)!=null){
				if (routes.get(i).equals(userPlace)){
					hasBeen.set(i, true);
					return true;
				}
					i++;	
		}
			return false;
			}


}