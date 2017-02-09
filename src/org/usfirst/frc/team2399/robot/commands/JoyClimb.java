package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Links climbing to joystick control
 */
public class JoyClimb extends Command {
	
	private Climber climber = Robot.climber;
	double speed;
	
	/**
	 * @param speed: the speed inputted into the constructor is assigned to the double created above
	 */
    public JoyClimb(double speed) {
    	this.speed = speed;
    	requires(climber);
    	setInterruptible(true);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     * When command is run, climber is set to the inputted speed (see constructor)
     */
    protected void execute() {
    	climber.setClimberSpeed(speed);
    	
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
