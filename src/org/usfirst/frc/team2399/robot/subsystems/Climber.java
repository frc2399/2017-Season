package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Climb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	private CANTalon climbTalon;
	
	public Climber()
	{
		climbTalon = new CANTalon(RobotMap.CLIMBER_TALON_ADDRESS);
	}
	
	/**
	 * Method that sets the climber Talon to go at the speed passed to the method
	 * Is multiplied by the forward constant to ensure that it goes in the correct direction
	 * @param speed
	 */
	public void setClimberSpeed(double speed)
	{
		climbTalon.set(speed * RobotMap.CLIMBER_FORWARD);
	}
	
	/**
	 * @return current output current
	 */
	public double getClimberSpeed()
	{
		return climbTalon.get();
	}

	/**
	 * Sets climber motor to zero
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Climb(RobotMap.CLIMBER_STOP));
	}
}
