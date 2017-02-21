package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to the center lift, place gear, drive away from the lift
 * Keeps the Gear Collector open for about 3 seconds before closing it
 */
public class AutoDriveToCenterLift extends CommandGroup {

	//private double angleAdjustment = 1;
	
    public AutoDriveToCenterLift() {
    	//if(redAlliance == true){
    	//	angleAdjustment = -1;
    	//}
    	//addSequential(new AutoDriveTest(19, 36));
    	//addSequential(new DriveAngle(angleAdjustment*30));
    	addSequential(new AutoDriveTest(69,36));
    	//addSequential(new DriveAngle(angleAdjustment*-30));
    	//addSequential(new AutoDriveTest(24,36));
    	addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
    	addSequential(new WaitCommand(3));
    	addParallel(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
    	addParallel(new AutoDriveTest(-20,36));
    }
}
