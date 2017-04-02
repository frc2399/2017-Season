package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveToFeederLiftRed extends CommandGroup {
	private double angleAdjustment = 1;
    public AutoDriveToFeederLiftRed() {
    	
    	SmartDashboard.putString("feeder side auto running", "yes");
    	DriverStation.reportError("Side Auto Running", false);
    	addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
        addSequential(new DriveDistanceHoldAngle(89, 40, 3));
        addSequential(new DriveAngle(angleAdjustment*60, 2));
        addSequential(new DriveDistanceHoldAngle(69, 40, 2));
        addSequential(new GearCollect(true));
    }
}
