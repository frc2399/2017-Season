package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class ShiftDangerous extends Command {
	private Shifter shifter = Robot.shifter;

	public ShiftDangerous() {
		requires(shifter);
		setInterruptible(true);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	/**
	 * 
	 */
	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shifter.setSolenoid(true);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
