package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Shift;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem {

	private Solenoid shifterHotSolenoid;
	private Solenoid shifterDangerousSolenoid;

	public Shifter() {
		shifterHotSolenoid = new Solenoid(3, RobotMap.SHIFTER_HOT_SOLENOID_PORT);
		shifterDangerousSolenoid = new Solenoid(3, RobotMap.SHIFTER_DANGEROUS_SOLENOID_PORT);
	}

	/**
	 * Method accepts a value of a boolean, the Solenoid object is then set to that value
	 * @param setShifter
	 */
	public void setShifterHotSolenoid(boolean setShifterHot) {
		shifterHotSolenoid.set(setShifterHot);
	}
	
	public void setShifterDangerousSolenoid(boolean setShifterDangerous) {
		shifterDangerousSolenoid.set(setShifterDangerous);
	}

	/**
	 * Checks value of Solenoid for testing purposes
	 * @return
	 */
	public boolean getShifterHotSolenoid() {
		return shifterHotSolenoid.get();
	}
	
	public boolean getShifterDangerousSolenoid() {
		return shifterDangerousSolenoid.get();
	}

	/**
	 * Default state of subsystem is off
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Shift(!RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
