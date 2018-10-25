package com.compsci.math;



public class Wind {
	
	public static double windDotSpeed(double initLat, double initLong, double finalLat, double finalLong, double windDir, double windMag, double cruiseSpeed) {
		return cruiseSpeed + (windMag) * Math.cos(Extra.toRadians(((aircraftBearing(initLat, initLong, finalLat, finalLong) - windDir))));
	}
	
	public static double aircraftBearing(double initLat, double initLong, double finalLat, double finalLong) {
		//Same beginning as the total distance calculator in Distance
		double lat1 = Extra.toRadians(initLat);
		double lat2 = Extra.toRadians(finalLat);
		
		double long1 = Extra.toRadians(initLong);
		double long2 = Extra.toRadians(finalLong);
		double Δlong = long2 - long1;
		
		//Slightly different calculations
		double a = Math.sin(Δlong) * Math.cos(lat2);
		double b = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(Δlong);
		return Math.atan2(b, a) * (180 / Math.PI);
		//The 180 / PI converts it to degrees
		
	}
}


