package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.GearIn;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearCollector extends Subsystem {

	
	private Solenoid gearSolenoid;
	
	
	
	public GearCollector(){
		gearSolenoid = new Solenoid(RobotMap.GEAR_SOLENOID_PORT);
	}
	
	/**
	 * Sets the solenoid to the inputted value
	 * @param setGear
	 */
	public void setGearSolenoid(boolean setGear){
		gearSolenoid.set(setGear);
	}
	
	
	/**
	 * Gets the value of the solenoid for testing purposes
	 * @return
	 */
	public boolean getGearSolenoid(){
		return gearSolenoid.get();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	/**
	 * Default state of the gear collecting mechanism is "in'
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new GearIn());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

