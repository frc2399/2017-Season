package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoyDrive extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double desiredAngle;

	public JoyDrive() {
		requires(driveTrain);
		setInterruptible(true);
	}

	//TODO; needs void?
	public void setDriveAngle(double desiredAngle) {
		this.desiredAngle = desiredAngle;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.setDesiredAngle(desiredAngle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		driveTrain.driveLeftPercent(OI.getLeftY());
		driveTrain.driveRightPercent(OI.getRightY());
		driveTrain.moveToAngle();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
