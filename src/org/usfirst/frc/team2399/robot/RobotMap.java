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
	 */

	/**
	 * Address for Talons
	 * Things on the CAN network have addresses. Things on PWM network have
	 * ports.
	 * TODO: Set actual address for shooter talon
	 */

	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS = 7;
	public static final int DRIVETRAIN_RIGHT_TALON_MIDDLE_ADDRESS = 5;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS = 10;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 6;
	public static final int DRIVETRAIN_LEFT_MIDDLE_TALON_ADDRESS = 8;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 9;
	public static final int SHOOTER_TALON_ADDRESS = 8756;
	

	
	
	/**
	 * Ports for sensors and joysticks
	 */
	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;
	public static final int JOYDRIVE_SHOOTER_STICK_PORT = 2;
	
	/**
	 * Forward Constants
	 * To ensure positive values of motion are what you expect.
	 * TODO: Set actual constants for shooter values
	 */

	public static final int JOYDRIVE_FORWARD_CONSTANT = -1;
	public static final int DRIVETRAIN_FORWARD_LEFT_CONSTANT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT_CONSTANT = 1;
	public static final int SHOOTER_FORWARD_CONSTANT = 1;

	public static final int SHOOTER_ENCODER_FORWARD_CONSTANT = 1;
	
	public static final boolean SHOOTER_ENCODER_REVERSE_OUTPUT_CONSTANT = SHOOTER_ENCODER_FORWARD_CONSTANT == -1;
	public static final boolean SHOOTER_MOTOR_REVERSE_OUTPUT_CONSTANT = SHOOTER_FORWARD_CONSTANT == -1;
	
	/**
	 * Constants for calculations
	 */
	
	/**
	 * P-Loop Constants
	 * TODO: Set actual values
	 */
	public static final int SHOOTER_SPEED_P_CONSTANT = 0;
	public static final int SHOOTER_SPEED_DECREMENT_CONSTANT = 0;
	public static final int SHOOTER_SPEED_INCREMENT_CONSTANT = 0;
	
	/**
	 * Encoder calculations
	 * Distance per pulse is the amount of distance travelled by the robot per one pulse of the encoder
	 * TODO: Find actual numbers/figure out how to do this for Talons
	 */
	public static final int SHOOTER_ENCODER_COUNT = 920348039;
	public static final double SHOOTER_GEAR_RATIO = 34023984;
	public static final double SHOOTER_WHEEL_CIRCUMFERENCE = 203948;
	
	/**
	 * Speed Constants
	 * TODO: Set actual constants
	 */
	
	public static final double SHOOTER_SPEED_CONSTANT = 1;
	public static final double SHOOTER_STOP_CONSTANT = 0;
	
	/**
	 * Throttle Constants
	 */
	
	public static final int SHOOTER_THROTTLE_FORWARD_CONSTANT = 1;

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
