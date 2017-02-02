package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterStop extends Command {
	
	private Shooter shooter = Robot.shooter;

    public ShooterStop() {
    	requires(shooter);
    	setInterruptible(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    //When the command is run, the shooter is set to this speed
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setShooterSpeed(RobotMap.SHOOTER_STOP_CONSTANT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
