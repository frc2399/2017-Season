package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.GearIn;
import org.usfirst.frc.team2399.robot.commands.GearStop;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearCollector extends Subsystem {

	
	private Solenoid gearOutSolenoid;
	private Solenoid gearInSolenoid;
	
	
	
	public GearCollector(){
		gearOutSolenoid = new Solenoid(RobotMap.PCM_ADDRESS, RobotMap.GEAR_OUT_SOLENOID_PORT);
		gearInSolenoid = new Solenoid(RobotMap.PCM_ADDRESS, RobotMap.GEAR_IN_SOLENOID_PORT);
	}
	
	/**
	 * Sets the solenoid to the inputted value
	 * @param setGear
	 */
	public void setGearOutSolenoid(boolean setGearOut){
		gearOutSolenoid.set(setGearOut);
	}
	
	public void setGearInSolenoid(boolean setGearIn){
		gearInSolenoid.set(setGearIn);
	}
	
	
	/**
	 * Gets the value of the solenoid for testing purposes
	 * @return
	 */
	
	public boolean getGearOutSolenoid(){
		return gearOutSolenoid.get();
	}
	
	public boolean getGearInSolenoid(){
		return gearInSolenoid.get();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	/**
	 * Default state of the gear collecting mechanism is "in'
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new GearStop());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

