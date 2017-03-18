package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to boiler-side lift, place gear, and back away from lift
 * Gear Collector remains open for about 3 seconds before closing
 *
 * For this command, robot starts on leftmost edge of the bumper on the outmost edge of the tape.
 */
public class AutoDriveToBoilerLiftBlue extends CommandGroup {
	private double angleAdjustment = 1;

    public AutoDriveToBoilerLiftBlue() {
       addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
       addSequential(new DriveDistanceHoldAngle(16, 40, 2));
       addSequential(new DriveAngle(angleAdjustment*-35, 3));
       addSequential(new DriveDistanceHoldAngle(89.25, 40, 4));
       addSequential(new DriveAngle(angleAdjustment* 90, 4));
       addSequential(new DriveDistanceHoldAngle(38, 40, 2));
    }
}
