package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.commands.GearIn;
import org.usfirst.frc.team2399.robot.commands.GearOut;

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
		return leftJoy.getY() * RobotMap.JOYDRIVE_FORWARD_CONSTANT;
	}

	public static double getRightY() {
		return rightJoy.getY() * RobotMap.JOYDRIVE_FORWARD_CONSTANT;
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
	
	private static Button gearOutButt = new JoystickButton(rightJoy, 55);
	private static Button gearInButt = new JoystickButton(rightJoy, 44);
	
	/**
	 * Presets - sets instances of commands and speeds for use with buttons
	 */
	
	private static GearOut moveGearOut = new GearOut();
	private static GearIn moveGearIn = new GearIn();
	
	public OI(){
		gearOutButt.whenPressed(moveGearOut);
		gearInButt.whenPressed(moveGearIn);
	}
}
