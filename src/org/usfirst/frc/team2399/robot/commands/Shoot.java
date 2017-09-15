package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves shooter motor
 * TODO: Incorporate throttle
 */
public class Shoot extends Command {
	
	private double minSpeed;
	private double maxSpeed;

	private Shooter shooter = Robot.shooter;

    public Shoot(double minSpeed, double maxSpeed) {
    	this.minSpeed = minSpeed;
    	this.maxSpeed = maxSpeed;
    	requires(shooter);
    }

    protected void initialize() {
    	shooter.setShooterSpeedMode(); 
    	//shooter.shooterEncoderReset();
    }

    /**
     * When the command is run, the shooter is set to this speed
     */
    protected void execute() {
    	//shooter.setShooterSpeed((minSpeed + (maxSpeed - minSpeed) * OI.getShooterThrottle()));
    }

    /**
     * In conjunction with buttons created in OI that operate whileHeld and with the command returning true, 
     * command will execute, finish, check if the button is still held, and then run through the command again if necessary, 
     * preventing us from being stuck in a loop
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
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
