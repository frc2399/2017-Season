package org.usfirst.frc.team2399.robot;


import org.usfirst.frc.team2399.robot.commands.JoyClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	/**
	 * DEVICES
	 */

	/**
	 * Joysticks
	 */
	
	private static Joystick leftJoy = new Joystick(RobotMap.JOYDRIVE_LEFT_STICK_PORT);
	private static Joystick rightJoy = new Joystick(RobotMap.JOYDRIVE_RIGHT_STICK_PORT);
	
	/**
	 * JOYSTICK METHODS
	 */

	/**
	 * Gets values from the Joysticks for setting speeds in other commands/subsystems
	 * @return the y-value from the Joystick
	 */
	public static double getLeftY() {
		return leftJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}

	public static double getRightY() {
		return rightJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	
	//Climbing buttons - to be used in teleop
	//TODO: set climbing buttons
	private static Button climbUpButt = new JoystickButton(leftJoy, 86);
	private static Button climbDownButt = new JoystickButton(leftJoy, 59);
	private static Button climbStopButt = new JoystickButton(leftJoy, 300);
	
	/**
	 * Presets
	 */
	
	//preset speeds
	
	//References RobotMap for speed values; to be used to set speed when buttons are pressed
	private static JoyClimb climbUpSpeed = new JoyClimb(RobotMap.CLIMB_UP);
	private static JoyClimb climbDownSpeed = new JoyClimb(RobotMap.CLIMB_DOWN);
	private static JoyClimb climbStopSpeed = new JoyClimb(RobotMap.CLIMB_STOP);

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/**
	 * OI Constructor
	 * climbUpButt: While held down, go up at the preset speed (see above)
	 * climbUpButt: While held down, go down at the preset speed (see above)
	 * climbUpButt: When pressed, stop (emergency stop button)
	 */
	public OI()
	{
		climbUpButt.whileHeld(climbUpSpeed);
		climbDownButt.whileHeld(climbDownSpeed);
		climbStopButt.whenPressed(climbStopSpeed);
	}
}
