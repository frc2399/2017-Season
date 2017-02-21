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
    
       addSequential(new AutoDriveTest(19, 24));
       addSequential(new DriveAngle(angleAdjustment*35));
       addSequential(new AutoDriveTest(72.5, 24));
       addSequential(new DriveAngle(angleAdjustment* -90));
       addSequential(new AutoDriveTest(36, 24));
       addSequential(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
       addSequential(new WaitCommand(1));
       addSequential(new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT));
      // addSequential(new AutoDriveTest(-20, 24));
    }
}
