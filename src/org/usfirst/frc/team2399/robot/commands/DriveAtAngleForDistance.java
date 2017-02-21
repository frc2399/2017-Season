package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives at an angle for a distance
 */
public class DriveAtAngleForDistance extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double setpoint;
	private double distance;
	private PIDController angleDistanceController;
	private Timer timer;
	
	private double velocity;
	private double time;
	private double rampTime;
	private double rampDistance;
	
	/**
	 * @param setpoint: Goal angle
	 * @param distance: Goal distance
	 */
    public DriveAtAngleForDistance(double setpoint, double distance) {
    	requires(driveTrain);
    	this.setpoint = setpoint;
    	this.distance = distance;
    	angleDistanceController = new PIDController(RobotMap.DRIVE_ANGLE_P, 
    			RobotMap.DRIVE_ANGLE_I, 
    			RobotMap.DRIVE_ANGLE_D,
    			0, 
    			driveTrain.yawSource(), 
    			driveTrain.controlRightSide());
    	angleDistanceController.setInputRange(-180, 180);
    	angleDistanceController.setOutputRange(-1, 1);
    	angleDistanceController.setContinuous();
    	angleDistanceController.setAbsoluteTolerance(RobotMap.ANGLE_MERKEL_DISTANCE_TOLERANCE);
    	setInterruptible(true);
    	velocity = 24;
    	timer = new Timer();
    }

    /**
     * Switches the Talons to speed mode
     * Sets the setpoint(where we want to be)
     * Sets position for distance
     */
    protected void initialize() {
    	driveTrain.resetLeftPosition();
    	driveTrain.resetRightPosition();
    	driveTrain.resetDriveTrainGyro();
    	timer.start();
    	driveTrain.setSpeedFixedAngleControlMode();
    	time = distance / velocity;
    	rampTime = RobotMap.DRIVE_RAMP_TIME;
    	rampDistance = velocity * rampTime / 2;
    	
    	
    	angleDistanceController.reset();
    	angleDistanceController.enable();
    	SmartDashboard.putData("AngleDistance PIDController", angleDistanceController);
    	
    }

    /**
     * Calculate P outputs for left, right and angle
     * Has two MIXED constants one for lines and one for angles
     * Called repeatedly when this Command is scheduled to run
     */
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
    	angleDistanceController.setSetpoint(setpoint);
    	driveTrain.setLeftDesiredPosition((Utility.inchesPerSecondToGearboxRPM(currentVelocity)));
    	SmartDashboard.putNumber("Angle Setpoint", angleDistanceController.getSetpoint());
    	SmartDashboard.putNumber("Left Velocity", driveTrain.getLeftSpeed());
    	SmartDashboard.putNumber("Right Velocity", driveTrain.getRightSpeed());
    	SmartDashboard.putNumber("Setpoint", Utility.inchesPerSecondToGearboxRPM(currentVelocity));
    }


    /**
     * Checks to see if our current distance is within our absolute tolerance for the setpoint
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
    	  return (Utility.inRange(driveTrain.getCurrentYaw(), setpoint, RobotMap.ANGLE_MERKEL_DISTANCE_TOLERANCE) 
       		   && Utility.inRange(driveTrain.getLeftSpeed(), 0, 1) 
       		   && Utility.inRange(driveTrain.getRightSpeed(), 0, 1)) 
    			  && (Utility.inRange(driveTrain.getLeftPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE) 
    		        		|| Utility.inRange(driveTrain.getRightPosition(), distance, RobotMap.DRIVE_DISTANCE_TOLERANCE));
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    	angleDistanceController.disable();
    }

    /**Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    	end();
    }
}
