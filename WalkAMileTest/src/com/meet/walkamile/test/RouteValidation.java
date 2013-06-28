package com.meet.walkamile.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.meet.walkamile.Position;
import com.meet.walkamile.Route;

public class RouteValidation extends TestCase {

	public RouteValidation(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRouteArrayListOfPositionString() {
	}

	public void testRoute() {
	}

	public void testCheckOff() {
		Position position = new Position();
		Route x = new Route();
		boolean[] y = { false, false };
		x.setHasBeen(y);
		y[0] = x.checkOff(position);
		assertTrue("first one should be finished", y[0] = true);
	}

	public void testDrawRoute() {

	}

	public void testIsRouteFinished() {
		Route x = new Route();
		// x should not be finished
		assertFalse("x should not be finished", x.isRouteFinished() == true);
	}

	public void testGetPositions() {

	}

	public void testSetPositions() {

	}

	public void testGetName() {

	}

	public void testSetName() {

	}

}
