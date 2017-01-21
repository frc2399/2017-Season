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
	public Shifter() {
		shifterSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID_PORT);
	}

	public void setSolenoid(boolean setShifter) {
		shifterSolenoid.set(setShifter);
	}

	public boolean getSolenoid() {
		return shifterSolenoid.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShiftHot());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
