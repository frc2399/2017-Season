package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive to an angle
 */
public class DriveAngle extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double setpoint;
	private double angularVelocity;
	private Timer timer; 
	private double finishTime;
	
	/**
	 * @param setpoint: Goal angle
	 */
    public DriveAngle(double setpoint,double timeout) {
    	requires(driveTrain);
    	this.setpoint = setpoint;
    	setInterruptible(true);
    	timer = new Timer();
    	angularVelocity = 60;
    	finishTime = Math.abs(setpoint / angularVelocity);
    	setTimeout(timeout);
    }

    /**
     * Switches the Talons to speed mode
     * Enables PIDController
     * Sets the setpoint(where we want to be)
     */
    protected void initialize() {
    	driveTrain.resetDriveTrainGyro();
    	driveTrain.setPercentControlMode();
    	driveTrain.getPIDController().reset();
    	driveTrain.getPIDController().enable();
    	timer.start();
    	}

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	
    	double currentSetpoint = setpoint;
    	double currentTime = timer.get();
    	if(currentTime < finishTime)
    	{
    		currentSetpoint = currentTime / finishTime * setpoint;
    	}
    	driveTrain.getPIDController().setSetpoint(currentSetpoint);
    	}

    /**
     * Checks to see if our current distance is within our absolute tolerance for the setpoint
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
       return isTimedOut() || Utility.inRange(driveTrain.getCurrentYaw(), setpoint, RobotMap.ANGLE_MERKEL_TOLERANCE) 
    		   && Utility.inRange(driveTrain.getLeftSpeed(), 0, 1) 
    		   && Utility.inRange(driveTrain.getRightSpeed(), 0, 1);
    		   //(driveTrain.getPIDController().onTarget());
       //);
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    	driveTrain.getPIDController().disable();
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    	end();
    }
}
