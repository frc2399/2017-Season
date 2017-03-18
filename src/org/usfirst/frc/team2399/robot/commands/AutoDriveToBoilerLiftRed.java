package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to boiler-side lift, place gear, and back away from lift
 * Gear Collector remains open for about 3 seconds before closing
 *
 *  For this command, robot starts on rightmost edge of the bumper on the outmost edge of the tape.
 */
public class AutoDriveToBoilerLiftRed extends CommandGroup {
	private double angleAdjustment = 1;

    public AutoDriveToBoilerLiftRed() {
       addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
       addSequential(new DriveDistanceHoldAngle(16, 40, 1)); //distance, velocity, timeout
       addSequential(new DriveAngle(angleAdjustment*35, 1.5)); //setpoint, timeout
       addSequential(new DriveDistanceHoldAngle(89.25, 40, 2.75));
       addSequential(new DriveAngle(angleAdjustment* -90, 2));
       addSequential(new DriveDistanceHoldAngle(38, 40, 1.5));
       addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
       addParallel(new Drive());
       addSequential(new WaitCommand(3));
       addSequential(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
       addSequential(new DriveDistanceHoldAngle(-20, 40, 1.25));
       addSequential(new DriveAngle(angleAdjustment* 62, 1.9));
       addSequential(new DriveDistanceHoldAngle(108, 40, 3.25));
    }
}
