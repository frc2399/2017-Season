package org.usfirst.frc.team2399.robot.subsystems;

import com.ctre.CANTalon;

public class DriveTrain {
	private CANTalon left;
	private CANTalon right;

	public void driveLeft(double leftSpeed) {
		left.set(leftSpeed);
	}

	public void driveRight(double rightSpeed) {
		right.set(rightSpeed);
	}

}
