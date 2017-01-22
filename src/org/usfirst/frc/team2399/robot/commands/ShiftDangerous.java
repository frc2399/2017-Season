package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Creates an instance of the Shifter class that is referenced from Robot so
 * this command can run from Shifter class
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
	 * Starts the timer for the Solenoid referenced as a constant in RobotMap
	 */
	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
	}

// Sets the Solenoid to a boolean value of true so it goes to the speed of dangerous
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shifter.setSolenoid(true);
	}

	/**
	 * Sets the command to return either true or false depending on if the time
	 * has been complete to alert if it should keep executing or not
	 */
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
