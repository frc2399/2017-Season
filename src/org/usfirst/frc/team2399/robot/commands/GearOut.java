package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the gear mechanism to the out position
 */
public class GearOut extends Command {
	
	private GearCollector gearCollector = Robot.gearCollector;

    public GearOut() {
    	requires(gearCollector);
    	setInterruptible(true);
    }

    /**
     * Called just before this Command runs the first time
     * Sets the amount of the time the command runs for
     */
    protected void initialize() {
    	setTimeout(RobotMap.GEAR_TIMER);
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     * Solenoid is set to the "out" value and the command continues running until the timer runs out
     */
    protected void execute() {
    	gearCollector.setGearOutSolenoid(RobotMap.GEAR_SOLENOID_OUT);
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute()
     * When the time passed exceeds the time established in initialize(), isTimedOut() returns true, 
     * making isFinished() return true and stopping the command
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     *subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
