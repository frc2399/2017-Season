package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.JoyDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private CANTalon leftFrontTalon;
	private CANTalon rightFrontTalon;
	private CANTalon leftBackTalon;
	private CANTalon rightBackTalon;
	private CANTalon leftMiddleTalon;
	private CANTalon rightMiddleTalon;
	
	private AHRS Navx = new AHRS(SPI.Port.kMXP);
	
	private double goalDistance;

	private double desiredAngle;
	private double anglePConstant = RobotMap.DRIVE_ANGLE_P;

	
	
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
		
		/**
		 * Talons that don't have encoders are set to Follower mode
		 * The line below that tells them which Talon to follow
		 * They will go to whichever mode the Talon they're set to follow 
		 */
		leftMiddleTalon.changeControlMode(TalonControlMode.Follower);
		leftMiddleTalon.set(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		leftBackTalon.changeControlMode(TalonControlMode.Follower);
		leftBackTalon.set(RobotMap.DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS);
		rightMiddleTalon.changeControlMode(TalonControlMode.Follower);
		rightMiddleTalon.set(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		rightBackTalon.changeControlMode(TalonControlMode.Follower);
		rightBackTalon.set(RobotMap.DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS);
		
		/**
		 * If the forward constant is negative (see boolean in RobotMap) reverse the output of
		 * either the sensor or the motor
		 */
		
		leftFrontTalon.reverseSensor(RobotMap.REVERSE_LEFT_ENCODER_OUTPUT);
	
		rightFrontTalon.reverseSensor(RobotMap.REVERSE_RIGHT_ENCODER_OUTPUT);
		
		/**
		 * Sets all constants for PID loops
		 * We won't be using I and D, and they are set to 0 automatically, but
		 * for clarity and safety's sake, they're set to zero here
		 * TODO: Set actual values for F and P
		 */
		leftFrontTalon.setF(0);
		leftFrontTalon.setP(0);
		leftFrontTalon.setI(0);
		leftFrontTalon.setD(0);
		
		rightFrontTalon.setF(0);
		rightFrontTalon.setP(0);
		rightFrontTalon.setI(0);
		rightFrontTalon.setD(0);
	
		
	}
	
	/**
	 * Velocity - this is better for programmers due to its precision
	 * Percent - this is better for drivers because it feels more natural
	 * TODO: Look at p.79 for a closed-loop walkthrough
	 * TODO: Create a method for resetting calculated control values when
	 * driver control (percent mode) begins; look through Talon methods first
	 * TODO: Does Follower mode apply to speed as well as Control Mode?
	 * @param leftSpeed rotations per minute
	 */
	public void driveLeftVelocity(double leftSpeed) {
		if(leftSpeed >= RobotMap.VELOCITY_LOWER_SOFT_LIMIT && leftSpeed <= RobotMap.VELOCITY_UPPER_SOFT_LIMIT)
		{
			leftFrontTalon.changeControlMode(TalonControlMode.Speed);
			leftFrontTalon.set(leftSpeed*RobotMap.DRIVETRAIN_FORWARD_LEFT);
		}
	}
	
	public void driveLeftPercent(double leftSpeed) {
		if(leftSpeed >= RobotMap.PERCENT_LOWER_SOFT_LIMIT && leftSpeed <= RobotMap.PERCENT_UPPER_SOFT_LIMIT )
		{
			leftFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
			leftFrontTalon.set(leftSpeed*RobotMap.DRIVETRAIN_FORWARD_LEFT);
		}
	}
	

	public void driveRightVelocity(double rightSpeed) {
		if(rightSpeed >= RobotMap.VELOCITY_LOWER_SOFT_LIMIT && rightSpeed <= RobotMap.VELOCITY_UPPER_SOFT_LIMIT)
		{
			rightFrontTalon.changeControlMode(TalonControlMode.Speed);
		
			rightFrontTalon.set(rightSpeed*RobotMap.DRIVETRAIN_FORWARD_RIGHT);
		}
	}
	
	public void driveRightPercent(double rightSpeed) {
		if(rightSpeed >= RobotMap.PERCENT_LOWER_SOFT_LIMIT && rightSpeed <= RobotMap.PERCENT_UPPER_SOFT_LIMIT )
		{
			rightFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
			rightFrontTalon.set(rightSpeed*RobotMap.DRIVETRAIN_FORWARD_RIGHT);
		}
	}

	
	
	/**
	 * Gets the current position of the robot
	 * Multiplied by the circumference of the wheel for scaling
	 * @return
	 */
	public double getLeftPosition(){
		return leftFrontTalon.getPosition() * RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE;
	}
	
	public double getRightPosition(){
		return rightFrontTalon.getPosition() * RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE;
	}
	
	/**
	 * Methods that get/set the left and right positions of where we want to be
	 * setPosition zeros out the encoder data, so that we start at 0
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
	public double returnLeftDistanceError(){
		return rightFrontTalon.getError();	
	}
	
	public double returnRightDistanceError(){
		return leftFrontTalon.getError();
	}
	
	/**
	 * Methods to increment, decrement, and get the distance constant
	 * Method takes in the talon the P constant is being set for, gets the current P, and sets
	 * the P to the current P + the increment/decrement constant
	 */
	
	public void incrementRightDistancePConstant(){
		double currentPConstant = rightFrontTalon.getP();
		rightFrontTalon.setP(currentPConstant += RobotMap.DRIVE_DISTANCE_INCREMENT);
	}
	
	public void incrementLeftDistancePConstant(){
		double currentPConstant = leftFrontTalon.getP();
		leftFrontTalon.setP(currentPConstant += RobotMap.DRIVE_DISTANCE_INCREMENT);
	}
	
	public void decrementRightDistancePConstant(){
		double currentPConstant = rightFrontTalon.getP();
		rightFrontTalon.setP(currentPConstant -= RobotMap.DRIVE_DISTANCE_DECREMENT);
	}
	
	public void decrementLeftDistancePConstant(){
		double currentPConstant = leftFrontTalon.getP();
		leftFrontTalon.setP(currentPConstant -= RobotMap.DRIVE_DISTANCE_DECREMENT);
	}
	
	public double returnRightDistanceConstant(){
		return rightFrontTalon.getP();
	}
	
	public double returnLeftDistanceConstant(){
		return leftFrontTalon.getP();
	}
	
	/**
	 * Returns angle relative to initial position OR relative to field, measured from 180 to -180 degrees
	 * Initial position - gyro in robot-oriented mode
	 * Relative to field - gyro in field-oriented mode
	 * @return
	 */
	public double getCurrentAngle()
	{
		return Navx.getYaw();
	}
	
	public double getTheAngle()
	{
		if(Navx.getAngle() <= 360)
		return Navx.getAngle();
		else
		{
			return Navx.getAngle() % 360;
		}
	}
	public void setDesiredAngle(double goalAngle)
	{
		desiredAngle = goalAngle;
	}
	
	public double getDesiredAngle()
	{
		return desiredAngle;
	}
	
	/**
	 * TODO: Simplify this error calculation
	 * @return
	 */
	public double calculateAngleError()
	{
		double newDesiredAngle;
		
		if(getCurrentAngle()-180 >= getDesiredAngle())
		{
			newDesiredAngle = getDesiredAngle() + 360;
		}
		else if(getCurrentAngle()+180 < getDesiredAngle())
		{
			newDesiredAngle = getDesiredAngle() - 360;
		}
		else
		{
			newDesiredAngle = getDesiredAngle();
		}
		
		return newDesiredAngle - getCurrentAngle();
	}
	
	public void moveToAngle()
	{
		double pOutput = calculateAngleError() * anglePConstant;
		driveRightVelocity(-pOutput);
		driveLeftVelocity(pOutput);
	}
	
	public boolean isDriveAngleFinished()
	{
		return Math.abs(calculateAngleError()) <= RobotMap.DRIVE_ANGLE_ERROR;
	}
	
	public void incrementAnglePConstant()
	{
		anglePConstant += RobotMap.DRIVE_ANGLE_INCREMENT;
	}
	
	public void decrementAnglePConstant()
	{
		anglePConstant -= RobotMap.DRIVE_ANGLE_DECREMENT;
	}
	
	public double getAnglePConstant()
	{
		return anglePConstant;
	}
	
	public void driveAtAngleToDistance()
	{
		double anglePOutput = calculateAngleError() * anglePConstant;
		double leftPOutput = leftFrontTalon.getError() * leftFrontTalon.getP();
		double rightPOutput = rightFrontTalon.getError() * rightFrontTalon.getP();
		
		driveRightVelocity(rightPOutput * RobotMap.DRIVE_MIXED_LINEAR - anglePOutput * RobotMap.DRIVE_MIXED_ANGULAR);
		driveLeftVelocity(leftPOutput * RobotMap.DRIVE_MIXED_LINEAR + anglePOutput * RobotMap.DRIVE_MIXED_ANGULAR);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}		
}
