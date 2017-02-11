package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAngle extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double setpoint;
    public DriveAngle(double setpoint) {
    	requires(driveTrain);
    	this.setpoint = setpoint;
    	setInterruptible(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.driveAngle(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Checks to see if our current distance is within our absolute tolerance for the setpoint
     */
    protected boolean isFinished() {
        return driveTrain.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
