package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to boiler-side lift, place gear, and back away from lift
 * Gear Collector remains open for about 3 seconds before closing 
 */
public class AutoDriveToBoilerLift extends CommandGroup {
	private double angleAdjustment = 1;
	
    public AutoDriveToBoilerLift(boolean redAlliance) {
    	if(redAlliance == true){
    		angleAdjustment = -1;
    	}
       addSequential(new DriveAngle(angleAdjustment*0));
       addSequential(new DriveAtAngleForDistance(angleAdjustment*0,120));
       addSequential(new DriveAngle(angleAdjustment*45));
       addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
       addSequential(new WaitCommand(3));
       addParallel(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
       addParallel(new DriveAtAngleForDistance(angleAdjustment*0,-33));
    }
}
