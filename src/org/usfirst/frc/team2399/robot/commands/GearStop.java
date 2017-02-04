package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearStop extends Command {
	
	private GearCollector gearCollector = Robot.gearCollector;
	
    public GearStop() {
    	requires(gearCollector);
    	setInterruptible(true);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearCollector.setGearInSolenoid(!RobotMap.GEAR_SOLENOID_IN_CONSTANT);
    	gearCollector.setGearOutSolenoid(!RobotMap.GEAR_SOLENOID_OUT_CONSTANT);
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
