package com.meet.walkamile;

import android.location.Location;

public class Stop {

	//fildes
	private Location loc;
	private boolean hasBeen=false;
	
	//
	public boolean hasStop (Location l){
		double checkLa =(l.getLatitude()/40075016.6856 - loc.getLatitude()/40075016.6856);
		double checkLo = (l.getLongitude()/40075016.6856 - loc.getLongitude()/40075016.6856);
		
		if (checkLa>=100 && checkLa>=-100){
			
			if (checkLo>=100 && checkLo>=-100){
			this.hasBeen=true;
			return true;
		}}
		return false;
			
	}
	
	//
	public boolean isHasBeen() {
		return hasBeen;
	}
	
}
