package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to boiler-side lift, place gear, and back away from lift
 * Gear Collector remains open for about 3 seconds before closing 
 */
public class AutoDriveToBoilerLift extends CommandGroup {
	private double angleAdjustment = 1;
	
    public AutoDriveToBoilerLift(DriverStation.Alliance alliance) {
    	if(alliance == DriverStation.Alliance.Red){
    		angleAdjustment = -1;
    	}
       /*addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
       addSequential(new DriveDistanceHoldAngle(16, 36, 3));
       addSequential(new DriveAngle(angleAdjustment*-35, 3));
       addSequential(new DriveDistanceHoldAngle(89.25, 36, 5)); 
       addSequential(new DriveAngle(angleAdjustment* 90, 4));
       addSequential(new DriveDistanceHoldAngle(38, 36, 4));
       addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
       addParallel(new Drive());
       addSequential(new WaitCommand(3));
       addSequential(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
       addSequential(new DriveDistanceHoldAngle(-20, 36, 3));
       addSequential(new DriveAngle(angleAdjustment* 62,4));
       addSequential(new DriveDistanceHoldAngle(108,36,5));
       */
    	
    	/*
    	 * shift to hot
    	 * drive 116  in
    	 * negative 45 degree turn
    	 * drive forward 38 inches
    	 * deposit gear
    	 * wait 2 seconds
    	 * move gear holder back to original position
    	 * move backwards 38 inches
    	 * rotate positive 45 degrees 
    	 * drive forward 82 inches
    	 */
    	addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
    	addSequential(new DriveDistanceHoldAngle(116, 36, 4));
    	addSequential(new DriveAngle(angleAdjustment*-45, 3));
    	addSequential(new DriveDistanceHoldAngle(38, 36, 2));
    	addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
    	addSequential(new WaitCommand(2));
    	addSequential(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
    	addSequential(new DriveDistanceHoldAngle(38, 36, 2));
    	addSequential(new DriveAngle(angleAdjustment*45, 3));
    	addSequential(new DriveDistanceHoldAngle(82, 36, 3));
    }
}
