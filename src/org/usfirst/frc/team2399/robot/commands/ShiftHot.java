package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Shifts drivetrain to hot (low) gear
 */
public class ShiftHot extends Command {
	private Shifter shifter = Robot.shifter;

	public ShiftHot() {
		requires(shifter);
		setInterruptible(true);
	}

	/**
	 * Sets a timer of how long the solenoid needs to run
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		setTimeout(RobotMap.SHIFT_TIMER);
	}

	/**
	 * Sets the solenoid to a boolean value of false
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		shifter.setShifterHotSolenoid(RobotMap.SHIFTER_SOLENOID_HOT);
	}

	/**
	 * isTimedOut returns true when the timer runs out, and the command will no longer execute
	 * Make this return true when this Command no longer needs to run execute()
	 */
	
	protected boolean isFinished() {
		return isTimedOut();
	}

	/**
	 *  Called once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 *  Called when another command which requires one or more of the same
	 *  subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
