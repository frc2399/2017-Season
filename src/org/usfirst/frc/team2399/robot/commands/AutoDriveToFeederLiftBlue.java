package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveToFeederLiftBlue extends CommandGroup {
	public double angleAdjustment = -1;

    public AutoDriveToFeederLiftBlue() {
    	addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
        addSequential(new DriveDistanceHoldAngle(89, 40, 3));
        addSequential(new DriveAngle(angleAdjustment*60, 3));
        addSequential(new DriveDistanceHoldAngle(69, 40, 2));
        addSequential(new GearCollect(true));
    }
}
