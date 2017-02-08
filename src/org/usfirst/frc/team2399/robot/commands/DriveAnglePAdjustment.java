package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Used for loop tuning with buttons
 */
public class DriveAnglePAdjustment extends Command {
	
	private DriveTrain driveTrain = Robot.driveTrain;
	private boolean isIncrementing;
	

    public DriveAnglePAdjustment(boolean isIncrementing) {
    	requires(driveTrain);
    	setInterruptible(true);
    	this.isIncrementing = isIncrementing;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isIncrementing)
    	{
    		driveTrain.incrementAnglePConstant();
    	}
    	else
    	{
    		driveTrain.decrementAnglePConstant();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
