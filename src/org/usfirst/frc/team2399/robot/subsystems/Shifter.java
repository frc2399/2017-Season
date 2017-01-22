package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.ShiftHot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem {

	private Solenoid shifterSolenoid;

	/**
	 * TODO: get correct port #
	 */
	// Creates a object belonging to the Solenoid class with the port as a
	// parameter
	public Shifter() {
		shifterSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID_PORT);
	}

	/**
	 * Method Accepts a value of a boolean, the solenoid object is then set to that value
	 * 
	 * @param setShifter
	 */
	public void setShifterSolenoid(boolean setShifter) {
		shifterSolenoid.set(setShifter);
	}

	/**
	 * Makes it so we can check if it really set the solenoid to true or false
	 * 
	 * @return
	 */
	public boolean getShifterSolenoid() {
		return shifterSolenoid.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShiftHot());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
