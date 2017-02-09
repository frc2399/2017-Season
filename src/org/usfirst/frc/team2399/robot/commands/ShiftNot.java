package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.Robot;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets both solenoids to false (idle)
 */
public class ShiftNot extends Command {
	
	private Shifter shifter = Robot.shifter;

    public ShiftNot() {
    	requires(shifter);
    	setInterruptible(true);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     *  Both solenoids are set to false (subsystem is idle)
     */
    protected void execute() {
    	shifter.setShifterHotSolenoid(!RobotMap.SHIFTER_SOLENOID_HOT);
    	shifter.setShifterDangerousSolenoid(!RobotMap.SHIFTER_SOLENOID_DANGEROUS);
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     *  Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     *  Called when another command which requires one or more of the same
     *  subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
