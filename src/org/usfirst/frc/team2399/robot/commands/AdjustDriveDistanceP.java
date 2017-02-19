package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustDriveDistanceP extends Command {
	private DriveTrain driveTrain = Robot.driveTrain;
	private boolean isIncrementing;
    public AdjustDriveDistanceP(boolean isIncrementing) {
		this.isIncrementing = isIncrementing;
        requires(driveTrain);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	if(isIncrementing)
    	{
    		driveTrain.incrementRightPConstant();
    		driveTrain.incrementLeftPConstant();
    	}
    	else{
    		driveTrain.decrementLeftPConstant();
    		driveTrain.decrementRightPConstant();
    	}
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     *  Called when another command which requires one or more of the same
     *  subsystems is scheduled to ru
     */
    protected void interrupted() {
    }
}
