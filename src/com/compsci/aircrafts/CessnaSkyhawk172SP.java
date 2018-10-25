package com.compsci.aircrafts;

public class CessnaSkyhawk172SP {

	//How fast the aircraft can ascend (feet per minute)
	public int climb = 150 / 60;
	
	//How fast the aircraft can travel horizontally while under 10,000 feet
	public int heightSpeed1 = 80;
	
	//How fast the aircraft can travel horizontally while above 10,000 feet
	public int heightSpeed2 = 80;
	
	//How fast the aircraft travels horizontally (mach)
	public double cruiseSpeed = 115;
	
	//How fast the aircraft can descend (feet per minute)
	public int descent = 450 / 60;
	
	//At what rate the aircraft burns fuel while traveling vertically up (pounds per hour)
	public int fuelBurnAscent = 150;
	
	//At what rate the aircraft burns fuel while traveling horizontally varying with altitude (pounds per hour)
	public int[] fuelBurnCruise = {120, 115, 110, 105, 100, 95, 90, 85}; //pph
	public int[] altitudeIndex = {3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000}; //feet
	
	public double cruiseFuel(int altitude) {
		return (-.005 * altitude) + 135;
	}
	
	//Amount of fuel burned per hour while descending (pounds per hour)
	public int fuelBurnDescent = 55;
	
	//How high the aircraft can go (feet)
	public int maxAltitude = 10000;
	
	public int minAltitude = 1000;
	
}
