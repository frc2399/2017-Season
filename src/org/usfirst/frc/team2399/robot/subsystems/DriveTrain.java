package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.commands.Drive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends PIDSubsystem {
	
	private CANTalon leftFrontTalon;
	private CANTalon rightFrontTalon;
	private CANTalon leftBackTalon;
	private CANTalon rightBackTalon;
	private CANTalon leftMiddleTalon;
	private CANTalon rightMiddleTalon;
	
	private AHRS Navx = new AHRS(SPI.Port.kMXP);
	
	private double goalDistance;

	private double targetAngle;
	private static double anglePConstant = RobotMap.DRIVE_ANGLE_P;
	private static double angleIConstant = RobotMap.DRIVE_ANGLE_I;
	private static double angleDConstant = RobotMap.DRIVE_ANGLE_D;
	
	private double angleMerkelTolerance = RobotMap.ANGLE_MERKEL_TOLERANCE;

	public DriveTrain() {
		/**
		 * Invokes PIDSubsystem constructor
		 */
		super("DriveTrain", anglePConstant, angleIConstant, angleDConstant);
		
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
		 *  the sensor 
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
		
		/**
		 * Treats the max and min values to be the same point for rotational distances
		 */
		getPIDController().setContinuous();
		
		/**
		 * Sets the range of inputs (degrees from getYaw())
		 */
		getPIDController().setInputRange(-180.0, 180.0);
		
		/**
		 * Sets the output range (voltage)
		 */
		getPIDController().setOutputRange(-1.0, 1.0);
		
		/**
		 * Sets the acceptable range to target
		 */
		getPIDController().setAbsoluteTolerance(angleMerkelTolerance);
	}
	
	/**
	 * Velocity - this is better for programmers due to its precision
	 * Percent - this is better for drivers because it feels more natural
	 * TODO: Create a method for resetting calculated control values when
	 * driver control (percent mode) begins; look through Talon methods first
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
		getPIDController().disable();
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
		getPIDController().disable();
		if(rightSpeed >= RobotMap.PERCENT_LOWER_SOFT_LIMIT && rightSpeed <= RobotMap.PERCENT_UPPER_SOFT_LIMIT )
		{
			rightFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
			rightFrontTalon.set(rightSpeed*RobotMap.DRIVETRAIN_FORWARD_RIGHT);
		}
	}
	
	/**
	 * Sets the Talons to speed mode
	 */
	public void setSpeedControlMode()
	{
		leftFrontTalon.changeControlMode(TalonControlMode.Speed);
		rightFrontTalon.changeControlMode(TalonControlMode.Speed);
	}
	
	/**
	 * Sets the Talons to percent mode
	 */
	public void setPercentControlMode()
	{
		leftFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
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
		this.goalDistance = goalDistance;
		leftFrontTalon.setPosition(0);
		leftFrontTalon.setSetpoint(goalDistance);
	}
	
	public void setRightDesiredPosition(double goalDistance){
		this.goalDistance = goalDistance;
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
	 * Returns the P value of the Talons
	 */
	public double returnRightDistanceConstant(){
		return rightFrontTalon.getP();
	}
	
	public double returnLeftDistanceConstant(){
		return leftFrontTalon.getP();
	}
	
	/**
	 * Sets 0 degrees to be where the robot is facing
	 */
	public void resetDriveTrainGyro()
	{
		Navx.reset();
	}
	
	/**
	 * @param targetAngle: Goal angle
	 */
	public void setTargetAngle(double targetAngle)
	{
		this.targetAngle = targetAngle;
	}
	
	/**
	 * Returns the angle we want to be
	 * @return double targetAngle (degrees)
	 */
	public double getTargetAngle()
	{
		return targetAngle;
	}
	
	/**
	 * Returns angle relative to initial position OR relative to field, measured from 180 to -180 degrees
	 * Initial position - gyro in robot-oriented mode
	 * Relative to field - gyro in field-oriented mode
	 * @return double degrees (-180 - 180)
	 */
	public double getCurrentAngle()
	{
		return Navx.getYaw();
	}
	
	/**
	 * Sets the distance P output for the left and right Talon
	 */
	public void setDriveTrainDistancePOutput()
	{
		double leftPOutput = leftFrontTalon.getError() * leftFrontTalon.getP();
		double rightPOutput = rightFrontTalon.getError() * rightFrontTalon.getP();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	@Override
	protected double returnPIDInput()
	{
		return Navx.getYaw();
	}
	
	/**
	 * Sets rotation based on clockwise movment being positive
	 */
	@Override
	protected void usePIDOutput(double output)
	{
		driveRightVelocity(-output);
		driveLeftVelocity(output);
	}		
}
