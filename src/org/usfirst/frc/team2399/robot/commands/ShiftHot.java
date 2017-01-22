package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created an instance of the Shifter Class which is equal to the instance of
 * class in Robot
 */
public class ShiftHot extends Command {
	private Shifter shifter = Robot.shifter;

	public ShiftHot() {
		requires(shifter);
		setInterruptible(true);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	/**
	 * Sets a timer of how long the solenoid needs to run
	 */
	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
	}

	/**
	 * Sets the solenoid to a boolean value of false
	 */
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shifter.setSolenoid(false);
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
