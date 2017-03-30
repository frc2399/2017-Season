package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveToHopperLiftBlue extends CommandGroup {
	private double angleAdjustment = 1;
    public AutoDriveToHopperLiftBlue() {
    	
    	addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
        addSequential(new DriveDistanceHoldAngle(16, 40, 2));
        addSequential(new DriveAngle(angleAdjustment*35, 3));
        addSequential(new DriveDistanceHoldAngle(89.25, 40, 4));
        addSequential(new DriveAngle(angleAdjustment* -90, 4));
     //   addSequential(new DriveDistanceHoldAngle(38, 40, 2));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
