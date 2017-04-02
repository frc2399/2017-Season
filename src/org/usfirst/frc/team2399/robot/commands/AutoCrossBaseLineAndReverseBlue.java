package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCrossBaseLineAndReverseBlue extends CommandGroup {

	public double angleAdjustment = -1;
    public AutoCrossBaseLineAndReverseBlue() {
       
    	addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
     	addSequential(new DriveDistanceHoldAngle(120, 40, 4));
     	new WaitCommand("show refs we're in the neutral zone", 2);
     	addSequential(new DriveDistanceHoldAngle(-31, 40, 2));
     	addSequential(new DriveAngle(angleAdjustment*60, 2));
    	
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
