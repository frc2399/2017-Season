package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustDistanceF extends Command {
	private DriveTrain driveTrain = Robot.driveTrain;
	private boolean isIncrementing;
    public AdjustDistanceF(boolean isIncrementing) {
    	this.isIncrementing = isIncrementing;
    	requires(driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isIncrementing)
    	{
    		driveTrain.incrementLeftFConstant();
    		driveTrain.incrementRightFConstant();
    	}
    	else
    	{
    		driveTrain.decrementLeftFConstant();
    		driveTrain.decrementRightFConstant();
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
