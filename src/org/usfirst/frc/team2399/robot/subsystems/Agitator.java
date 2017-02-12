package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Agitate;

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
		agitatorTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void setAgitatorSpeed(double speed){
		agitatorTalon.set(speed);
	}
	
	public double getAgitatorSpeed(){
		return agitatorTalon.getSpeed();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Agitate(RobotMap.AGITATOR_STOP));
    }
}

