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
	 * TODO: Set actual numbers
	 */
	
	public static final int DRIVETRAIN_RIGHT_TALON_FRONT_ADDRESS = 1;
	public static final int DRIVETRAIN_LEFT_TALON_FRONT_ADDRESS = 4;
	public static final int DRIVETRAIN_LEFT_BACK_TALON_ADDRESS = 3;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS = 2;
	public static final int DRIVETRAIN_LEFT_MIDDLE_TALON_ADDRESS = 209384;
	public static final int DRIVETRAIN_RIGHT_MIDDLE_TALON_ADDRESS = 2039480;
	public static final int CLIMBER_TALON_ADDRESS = 2399;

	public static final int JOYDRIVE_LEFT_STICK_PORT = 0;
	public static final int JOYDRIVE_RIGHT_STICK_PORT = 1;

	/**
	 * FORWARD CONSTANTS
	 * To ensure positive values of motion are what you
	 * expect.
	 * TODO: Set actual forward constants
	 */

	public static final int JOYDRIVE_FORWARD_CONSTANT = -1;
	public static final int DRIVETRAIN_FORWARD_LEFT_CONSTANT = 1;
	public static final int DRIVETRAIN_FORWARD_RIGHT_CONSTANT = 1;
	public static final int CLIMBER_FORWARD_CONSTANT = 1;
	public static final int DRIVETRAIN_ENCODER_FORWARD_LEFT_CONSTANT = 1;
	public static final int DRIVETRAIN_ENCODER_FORWARD_RIGHT_CONSTANT = 1;
	
	public static final boolean REVERSE_LEFT_FRONT_MOTOR_OUTPUT_CONSTANT = (DRIVETRAIN_FORWARD_LEFT_CONSTANT == -1);
	public static final boolean REVERSE_RIGHT_FRONT_MOTOR_OUTPUT_CONSTANT = (DRIVETRAIN_FORWARD_LEFT_CONSTANT == -1);
	public static final boolean REVERSE_LEFT_ENCODER_OUTPUT_CONSTANT = (DRIVETRAIN_ENCODER_FORWARD_LEFT_CONSTANT == -1);
	public static final boolean REVERSE_RIGHT_ENCODER_OUTPUT_CONSTANT = (DRIVETRAIN_ENCODER_FORWARD_RIGHT_CONSTANT == -1);
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
	
	public static final double DISTANCE_DECREMENENT_CONSTANT = 0;
	public static final double DISTANCE_INCREMENT_CONSTANT = 0;
	public static final double ANGLE_P_CONSTANT = 0;
	
	/**
	 * CALCULATION CONSTANTS
	 */
	
	public static final double DRIVETRAIN_WHEEL_DIAMETER_CONSTANT = 0;
	public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE_CONSTANT =  DRIVETRAIN_WHEEL_DIAMETER_CONSTANT * Math.PI;

}
