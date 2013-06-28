package com.meet.walkamile.test;

import junit.framework.TestCase;

import com.meet.walkamile.Route;
import com.meet.walkamile.RouteSet;

public class RouteSetValidation extends TestCase {

	public RouteSetValidation(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRemoveRoute() {
		fail("Not yet implemented");
	}

	public void testCheckIfFinished() {
		RouteSet route = new RouteSet();
		Route r = new Route();
		r.setRouteFinished(true);
		//r.checkOff(position);
		//assertTrue("route shouldnt be finishes",
			//	route.checkIfFinished() == true);
	}
}
