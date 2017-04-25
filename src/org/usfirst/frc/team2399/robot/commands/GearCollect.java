package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Operates gear pneums
 */
public class GearCollect extends Command {
	
	private GearCollector gearCollector = Robot.gearCollector;
	private boolean open;

	/**
	 * @param open if true, gear collector will open, if false, gear collector will close
	 */
    public GearCollect(boolean open) {
    	this.open = open;
    	requires(gearCollector);
    	setInterruptible(true);
    }

    /**
     * Called just before this Command runs the first time
     * Sets the amount of the time the command runs for
     */
    protected void initialize() {
    	setTimeout(RobotMap.GEAR_TIMER);
    	SmartDashboard.putBoolean("In", !open);
		SmartDashboard.putBoolean("Out", open);
	
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     * Solenoids set to values passed to the constructor
     */
    protected void execute() {
    	gearCollector.setGearInSolenoid(!open);
    	gearCollector.setGearOutSolenoid(open);
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
