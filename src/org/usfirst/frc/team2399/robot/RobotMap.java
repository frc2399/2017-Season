package org.usfirst.frc.team2399.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/**
	 * Creates instances of subsystems for use in commands
	 */

	/**
	 * ADDRESSES, PORTS, AND CHANNELS
	 */

	/**
	 * Address for Talons Things on the CAN network have addresses. Things on
	 * PWM network have ports. TODO: will need to change the ports with the new
	 * Chassis
	 */

	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS = 1;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS = 4;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 3;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 2;
	public static final int CLIMBER_TALON_ADDRESS = 2399;

	/**
	 * Ports for sensors and joysticks, Created a port for the Solenoid
	 */
	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;
	public static final int SHIFTER_SOLENOID_PORT = 379;

	/**
	 * Forward Constants To ensure positive values of motion are what you
	 * expect/Boolean constants to ensure direction of motion is what you'd expect it to be.
	 *
	 */

	public static final int JOYDRIVE_FORWARD_CONSTANT = -1;
	public static final int DRIVETRAIN_FORWARD_LEFT_CONSTANT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT_CONSTANT = 1;
	public static final int CLIMBER_FORWARD_CONSTANT = 1;
	
	public static final boolean SHIFTER_SOLENOID_DANGEROUS_CONTSTANT = true;
	public static final boolean SHIFTER_SOLENOID_HOT_CONTSTANT = false;

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	/**
	 * Preset Speeds
	 */

	/**
	 * Preset speeds for climber to be used elsewhere in code Currently at full
	 * speed - may need to be less depending on the mechanism
	 */
	// TODO: set actual speeds
	public static final double CLIMB_UP = 1.0;
	public static final double CLIMB_DOWN = -1.0;
	public static final double CLIMB_STOP = 0.0;

	/**
	 * Timing Constants
	 */

	// TODO: Figure out what this time should actually be
	// Created a constant for how long the solenoid should run
	public static final double SHIFT_TIMER = 0.5;

}
