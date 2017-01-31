package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.ShooterStop;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private CANTalon shooterTalon;
	private double goalSpeed;
	private double shooterSpeedPConstant = RobotMap.SHOOTER_SPEED_P_CONSTANT;

	/**
	 * The encoder is linked directly to the Talon Sets the PID constants
	 */
	public Shooter() {
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON_ADDRESS);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.reverseOutput(RobotMap.SHOOTER_FORWARD_CONSTANT == -1);
		shooterTalon.reverseSensor(false);
		shooterTalon.setF(0);
		shooterTalon.setP(0);
		shooterTalon.setI(0);
		shooterTalon.setD(0);
	}

	// Sets the speed to the inputted speed * the forward constant
	// Position change/10 ms
	public void setShooterSpeed(double speed) {
		shooterTalon.set(speed * RobotMap.SHOOTER_FORWARD_CONSTANT);
	}

	/**
	 *
	 */

	// Gets speed from sensor in ticks/100 ms
	public double getShooterSpeed() {
		return shooterTalon.getSpeed();
	}

//Reset the desired Speed by the position being zero
	public void setShooterDesiredSpeed(double goalSpeed) {
		shooterTalon.setPosition(0);
		this.goalSpeed = goalSpeed;
	}

	public double getShooterDesiredSpeed() {
		return goalSpeed;
	}

// Gets the error from the CANTalon
	public double calculateSpeedError() {
		return shooterTalon.getError();
	}

// Allows us to Increment and Decrement for accuracy 
	public void incrementSpeedConstant() {
		double currentPConstant = shooterTalon.getP();
		shooterTalon.setP(currentPConstant += RobotMap.SHOOTER_SPEED_INCREMENT_CONSTANT);
	}

	public void decrementSpeedConstant() {
		double currentPConstant = shooterTalon.getP();
		shooterTalon.setP(currentPConstant -= RobotMap.SHOOTER_SPEED_DECREMENT_CONSTANT);
	}

	public double getShooterSpeedConstant() {
		return shooterSpeedPConstant;
	}

	// Default is the motor at 0 speed
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterStop());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}
