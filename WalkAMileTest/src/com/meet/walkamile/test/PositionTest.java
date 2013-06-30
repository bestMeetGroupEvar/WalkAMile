package com.meet.walkamile.test;

import com.meet.walkamile.Position;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

	public PositionTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPosition() {
		fail("Not yet implemented");
	}

	public void testPositionDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testPositionLatLng() {
		fail("Not yet implemented");
	}

	public void testGetLatAndLon() {
		fail("Not yet implemented");
	}

	public void testSetLatAndLon() {
		fail("Not yet implemented");
	}

	public void testGetLatitude() {
		fail("Not yet implemented");
	}

	public void testGetLongitude() {
		fail("Not yet implemented");
	}

	public void testCalculateDistanceInKM() {
		Position jerusalem= new Position(31.7833,35.2167);
		Position eilat= new Position(29.5500,34.9500);
		double dis= jerusalem.CalculateDistanceInKM(eilat);
		assertTrue("first one should be finished", dis<321.5&&dis>301.5);

	}

}
