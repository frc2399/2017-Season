package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.AgitatorStop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Agitator extends Subsystem {

	private CANTalon agitatorTalon;
	
	public Agitator(){
		agitatorTalon = new CANTalon(RobotMap.AGITATOR_TALON_ADDRESS);
		agitatorTalon.changeControlMode(TalonControlMode.Speed);
	}
	
	public void setAgitatorSpeed(double speed){
		agitatorTalon.set(speed);
	}
	
	public double getAgitatorSpeed(){
		return agitatorTalon.getSpeed();
	}
	
	
	/**
	 * TODO: Did I do this right?
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new AgitatorStop());
    }
}

