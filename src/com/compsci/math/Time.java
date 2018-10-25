package com.compsci.math;

public class Time {

	//Up to 10000 feet
	public static double timeAscent1(double climb) {
		return 10000 / climb;
	}
	
	//Above 10000 feet
	public static double timeAscent2(double climb, int altitude) {
		return (altitude - 10000) / climb;
	}
	
	//If the plane stays below 10000 the whole flight
	public static double timeAscent3(double climb, int altitude) {
		return altitude / climb;
	}
	
	public static double timeAscentChange(int altitude1, int altitude2, double climb) {
		return (altitude2 - altitude1) / climb;
	}
	
	public static double timeDescentChange(int altitude1, int altitude2, double descent) {
		return (altitude1 - altitude2) / descent;
	}
	
	public static double timeDescent1(double descent, int altitude) {
		return (altitude - 10000) / descent;
	}
		
	public static double timeDescent2(double descent) {
		return 10000 / descent;
	}
	
	public static double timeDescent3(double descent, int altitude) {
		return altitude / descent;
	}
	
	public static double timeCruise(double cruiseDistance, double cruiseSpeed) {
		return cruiseDistance / cruiseSpeed;
	}
			
}
