package org.usfirst.frc.team2399.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drive to hopper
 * Waits for 2 seconds for the hopper to drop all the balls
 */
public class AutoDriveToHopper extends CommandGroup {

	private double angleAdjustment = 1;
    public AutoDriveToHopper(boolean redAlliance) {
    	if(redAlliance == true){
    		angleAdjustment = -1;
    	}
    	addSequential(new DriveAngle(angleAdjustment*45));
    	addSequential(new DriveAtAngleForDistance(angleAdjustment*45,150));
    	addSequential(new WaitCommand(2));
    }
}