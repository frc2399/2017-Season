package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive to an angle
 */
public class DriveAngle extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double setpoint;
	
	/**
	 * @param setpoint: Goal angle
	 */
    public DriveAngle(double setpoint) {
    	requires(driveTrain);
    	this.setpoint = setpoint;
    	setInterruptible(true);
    }

    /**
     * Switches the Talons to speed mode
     * Enables PIDController
     * Sets the setpoint(where we want to be)
     */
    protected void initialize() {
    	driveTrain.setSpeedControlMode();
    	driveTrain.getPIDController().enable();
    	driveTrain.getPIDController().setSetpoint(setpoint);
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	
    }

    /**
     * Checks to see if our current distance is within our absolute tolerance for the setpoint
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return driveTrain.getPIDController().onTarget();
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
