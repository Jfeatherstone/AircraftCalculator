package com.compsci.math;

public class Extra {

	public static double toRadians(double angle) {
		return angle * ((Math.PI) / 180);
	}
	
	public static double temp(int altitude) {
		return 273 + (15 - (altitude * .00198));
	}
	
	public static String timeFormat(double t) {
		t /= 60;
		int h = (int)(t / 60);
		int m = (int)(t % 60);
		
		if (m == 0)
			return h+" hours";
		else {
			if (h == 0)
				return m+" minutes";
			else
				return h+" hours, "+m+" minutes";

		}
	}
	
}
