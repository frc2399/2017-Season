package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Shift to dangerous (high) speed
 */

public class ShiftDangerous extends Command {
	
	private Shifter shifter = Robot.shifter;

	public ShiftDangerous() {
		requires(shifter);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time
	 * Starts the timer for the command
	 */
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
	}

	/**
	 *  Sets the Solenoid to a boolean value of true so it goes to the speed of dangerous
	 *  Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		shifter.setShifterDangerousSolenoid(RobotMap.SHIFTER_SOLENOID_DANGEROUS);
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
