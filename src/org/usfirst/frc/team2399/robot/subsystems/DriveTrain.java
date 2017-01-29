package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.JoyDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private CANTalon leftFrontTalon;
	private CANTalon rightFrontTalon;
	private CANTalon leftBackTalon;
	private CANTalon rightBackTalon;
	private CANTalon leftMiddleTalon;
	private CANTalon rightMiddleTalon;
	
	private double goalDistance;
	private double distancePConstant;
	
	private double anglePConstant = RobotMap.ANGLE_P_CONSTANT;
	private double desiredAngle;
	
	public DriveTrain() {
		leftFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		rightFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		leftBackTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_BACK_TALON_ADDRESS);
		rightBackTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS);
		leftMiddleTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_MIDDLE_TALON_ADDRESS);
		rightMiddleTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_MIDDLE_TALON_ADDRESS);
		
		/**
		 * Sets the type of encoder to be routed through the Talon, as well as what mode the Talon is in
		 */
		leftFrontTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightFrontTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		leftMiddleTalon.changeControlMode(TalonControlMode.Follower);
		leftMiddleTalon.set(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		leftBackTalon.changeControlMode(TalonControlMode.Follower);
		leftBackTalon.set(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		rightMiddleTalon.changeControlMode(TalonControlMode.Follower);
		rightMiddleTalon.set(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		rightBackTalon.changeControlMode(TalonControlMode.Follower);
		rightBackTalon.set(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		
		//TODO: Set actual values for these constants
		leftFrontTalon.setF(0);
		leftFrontTalon.setP(0);
		leftFrontTalon.setI(0);
		leftFrontTalon.setD(0);
		
		rightFrontTalon.setF(0);
		rightFrontTalon.setP(0);
		rightFrontTalon.setI(0);
		rightFrontTalon.setD(0);
	
		
	}
	
	//Look at p.79 for a closed-loop walkthrough
	//We should put in a method for resetting the calculated values when driver control begins
	public void driveLeftVelocity(double leftSpeed) {
		leftFrontTalon.changeControlMode(TalonControlMode.Speed);
		
		leftFrontTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftBackTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftMiddleTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
	}
	
	public void driveLeftPercent(double leftSpeed) {
		leftFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
		leftFrontTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftBackTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftMiddleTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
	}
	

	public void driveRightVelocity(double rightSpeed) {
		rightFrontTalon.changeControlMode(TalonControlMode.Speed);
		
		rightFrontTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightBackTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightMiddleTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
	}
	
	public void driveRightPercent(double rightSpeed) {
		rightFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
		rightFrontTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightBackTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightMiddleTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
	}

	
	
	//Multiplied by the circumference of the wheel for scaling
	public double getLeftPosition(){
		return leftFrontTalon.getPosition() * RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE_CONSTANT;
	}
	
	public double getRightPosition(){
		return rightFrontTalon.getPosition() * RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE_CONSTANT;
	}
	
	/**
	 * Methods that get/set the left and right positions of where we want to be
	 */
	
	public void setLeftDesiredPosition(double goalDistance){
		leftFrontTalon.setPosition(0);
		this.goalDistance = goalDistance;
	}
	
	public void setRightDesiredPosition(double goalDistance){
		rightFrontTalon.setPosition(0);
		this.goalDistance = goalDistance;
	}
	
	public double getLeftDesiredDistance()
	{
		return goalDistance;
	}
	
	public double getRightDesiredDistance()
	{
		return goalDistance;
	}
	

	
	/**
	 * Methods that calculate the error in distance from where we are to where we want to be
	 */
	
	public double calculateLeftDistanceError(){
		return rightFrontTalon.getError();
		
	}
	
	public double calculateRightDistanceError(){
		return leftFrontTalon.getError();
		
	}
	
	/**
	 * Methods to increment, decrement, and get the angle constant
	 */
	
	public void incrementDistanceConstant(){
		distancePConstant += RobotMap.DISTANCE_INCREMENT_CONSTANT;
	}
	
	public void decrementDistanceConstant(){
		distancePConstant -= RobotMap.DISTANCE_DECREMENENT_CONSTANT;
	}
	
	public double getDistanceConstant(){
		return distancePConstant;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}
	
	/**
	 * Sets desired angle for driving at an angle
	 * @param goalAngle
	 */
	public void setDesiredAngle(double goalAngle) {
		desiredAngle = goalAngle;
	}
	
	public void moveToAngle() {
		//TODO: p loop from getCurrentAngle()
	}
	
	/**
	 * Questions
	 * -How do we increment/decrement constants with a Talon? Do we need to?
	 * -Is there a reason that we can't use the NavX?
	 * -Close-loop velocity walkthrough on p.79
	 * 
	 * To Do
	 * -Angle methods require position calculations using arcs
	 */
	
		
}
