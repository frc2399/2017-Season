package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Agitator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves agitator motor backwards
 */

public class Agitate extends Command {

	private Agitator agitator = Robot.agitator;
	private double speed;
	
    public Agitate(double speed) {
    	this.speed = speed;
        requires(agitator);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	agitator.setAgitatorSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
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
