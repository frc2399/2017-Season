package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveTest extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private Timer timer;
	private double distance;
	private double velocity;
    public AutoDriveTest() {
    	requires(driveTrain);
    	setInterruptible(true);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	distance = 120; // inches
    	velocity = 24; // inches per second
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	//MAKE THIS IN CORRECT UNITS
    	driveTrain.driveLeftVelocity(Utility.inchesPerSecondToTalonVelocity(velocity));
    	driveTrain.driveRightVelocity(Utility.inchesPerSecondToTalonVelocity(velocity));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(driveTrain.getLeftPosition() > distance && driveTrain.getRightPosition() > distance)
        {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
