package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to the center lift, place gear, drive away from the lift
 * Keeps the Gear Collector open for about 3 seconds before closing it
 */
public class AutoDriveToCenterLift extends CommandGroup {
	
    public AutoDriveToCenterLift() {
    	addSequential(new DriveDistanceHoldAngle(69, 40, 3));
    }
}
