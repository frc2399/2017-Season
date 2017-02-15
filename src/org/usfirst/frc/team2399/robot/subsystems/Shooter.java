package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.Shoot;

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
	 * The encoder is linked directly to the Talon
	 * reverseOutput and reverseSensor reverse their respective devices if give a value of "true" (see RobotMap for 
	 * constants)
	 * FPID constants are now set through the Talon
	 */
	public Shooter() {
		shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON_ADDRESS);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.reverseOutput(RobotMap.SHOOTER_MOTOR_REVERSE_OUTPUT_CONSTANT);
		shooterTalon.reverseSensor(RobotMap.SHOOTER_ENCODER_REVERSE_OUTPUT_CONSTANT);
		
		shooterTalon.setF(0);
		shooterTalon.setP(0);
		shooterTalon.setI(0);
		shooterTalon.setD(0);
	}

	/**
	 * Sets the speed to the inputted speed 
	 * @param speed
	 * Position change/10 ms
	 */
	public void setShooterSpeed(double speed) {
		shooterTalon.set(speed);
	}

	/**
	 *Gets speed from sensor in ticks/100 ms
	 *TODO: Does this need to be converted to ft/s for our purposes?
	 */
	public double getShooterSpeed() {
		return shooterTalon.getSpeed();
	}

    /**
     * Encoder position is set to zero, speed is set to inputted value
     * @param goalSpeed
     */
	public void setShooterDesiredSpeed(double goalSpeed) {
		shooterTalon.setPosition(0);
		this.goalSpeed = goalSpeed;
	}

	public double getShooterDesiredSpeed() {
		return goalSpeed;
	}

    /*
     * Gets the error from the CANTalon
     */
	public double calculateSpeedError() {
		return shooterTalon.getError();
	}

    /*
     * Allows us to Increment and Decrement for testing
     */
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

	/*
	 * Default is the motor at 0 speed(non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Shoot(0));
	}

}
