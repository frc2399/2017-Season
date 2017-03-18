package org.usfirst.frc.team2399.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {


	/**
	 * ADDRESSES, PORTS, AND CHANNELS
	 * Address for Talons 
	 * Things on the CAN network have addresses. Things on PWM network have ports. 
	 */
	
	/**
	 * Address for Talons
	 * Things on the CAN network have addresses. Things on PWM network have
	 * ports.
	 * TODO: Set actual address for shooter talon and agitator Talon
	 * Talon Addresses
	 */
	
	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS =8 ;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS =1 ;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 2;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 7;
	public static final int DRIVETRAIN_LEFT_MIDDLE_TALON_ADDRESS =5 ;
	public static final int DRIVETRAIN_RIGHT_MIDDLE_TALON_ADDRESS = 3;
	public static final int CLIMBER_TALON_ADDRESS = 10;
	public static final int PCM_ADDRESS = 3;
	//TODO: Make sure these match where they're plugged in
	public static final int AGITATOR_TALON_ADDRESS = 4; 
	public static final int SHOOTER_TALON_ADDRESS = 6;
	/**
	 * Ports for sensors, joysticks, and solenoids
	 */
	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;
	public static final int JOYDRIVE_SHOOTER_STICK_PORT = 2;

	public static final int SHIFTER_HOT_SOLENOID_PORT = 2;
	public static final int SHIFTER_DANGEROUS_SOLENOID_PORT= 3;
	public static final int GEAR_OUT_SOLENOID_PORT = 1;
	public static final int GEAR_IN_SOLENOID_PORT = 0;

	 /** 
	  * Forward Constants
	  * To ensure positive values of motion are what you expect.
	  * TODO: Confirm all forward constants are correct
	  */

	public static final int JOYDRIVE_FORWARD = -1;
	
	public static final int DRIVETRAIN_FORWARD_LEFT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT = -1;
	
	public static final int CLIMBER_FORWARD = 1;
	public static final double CLIMBER_FORWARD_REDUCED = .5;
	public static final int CLIMBER_STOP = 0;
	
	public static final int SHOOTER_FORWARD = 1;
	public static final double SHOOTER_STOP = 0;
	
	public static final double AGITATOR_STOP = 0;
	public static final double AGITATOR_FORWARD = 1;
	public static final double AGITATOR_BACKWARDS = -1;
	
	public static final int DRIVETRAIN_ENCODER_FORWARD_LEFT = 1;
	public static final int DRIVETRAIN_ENCODER_FORWARD_RIGHT = -1;
	public static final int SHOOTER_ENCODER_FORWARD = -1;
	
	public static final boolean REVERSE_LEFT_ENCODER_OUTPUT = (DRIVETRAIN_ENCODER_FORWARD_LEFT == -1);
	public static final boolean REVERSE_RIGHT_ENCODER_OUTPUT = (DRIVETRAIN_ENCODER_FORWARD_RIGHT == -1);
	public static final boolean SHOOTER_ENCODER_REVERSE_OUTPUT_CONSTANT = SHOOTER_ENCODER_FORWARD == -1;
	public static final boolean SHOOTER_MOTOR_REVERSE_OUTPUT_CONSTANT = SHOOTER_FORWARD == -1;
	
	public static final boolean SHIFTER_SOLENOID_DANGEROUS= true;
	public static final boolean SHIFTER_SOLENOID_HOT= true;
	public static final boolean GEAR_SOLENOID_OUT= true;
	public static final boolean GEAR_SOLENOID_IN = true;
	
	public static final boolean BRAKE_MODE = true;
	
	/**
	 * Constants for calculations
	 */
	
	public static final double RED_ALLIANCE_ANGLING_CONSTANT = -1;
	public static final double BLUE_ALLIANCE_ANGLING_CONSTANT = 1;
	/**
	 * Wheel Calculations
	 * Distance per pulse is the amount of distance travelled by the robot per one pulse of the encoder
	 * TODO: Find shooter wheel numbers
	 */
	public static final int SHOOTER_ENCODER_COUNT = 4096;
	public static final double SHOOTER_GEAR_RATIO = 1.0;
	public static final double SHOOTER_WHEEL_DIAMETER = 3.875;
	public static final double SHOOTER_WHEEL_CIRCUMFERENCE = RobotMap.SHOOTER_WHEEL_DIAMETER* Math.PI;
	public static final double SHOOTER_SPEED_MIN = 5000;
	public static final double SHOOTER_SPEED_MAX = 9000;
	
	public static final int DRIVETRAIN_ENCODER_COUNT = 4096; 
	public static final double DRIVETRAIN_GEAR_RATIO = 1.797; //Emperically determined
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 4.0;
	public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE =  DRIVETRAIN_WHEEL_DIAMETER * Math.PI;

	
	/**
	 * Throttle Constants
	 */
	public static final int SHOOTER_THROTTLE_FORWARD_CONSTANT = 1;

	/**
	 * ENCODER CONSTANTS
	 * TODO: Set actual values for constants
	 */
	public static final double DISTANCE_P_DECREMENT = 0.005;
	public static final double DISTANCE_P_INCREMENT = 0.005;
	public static final double DISTANCE_F_DECREMENT = 0.005;
	public static final double DISTANCE_F_INCREMENT = 0.005;
	public static final double ANGLE_P = 0;
	
	public static final double RIGHT_DISTANCE_P = 0.5;
	public static final double LEFT_DISTANCE_P = 0.5;
	public static final double RIGHT_DISTANCE_F = 0.5;
	public static final double LEFT_DISTANCE_F = 0.5;
	public static final double RIGHT_DISTANCE_I = .0015;
	public static final double LEFT_DISTANCE_I = .0015;
	public static final double RIGHT_DISTANCE_D = 0;
	public static final double LEFT_DISTANCE_D = 0;
	
	public static double DRIVE_ANGLE_P = 0.03;
	public static double DRIVE_ANGLE_I = 1.0E-5;
	public static double DRIVE_ANGLE_D = 0;
	
	public static double DRIVE_RAMP_TIME = 1;
	
	public static double ANGLE_MERKEL_TOLERANCE = 0.75;
	public static double ANGLE_MERKEL_DISTANCE_TOLERANCE = 3;
	public static double DRIVE_DISTANCE_TOLERANCE = 1;
	
	public static final double TEST_ANGLE_1 = 90.0;
	public static final double TEST_ANGLE_2 = 180.0;
	public static final double TEST_ANGLE_3 = -90.0;
	public static final double DRIVE_ANGLE_ERROR = 0;
	
	public static final double SHOOTER_SPEED_P_CONSTANT = 0;
	public static final double SHOOTER_SPEED_DECREMENT_CONSTANT = 0.005;
	public static final double SHOOTER_SPEED_INCREMENT_CONSTANT = 0.005;
	
	/**
	 * Timing Constants
	 */
	public static final double GEAR_TIMER = 0.2;
	public static final double SHIFT_TIMER = 0.5;

	/**
	 * Constants for completing both driving to a distance and to an angle at the same time
	 */
	public static final double DRIVE_MIXED_LINEAR = 0;
	public static final double DRIVE_MIXED_ANGULAR = 0;
	
	/**
	 * Speed Soft Limits
	 * TODO: Calculate conversion for velocity - find them in code implementations part 3 journal
	 */
	public static final double VELOCITY_LOWER_SOFT_LIMIT = 0;
	public static final double VELOCITY_UPPER_SOFT_LIMIT = 0;
	public static final double PERCENT_LOWER_SOFT_LIMIT = -1;
	public static final double PERCENT_UPPER_SOFT_LIMIT = 1;
	
	/**
	 * Joystick Output
	 * TODO: Figure out what this time should actually be
	 */
	public static final double DEADBAND = 0.05;
	
	public static final int AUTO_GEAR_RED_LIFT_SELECT_PORT = 0;
	public static final int AUTO_GEAR_CENTER_LIFT_SELECT_PORT = 1;
	public static final int AUTO_GEAR_BLUE_LIFT_SELECT_PORT = 2;
}
