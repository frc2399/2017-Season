package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.subsystems.Climber;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	

	/**
	 * ADDRESSES, PORTS, AND CHANNELS
	 * Address for Talons Things on the CAN network have addresses. Things on
	 * PWM network have ports. TODO: Find correct cantalon once on the robot
	 * 
	 */
	
	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS = 7;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS = 10;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 6;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 9;
	public static final int DRIVETRAIN_LEFT_MIDDLE_TALON_ADDRESS = 8;
	public static final int DRIVETRAIN_RIGHT_MIDDLE_TALON_ADDRESS = 5;
	public static final int CLIMBER_TALON_ADDRESS = 2;

	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;

	/**
	 * FORWARD CONSTANTS
	 * To ensure positive values of motion are what you
	 * expect.
	 * TODO: Set actual forward constants for encoders
	 */

	public static final int JOYDRIVE_FORWARD = -1;
	public static final int DRIVETRAIN_FORWARD_LEFT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT = 1;
	public static final int CLIMBER_FORWARD = 1;
	public static final int DRIVETRAIN_ENCODER_FORWARD_LEFT = 1;
	public static final int DRIVETRAIN_ENCODER_FORWARD_RIGHT = 1;
	
	public static final boolean REVERSE_LEFT_FRONT_MOTOR_OUTPUT = (DRIVETRAIN_FORWARD_LEFT == -1);
	public static final boolean REVERSE_RIGHT_FRONT_MOTOR_OUTPUT = (DRIVETRAIN_FORWARD_LEFT == -1);
	public static final boolean REVERSE_LEFT_ENCODER_OUTPUT = (DRIVETRAIN_ENCODER_FORWARD_LEFT == -1);
	public static final boolean REVERSE_RIGHT_ENCODER_OUTPUT = (DRIVETRAIN_ENCODER_FORWARD_RIGHT == -1);
	
	/**
	 * PRESET SPEEDS
	 * Preset speeds for climber to be used elsewhere in code
	 * Currently at full speed - may need to be less depending on the mechanism
	 * TODO: set actual speeds
	 */
	public static final double CLIMB_UP = 1.0;
	public static final double CLIMB_DOWN = -1.0;
	public static final double CLIMB_STOP = 0.0;
	
	/**
	 * ENCODER CONSTANTS
	 * TODO: Set actual values for constants
	 */
	
	public static final double DISTANCE_DECREMENT = 0;
	public static final double DISTANCE_INCREMENT = 0;
	public static final double ANGLE_P = 0;
	
	/**
	 * CALCULATION CONSTANTS
	 */
	
	/**
	 * @param DRIVETRAIN_WHEEL_DIAMETER inches
	 */
	
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 4;
	public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE =  DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
	
	/**
	 * Speed Soft Limits
	 * TODO: Calculate conversion for velocity
	 */
	
	public static final double VELOCITY_LOWER_SOFT_LIMIT = 0;
	public static final double VELOCITY_UPPER_SOFT_LIMIT = 0;
	public static final double PERCENT_LOWER_SOFT_LIMIT = -1;
	public static final double PERCENT_UPPER_SOFT_LIMIT = 1;
	

}
