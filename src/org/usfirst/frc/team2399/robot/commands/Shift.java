package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shift to dangerous (high) speed or hot (low) speed with pneums
 */

public class Shift extends Command {
	
	private Shifter shifter = Robot.shifter;
	private boolean shiftHotValue;
	private boolean shiftDangerousValue;

	/**
	 * @param shiftHotValue: Should be set to either the RobotMap value (on) or !RobotMap value (off)
	 * @param shiftDangerousValue: Should be set to either the RobotMap value (on) or !RobotMap value (off)
	 */
	public Shift(boolean shiftHotValue, boolean shiftDangerousValue) {
		this.shiftHotValue = shiftHotValue;
		this.shiftDangerousValue = shiftDangerousValue;
		requires(shifter);
		setInterruptible(true);
	}

	/**
	 * Sets how long the timer should run for
	 */
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
		SmartDashboard.putBoolean("   ", shiftHotValue);
		SmartDashboard.putBoolean("    ", shiftDangerousValue);
	}

	/**
	 *  Sets the Solenoids to values passed to the constructor
	 *  Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		shifter.setShifterHotSolenoid(shiftHotValue);
		shifter.setShifterDangerousSolenoid(shiftDangerousValue);
	}

	/**
	 * If the command returns true (the timer has run out), stop executing the command
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return isTimedOut();
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
