package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Climb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	/**
	 * New Talon created
	 */
	
	private CANTalon climbTalon;
	
	
	/**
	 * Talon assigned an address in RobotMap
	 */
	public Climber()
	{
		climbTalon = new CANTalon(RobotMap.CLIMBER_TALON_ADDRESS);
	}
	
	/**
	 * Method that sets the climber Talon to go at an inputted speed
	 * Is multiplied by the forward constant to ensure that it goes in the correct direction
	 * @param speed
	 */
	public void setClimberSpeed(double speed)
	{
		climbTalon.set(speed * RobotMap.CLIMBER_FORWARD);
	
	}

	/**
	 * Establishes the default state of the climber
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Climb(RobotMap.CLIMB_STOP));

	}



}
