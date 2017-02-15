package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoyShoot extends Command {
	
	private double speed;
	private Shooter shooter = Robot.shooter;

    public JoyShoot(double speed) {
    	this.speed = speed;
    	requires(shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    //TODO: Incorporate throttle (see last year's code)
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    //When the command is run, the shooter is set to this speed
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setShooterSpeed(speed * OI.getShooterThrottle());
    }

    /**
     * With whileHeld, command will execute, finish, check if the button is still held, and then
     * run through the command again if necessary, preventing us from being stuck in a loop
     */
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
