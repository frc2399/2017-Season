package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.ShooterStop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private CANTalon shooterTalon;
	
	private Timer timer = new Timer ();
	private double goalSpeed;
	private double shooterSpeedPConstant = RobotMap.SHOOTER_SPEED_P_CONSTANT;

	/**
	 * The encoder is linked directly to the Talon
	 */
	public Shooter(){
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON_ADDRESS);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	shooterTalon.changeControlMode(TalonControlMode.Speed);
    	
    	timer.start();
	}
	
	
	
	//Sets the speed to the inputted speed * the forward constant
	//Position change/10 ms
	 public void setShooterSpeed(double speed){
		 shooterTalon.set(speed * RobotMap.SHOOTER_FORWARD_CONSTANT);
	 }
 
	 //Gets speed from sensor in ticks/100 ms
	 public double getShooterSpeed(){
		 return shooterTalon.getSpeed();
	 }
	 
	 public void setShooterDesiredSpeed(double goalSpeed)
	 {
		 shooterTalon.reset();
		 this.goalSpeed = goalSpeed;
	 }
	 
	 public double getShooterDesiredSpeed()
	 {
		 return goalSpeed;
	 }
	
	 public void shootAtSpeed()
	 {
		 double currentTime = timer.get();
		 
		 if (currentTime > RobotMap.SHOOTER_HERTZ_CONSTANT)
		 {
			 double error = getShooterDesiredSpeed()-getShooterSpeed();
			 double pOutput = error * shooterSpeedPConstant;
			 setShooterSpeed(pOutput);
			 timer.reset();
		 }
	 }
	 
	 public double calculateSpeedError()
	 {
		 return getShooterDesiredSpeed()-getShooterSpeed();
	 }
	 
	 public void incrementSpeedConstant()
	 {
		 shooterSpeedPConstant -= RobotMap.SHOOTER_SPEED_INCREMENT_CONSTANT;
	 }
	 
	 public void decrementSpeedConstant()
	 {
		 shooterSpeedPConstant -= RobotMap.SHOOTER_SPEED_DECREMENT_CONSTANT;
	 }
	 
	 public double getShooterSpeedConstant()
	 {
		 return shooterSpeedPConstant;
	 }

	 //Default is the motor at 0 speed
    public void initDefaultCommand() {
    	setDefaultCommand(new ShooterStop());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

