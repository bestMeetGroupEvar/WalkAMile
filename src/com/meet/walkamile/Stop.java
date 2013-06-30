package com.meet.walkamile;

public class Stop {

	// fildes
	private Position loc;
	private boolean hasBeen = false;

	//
	public boolean hasStop(Position l) {
		if (loc.metersDistanceTo(l) <= 100) {
			return true;
		}
		return false;
	}

	//
	public boolean isHasBeen() {
		return hasBeen;
	}

}
