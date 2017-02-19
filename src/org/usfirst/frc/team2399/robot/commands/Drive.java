package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves DriveTrain motors
 */
public class Drive extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;

	public Drive() {
		requires(driveTrain);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		driveTrain.getPIDController().disable();		
	}
	
	/**
	 * Drives in percent mode, at a scaled speed 
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		driveTrain.driveLeftPercent(OI.modifyJoyOutputWithDeadband(OI.getLeftY()));
		driveTrain.driveRightPercent(OI.modifyJoyOutputWithDeadband(OI.getRightY()));
	}

	/**
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
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
