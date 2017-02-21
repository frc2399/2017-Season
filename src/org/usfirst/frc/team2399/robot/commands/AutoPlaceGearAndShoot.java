package org.usfirst.frc.team2399.robot.commands;

import org.usfirst.frc.team2399.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Place gear and shoot
 */
public class AutoPlaceGearAndShoot extends CommandGroup {

    public AutoPlaceGearAndShoot() {
    	addParallel(new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT));
    	//addParallel(new Shoot(RobotMap.SHOOTER_FORWAR));
    }
}
