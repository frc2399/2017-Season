package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.ShooterStop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private CANTalon shooterTalon;
	
	private Timer timer = new Timer ();
	private double desiredPosition;

	/**
	 * The encoder is linked directly to the Talon
	 */
	public Shooter(){
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON_ADDRESS);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    	
    	//TODO: Look in manual for how to do Distance Per Pulse for Talons
    	//shooterEncoder.setDistancePerPulse(RobotMap.SHOOTER_DISTANCE_PER_PULSE);
    	timer.stop();
	}
	
	
	//Sets the speed to the inputted speed * the forward constant
	 public void setShooterSpeed(double speed){
		 shooterTalon.set(speed * RobotMap.SHOOTER_FORWARD_CONSTANT);
	 }
 
	 public double getShooterSpeed(){
		 return shooterTalon.getSpeed();
	 }

	 //Default is the motor at 0 speed
    public void initDefaultCommand() {
    	setDefaultCommand(new ShooterStop());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

