package org.usfirst.frc.team2399.robot;

public class Utility
{
	public Utility()
	{
		
	}
	
	/**
	 * Unit Conversions
	 * @param feet
	 * @return
	 */
	public static double feetToInches(double feet)
	{
		return feet * 12.0;
	}

	public static double inchesToFeet(double inches)
	{
		return inches / 12.0;
	}
}
