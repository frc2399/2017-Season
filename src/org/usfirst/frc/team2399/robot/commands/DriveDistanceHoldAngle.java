package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceHoldAngle extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private Timer timer;
	private double distance;
	private double velocity;
	private double time;
	private double rampTime;
	private double rampDistance;
	
    public DriveDistanceHoldAngle(double dist, double vel, double timeout) {
    	this.distance = dist; // inches
    	this.velocity = vel; // inches per second
    	
    	requires(driveTrain);
    	setInterruptible(true);
    	timer = new Timer();
    	setTimeout(timeout);
    	time = Math.abs(distance) / velocity;
    	rampTime = RobotMap.DRIVE_RAMP_TIME;
    	rampDistance = velocity * rampTime / 2;
    	if(distance < 0)
    	{
    		velocity = -velocity;
    	}
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetLeftPosition();
    	driveTrain.resetRightPosition();
    	driveTrain.setSpeedControlMode();
    	driveTrain.resetDriveTrainGyro();
    	timer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentTime = timer.get();
    	double currentVelocity;
    	double avgDistance = (driveTrain.getLeftPosition() + driveTrain.getRightPosition()) / 2;
    	
    	if(currentTime < rampTime)
    	{
    		currentVelocity = currentTime / rampTime * velocity;
    	}
    	
    	else if(Math.abs(avgDistance) > (Math.abs(distance) - Math.abs(rampDistance)))
    	{
    		currentVelocity = Math.abs(distance - avgDistance) / rampDistance * velocity;
    	}
    	
    	else
    	{
    		currentVelocity = velocity;
    	}
    	//MAKE THIS IN CORRECT UNITS
    	driveTrain.setLeftSpeedWithAngle(Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    	driveTrain.setRightSpeedWithAngle(Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    	
    	driveTrain.putAngleOnSmartDashboard();
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(isTimedOut() || (Utility.inRange(driveTrain.getLeftPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE) 
        		|| Utility.inRange(driveTrain.getRightPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE)))
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
