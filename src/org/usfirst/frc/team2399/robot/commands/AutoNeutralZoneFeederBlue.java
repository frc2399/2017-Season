package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoNeutralZoneFeederBlue extends CommandGroup {

    public AutoNeutralZoneFeederBlue() {
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
        addSequential(new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS));
    	addSequential(new DriveDistanceHoldAngle(250, 40, 8));
    	/* turns 90 degrees and drives to the center of the field
    	 * addSequential(new DriveAngle(-90, 3));
    	 * addSequential(new DriveDistanceHoldAngle(72, 40, 3));
    	*/
    }
}
