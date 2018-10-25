package com.compsci.aircrafts;

import com.compsci.math.*;

public class Boeing737800 {
	
	//How fast the aircraft can ascend (feet per second)
	public double climb = 1500 / 60;
	
	//How fast the aircraft can travel horizontally while under 10,000 feet (feet per second
	public double heightSpeed1 = 250 /*knots*/ * 1.688 /*feet per second / knot*/;
	
	//How fast the aircraft can travel horizontally while above 10,000 feet
	public double heightSpeed2 = 280 * 1.688;
	
	//How fast the aircraft travels horizontally (in feet per second)
	public double cruiseSpeed(int altitude) {
		//1.688 is the conversion from knots to ft/s
		//.785 is the mach number
		return .785 * 1.688 * (39 * Math.sqrt(Extra.temp(altitude)));
	}
	
	//How fast the aircraft can descend (feet per second)
	public double descent = 1500 / 60;
	
	//At what rate the aircraft burns fuel while traveling vertically up (pounds per hour)
	public double fuelBurnAscent = 3000;
	
	//At what rate the aircraft burns fuel while traveling horizontally varying with altitude (pounds per hour)
	//These arrays aren't actually used in the program, but rather to model the following function which will be used
	public int[] fuelBurnCruise = {2500, 2450, 2400, 2350, 2300, 2250, 2200, 2150, 2100, 2050, 2000}; //pph
	public int[] altitudeIndex = {25000, 26000, 27000, 28000, 29000, 30000, 31000, 32000, 33000, 34000, 35000}; //feet
	//TODO: Model these arrays as a function so that any value of altitude can be entered
	public double cruiseFuel(int altitude) {
		return (-.05 * altitude + 3750);
	}
	
	//Amount of fuel burned per hour while descending (pounds per hour)
	public int fuelBurnDescent = 1000;
	
	//How high the aircraft can go (feet)
	public int maxAltitude = 35000;
	
	public int minAltitude = 25000;
	
	
}
