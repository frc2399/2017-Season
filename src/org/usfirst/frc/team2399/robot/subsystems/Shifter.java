package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.ShiftHot;
import org.usfirst.frc.team2399.robot.commands.ShiftNot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem {

	private Solenoid shifterHotSolenoid;
	private Solenoid shifterDangerousSolenoid;

	/**
	 * TODO: get correct port #
	 */
	// Creates a object belonging to the Solenoid class with the port as a
	// parameter
	public Shifter() {
		shifterHotSolenoid = new Solenoid(3, RobotMap.SHIFTER_HOT_SOLENOID_PORT);
		shifterDangerousSolenoid = new Solenoid(3, RobotMap.SHIFTER_DANGEROUS_SOLENOID_PORT);
	}

	/**
	 * Method Accepts a value of a boolean, the solenoid object is then set to that value
	 * 
	 * @param setShifter
	 */
	public void setShifterHotSolenoid(boolean setShifterHot) {
		shifterHotSolenoid.set(setShifterHot);
	}
	
	public void setShifterDangerousSolenoid(boolean setShifterDangerous) {
		shifterDangerousSolenoid.set(setShifterDangerous);
	}

	/**
	 * Makes it so we can check if it really set the solenoid to true or false
	 * 
	 * @return
	 */
	public boolean getShifterHotSolenoid() {
		return shifterHotSolenoid.get();
	}
	
	public boolean getShifterDangerousSolenoid() {
		return shifterDangerousSolenoid.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShiftNot());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
