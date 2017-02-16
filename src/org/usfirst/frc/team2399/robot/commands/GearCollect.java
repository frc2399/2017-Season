package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Operates gear pneums
 */
public class GearCollect extends Command {
	
	private GearCollector gearCollector = Robot.gearCollector;
	private boolean gearIn;
	private boolean gearOut;

	/**
	 * @param gearIn: Should be set to either the RobotMap value (on) or !RobotMap value (off)
	 * @param gearOut: Should be set to either the RobotMap value (on) or !RobotMap value (off)
	 */
    public GearCollect(boolean gearIn,boolean gearOut) {
    	this.gearIn = gearIn;
    	this.gearOut = gearOut;
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
     * Solenoids set to values passed to the constructor
     */
    protected void execute() {
    	gearCollector.setGearInSolenoid(gearIn);
    	gearCollector.setGearOutSolenoid(gearOut);
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
     *Called when another command which requires one or more of the same
     *subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
