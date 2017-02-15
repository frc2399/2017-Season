package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAtAngleForDistance extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private double setpoint;
	private double distance;
	
    public DriveAtAngleForDistance(double setpoint, double distance) {
    	requires(driveTrain);
    	this.setpoint = setpoint;
    	this.distance = distance;
    	setInterruptible(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     * Switches the Talons to speed mode
     * Sets the setpoint(where we want to be)
     * Sets position for distance
     */
    protected void initialize() {
    	driveTrain.setSpeedControlMode();
    	driveTrain.getPIDController().setSetpoint(setpoint);
    	driveTrain.setLeftDesiredPosition(distance);
    	driveTrain.setRightDesiredPosition(distance);	
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Calculate P outputs for left, right and angle
     * Has two MIXED constants one for lines and one for angles
     */
    protected void execute() {

    	double leftPOutput = driveTrain.returnLeftDistanceConstant() * driveTrain.returnLeftDistanceError();
    	double rightPOutput = driveTrain.returnRightDistanceConstant() * driveTrain.returnRightDistanceError();
    	double anglePOutput = driveTrain.getPIDController().getError() * driveTrain.getPIDController().getP();

    	driveTrain.driveLeftVelocity(leftPOutput * RobotMap.DRIVE_MIXED_LINEAR + anglePOutput * RobotMap.DRIVE_MIXED_ANGULAR);
    	driveTrain.driveRightVelocity(rightPOutput * RobotMap.DRIVE_MIXED_LINEAR - anglePOutput * RobotMap.DRIVE_MIXED_ANGULAR);
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
