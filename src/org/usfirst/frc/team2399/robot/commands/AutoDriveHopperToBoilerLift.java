package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to hopper, drive backwards, turn, drive to the boiler-side lift, place gear while shooter
 */
public class AutoDriveHopperToBoilerLift extends CommandGroup {
	private double angleAdjustment = 1;
    public AutoDriveHopperToBoilerLift(boolean redAlliance) {
    	if(redAlliance == true){
    		angleAdjustment = -1;
    	}
    	addSequential(new AutoDriveToHopper(redAlliance));
    	addSequential(new DriveDistanceHoldAngle(-62,36,7));
    	addSequential(new DriveAngle(angleAdjustment*62,3));
    	addSequential(new DriveDistanceHoldAngle(69,36,7)); //???
    	addSequential(new AutoPlaceGearAndShoot());
    }
}
