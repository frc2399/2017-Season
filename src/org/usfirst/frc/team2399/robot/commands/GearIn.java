package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearIn extends Command {
	
	private GearCollector gearCollector = Robot.gearCollector;

    public GearIn() {
    	requires(gearCollector);
    	setInterruptible(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     * Sets the amount of the time the command runs for
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(RobotMap.GEAR_TIMER);
    }

    /**
     * Solenoid is set to the "in" value and the command continues running until the timer runs out
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearCollector.setGearSolenoid(RobotMap.GEAR_SOLENOID_IN_CONSTANT);
    }

    /**
     * When the time passed exceeds the time established in initialize(), isTimedOut() returns true, 
     *  making isFinished() return true and stopping the command
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return gearCollector.getGearSolenoid() == RobotMap.GEAR_SOLENOID_IN_CONSTANT;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
