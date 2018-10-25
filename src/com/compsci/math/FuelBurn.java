package com.compsci.math;


public class FuelBurn {

	
	
	public static double ascentFuel(double timeAscent, double fuelBurnAscent) {
		return (fuelBurnAscent / 3600) * (timeAscent);
	}
	
	public static double descentFuel(double timeDescent, double fuelBurnDescent) {
		return (fuelBurnDescent / 3600) * (timeDescent);
	}
	
	public static double cruiseFuel(double timeCruise, double cruiseFuel) {
		return (cruiseFuel / 3600) * (timeCruise);
	}
}
