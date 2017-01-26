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
	private double goalDistance;
	private double time;
	private Timer timer = new Timer();
	private double distancePConstant = RobotMap.DISTANCE_P_CONSTANT;
	
	public DriveTrain() {
		leftFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		rightFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		leftBackTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_BACK_TALON_ADDRESS);
		rightBackTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS);
		
		leftFrontTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftFrontTalon.changeControlMode(TalonControlMode.Speed);
		rightFrontTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightFrontTalon.changeControlMode(TalonControlMode.Speed);
		
		timer.start();
		
		/**
		 * TODO: Figure out how to set distance per pulse
		 */
	}
	
	public void driveLeft(double leftSpeed) {
		leftFrontTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftBackTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
	}

	public void driveRight(double rightSpeed) {
		rightFrontTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightBackTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
	}

	/**
	 * Methods that get the left and right positions of the robot
	 */
	
	//TODO: Is this the right method?
	public double getLeftPosition(){
		return leftFrontTalon.getPosition();
	}
	
	public double getRightPosition(){
		return rightFrontTalon.getPosition();
	}
	
	/**
	 * Methods that get/set the left and right positions of where we want to be
	 */
	
	public void setLeftDesiredPosition(double goalDistance){
		leftFrontTalon.reset();
		this.goalDistance = goalDistance;
	}
	
	public void setRightDesiredPosition(double goalDistance){
		rightFrontTalon.reset();
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
	 * TODO: Write comment
	 */
	
	public void moveToLeftDistance()
	{
		double currentTime = timer.get();

		if (currentTime > RobotMap.DRIVE_LOOP_HERTZ_CONSTANT)
		{
			double error = getLeftDesiredDistance() - getLeftPosition();
			double pOutput = error * distancePConstant;
			driveLeft(pOutput);
			timer.reset();
		}	
	}
	
	public void moveToRightDistance()
	{
		double currentTime = timer.get();

		if (currentTime > RobotMap.DRIVE_LOOP_HERTZ_CONSTANT)
		{
			double error = getLeftDesiredDistance() - getLeftPosition();
			double pOutput = error * distancePConstant;
			driveLeft(pOutput);
			timer.reset();
		}	
	}
	
	/**
	 * Methods that calculate the error in distance from where we are to where we want to be
	 */
	
	public double calculateLeftDistanceError(){
		return getLeftDesiredDistance() - getLeftPosition();
		
	}
	
	public double calculateRightDistanceError(){
		return getRightDesiredDistance() - getRightPosition();
		
	}
	
	/**
	 * Methods to increment, decrement, and get the angle constant
	 */
	
	public void incrementAngleConstant(){
		distancePConstant += RobotMap.DISTANCE_INCREMENT_CONSTANT;
	}
	
	public void decrementAngleConstant(){
		distancePConstant -= RobotMap.DISTANCE_DECREMENENT_CONSTANT;
	}
	
	public double getAngleConstant(){
		return distancePConstant;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}

}
