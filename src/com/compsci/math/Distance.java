package com.compsci.math;

public class Distance {
	
	public static double distanceAscent(double heightSpeed1, double heightSpeed2, double climb, int altitude) {
		return (Time.timeAscent1(climb) * heightSpeed1) + (Time.timeAscent2(climb, altitude) * heightSpeed2);
	}

	public static double distanceDescent(double heightSpeed1, double heightSpeed2, double descent, int altitude) {
		return (Time.timeDescent1(descent, altitude) * heightSpeed2) + (Time.timeDescent2(descent) * heightSpeed1);
	}
	
	public static double totalDistance(double initLat, double initLong, double finalLat, double finalLong) {
		//From: https://www.movable-type.co.uk/scripts/latlong.html
		double lat1 = Extra.toRadians(initLat);
		double lat2 = Extra.toRadians(finalLat);
		double Δlat = lat2 - lat1;
		
		double long1 = Extra.toRadians(initLong);
		double long2 = Extra.toRadians(finalLong);
		double Δlong = long2 - long1;
		
		double Re = 20902230;
		
		double a = (Math.sin(Δlat / 2) * Math.sin(Δlat / 2)) + (Math.cos(lat1) * Math.cos(lat2) * Math.sin(Δlong / 2) * Math.sin(Δlong / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return c * Re;
	}
	
	public static double cruiseDistance(double totalDistance, double heightSpeed1, double heightSpeed2, double descent, double climb, int altitude) {		
		return totalDistance - (distanceAscent(heightSpeed1, heightSpeed2, climb, altitude) + distanceDescent(heightSpeed1, heightSpeed2, descent, altitude));
	}
			
}
