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
	 */

	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS = 1;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS = 4;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 3;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 2;
	
	/**
	 * Ports for sensors and joysticks
	 */
	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;
	public static final int CAMERA_STICK_PORT = 2;
	
	/**
	 * Forward Constants
	 * To ensure positive values of motion are what you expect.
	 */

	public static final int JOYDRIVE_FORWARD_CONSTANT = -1;
	public static final int DRIVETRAIN_FORWARD_LEFT_CONSTANT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT_CONSTANT = 1;

	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
