package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.Utility;
import org.usfirst.frc.team2399.robot.commands.Drive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
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
	public static double angleErrorPConstant = 0.512; //originally 0.02
	public static double incrementConstant = 0.005;
	
	private static double rightDistancePConstant = RobotMap.RIGHT_DISTANCE_P;
	private static double leftDistancePConstant = RobotMap.LEFT_DISTANCE_P;
	private static double rightDistanceFConstant = RobotMap.RIGHT_DISTANCE_F;
	private static double leftDistanceFConstant = RobotMap.LEFT_DISTANCE_F;
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
		
		leftFrontTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
		leftBackTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
		leftMiddleTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
		rightFrontTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
		rightBackTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
		rightMiddleTalon.enableBrakeMode(!RobotMap.BRAKE_MODE);
	
		/**
		 * Sets all constants for PID loops
		 * We won't be using I and D, and they are set to 0 automatically, but
		 * for clarity and safety's sake, they're set to zero here
		 * TODO: Set actual values for F and P
		 */
		leftFrontTalon.setF(RobotMap.LEFT_DISTANCE_F);
		leftFrontTalon.setP(RobotMap.LEFT_DISTANCE_P);
		leftFrontTalon.setI(RobotMap.LEFT_DISTANCE_I);
		leftFrontTalon.setD(RobotMap.LEFT_DISTANCE_D);
		
		rightFrontTalon.setF(RobotMap.RIGHT_DISTANCE_F);
		rightFrontTalon.setP(RobotMap.RIGHT_DISTANCE_P);
		rightFrontTalon.setI(RobotMap.RIGHT_DISTANCE_I);
		rightFrontTalon.setD(RobotMap.RIGHT_DISTANCE_D);
		
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
		getPIDController().setOutputRange(-1, 1);
		
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
		//if(leftSpeed >= RobotMap.VELOCITY_LOWER_SOFT_LIMIT && leftSpeed <= RobotMap.VELOCITY_UPPER_SOFT_LIMIT)
		{
			leftFrontTalon.set(leftSpeed*RobotMap.DRIVETRAIN_FORWARD_LEFT);
		}
	}
	
	public void driveLeftPercent(double leftSpeed) {
	//	getPIDController().disable();
		if(leftSpeed >= RobotMap.PERCENT_LOWER_SOFT_LIMIT && leftSpeed <= RobotMap.PERCENT_UPPER_SOFT_LIMIT )
		{
			leftFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
			leftFrontTalon.set(leftSpeed*RobotMap.DRIVETRAIN_FORWARD_LEFT);
		}
	}
	
	public void driveRightVelocity(double rightSpeed) {
	//	if(rightSpeed >= RobotMap.VELOCITY_LOWER_SOFT_LIMIT && rightSpeed <= RobotMap.VELOCITY_UPPER_SOFT_LIMIT)
		{		
			rightFrontTalon.set(rightSpeed*RobotMap.DRIVETRAIN_FORWARD_RIGHT);
		}
	}
	
	public void driveRightPercent(double rightSpeed) {
	//	getPIDController().disable();
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
	
	public void setSpeedFixedAngleControlMode()
	{
		leftFrontTalon.changeControlMode(TalonControlMode.Speed);
		rightFrontTalon.changeControlMode(TalonControlMode.PercentVbus);
		
		
	}
	
	public double getLeftSpeed()
	{
		return leftFrontTalon.getSpeed();
	}
	
	public double getRightSpeed()
	{
		return rightFrontTalon.getSpeed();
	}

	/**
	 * Gets the current position of the robot
	 * Divided by the gear ratio for the encoder
	 * Multiplied by the circumference of the wheel for scaling
	 * @return
	 */
	public double getLeftPosition(){
		return leftFrontTalon.getPosition() / RobotMap.DRIVETRAIN_GEAR_RATIO 
				* RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE * RobotMap.DRIVETRAIN_ENCODER_FORWARD_LEFT;
	}
	
	public double getRightPosition(){
		return rightFrontTalon.getPosition() / RobotMap.DRIVETRAIN_GEAR_RATIO 
				* RobotMap.DRIVETRAIN_WHEEL_CIRCUMFERENCE * RobotMap.DRIVETRAIN_ENCODER_FORWARD_RIGHT;
	}
	
	public void resetLeftPosition(){
		leftFrontTalon.setPosition(0);
	}
	
	public void resetRightPosition(){
		rightFrontTalon.setPosition(0);
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
		return rightFrontTalon.getClosedLoopError();	
	}
	
	public double returnRightDistanceError(){
		return leftFrontTalon.getClosedLoopError();
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
	 * Returns the F value of the Talons
	 */
	public double returnRightDistanceFeedForward()
	{
		return rightFrontTalon.getF();
	}
	
	public double returnLeftDistanceFeedForward()
	{
		return leftFrontTalon.getF();
	}
	
	public void setLeftPConstant(double pConstant)
	{
		leftDistancePConstant = pConstant;
		leftFrontTalon.setP(leftDistancePConstant);
	}
	
	public void setRightPConstant(double pConstant)
	{
		rightDistancePConstant = pConstant;
		rightFrontTalon.setP(rightDistancePConstant);
	}
	
	public void incrementLeftPConstant(){
		double currentP = leftFrontTalon.getP();
		leftFrontTalon.setP(currentP + RobotMap.DISTANCE_P_INCREMENT);
	}
	
	public void incrementRightPConstant(){
		double currentP = rightFrontTalon.getP();
		rightFrontTalon.setP(currentP + RobotMap.DISTANCE_P_INCREMENT);
	}
	
	public void decrementLeftPConstant(){
		double currentP = leftFrontTalon.getP();
		leftFrontTalon.setP(currentP - RobotMap.DISTANCE_P_DECREMENT);
	}
	
	public void decrementRightPConstant(){
		double currentP = rightFrontTalon.getP();
		rightFrontTalon.setP(currentP - RobotMap.DISTANCE_P_DECREMENT);
	}
	
	public void incrementLeftFConstant(){
		double currentF = leftFrontTalon.getF();
		leftFrontTalon.setF(currentF + RobotMap.DISTANCE_F_INCREMENT);
	}
	
	public void incrementRightFConstant(){
		double currentF = rightFrontTalon.getF();
		rightFrontTalon.setF(currentF + RobotMap.DISTANCE_F_INCREMENT);
	}
	
	public void decrementLeftFConstant(){
		double currentF = leftFrontTalon.getF();
		leftFrontTalon.setF(currentF - RobotMap.DISTANCE_F_DECREMENT);
	}
	
	public void decrementRightFConstant(){
		double currentF = rightFrontTalon.getF();
		rightFrontTalon.setF(currentF - RobotMap.DISTANCE_F_DECREMENT);
	}
	
	public void incrementAngleErrorPConstant()
	{
		angleErrorPConstant += incrementConstant;
	}
	
	public void decrementAngleErrorPConstant()
	{
		angleErrorPConstant -= incrementConstant;
	}	
	
	public double getAngleErrorPConstant()
	{
		return angleErrorPConstant;
	}
	
	public void setRightSpeedWithAngle(double speed)
	{
		driveRightVelocity(speed + (getCurrentYaw() * angleErrorPConstant));
	}
	
	public void setLeftSpeedWithAngle(double speed)
	{
		driveLeftVelocity(speed - (getCurrentYaw() * angleErrorPConstant));
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
	public double getCurrentYaw()
	{
		return Navx.getYaw();
	}
	
	/**
	 * Returns angle relative to initial position OR relative to field, measured from -360 to 360
	 * getAngle() returns the angle accumulated since the last gyro reset
	 * To account for this, if the absolute value of the angle is greater than 360 degrees
	 * Divide by 360 degrees and return the remainder
	 * Initial position - gyro in robot-oriented mode
	 * Relative to field - gyro in field-oriented mode
	* @return
	 */
	public double getCurrentAngle()
	{
		if(Math.abs(Navx.getAngle()) > 360)
		{
			return Navx.getAngle() % 360;
		}
		
		return Navx.getAngle();
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
	 * Sets rotation based on clockwise movement being positive
	 */
	@Override
	protected void usePIDOutput(double output)
	{
		driveRightPercent(output);
		driveLeftPercent(-output);
	}	
	
	public PIDOutput controlRightSide()
	{
		return new PIDOutput(){
			public void pidWrite(double output)
			{
				driveRightPercent(output);
			}
		};
	}
	
	public PIDSource yawSource()
	{
		return new PIDSource(){

			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public PIDSourceType getPIDSourceType()
			{
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet()
			{
				return Navx.getYaw();
			}
			
		};
	}
}
