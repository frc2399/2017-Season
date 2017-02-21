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
	
    public DriveDistanceHoldAngle(double distance, double velocity) {
    	this.distance = distance; // inches
    	this.velocity = velocity; // inches per second
    	if(distance < 0)
    	{
    		velocity = -velocity;
    	}
    	requires(driveTrain);
    	setInterruptible(true);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetLeftPosition();
    	driveTrain.resetRightPosition();
    	driveTrain.resetDriveTrainGyro();
    	timer.start();
    	driveTrain.setSpeedControlMode();
    	time = Math.abs(distance) / velocity;
    	rampTime = RobotMap.DRIVE_RAMP_TIME;
    	rampDistance = velocity * rampTime / 2;
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
    	
    	else if(avgDistance > distance - rampDistance)
    	{
    		currentVelocity = (distance - avgDistance) / rampDistance * velocity;
    	}
    	
    	else
    	{
    		currentVelocity = velocity;
    	}
    	//MAKE THIS IN CORRECT UNITS
    	driveTrain.setLeftSpeedWithAngle(Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    	driveTrain.setRightSpeedWithAngle(Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    	SmartDashboard.putNumber("Left Velocity", driveTrain.getLeftSpeed());
    	SmartDashboard.putNumber("Right Velocity", driveTrain.getRightSpeed());
    	SmartDashboard.putNumber("Setpoint", Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Utility.inRange(driveTrain.getLeftPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE) 
        		|| Utility.inRange(driveTrain.getRightPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE))
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
