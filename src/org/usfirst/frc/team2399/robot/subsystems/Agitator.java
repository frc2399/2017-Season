package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Agitate;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Agitator extends Subsystem {

	private CANTalon agitatorTalon;

	public Agitator(){
		agitatorTalon = new CANTalon(RobotMap.AGITATOR_TALON_ADDRESS);
		
		/**
		 * Changed control mode to percent
		 */
		agitatorTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	/**
	 * Sets agitator Talon speed
	 * @param speed 
	 */
	public void setAgitatorSpeed(double speed){
		agitatorTalon.set(speed);
	}
	
	/**
	 * Gets agitator Talon speed
	 * @return agitator Talon speed
	 */
	public double getAgitatorSpeed(){
		return agitatorTalon.getSpeed();
	}
	
	/**
	 * Sets Agitator speed to zero 
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new Agitate(RobotMap.AGITATOR_STOP));
    }
}

