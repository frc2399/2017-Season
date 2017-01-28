package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.commands.JoyShoot;
import org.usfirst.frc.team2399.robot.commands.ShooterStop;

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
	
	/**
	 * Buttons
	 */
	
	private static Button shootButt = new JoystickButton(leftJoy, 17);
	private static Button stopShootButt = new JoystickButton(leftJoy, 18);

	/**
	 * Presets
	 */
	private static JoyShoot shooterOn = new JoyShoot(RobotMap.SHOOTER_SPEED_CONSTANT);
	private static ShooterStop shooterStop = new ShooterStop();

	public OI(){
		shootButt.whileHeld(shooterOn);
		stopShootButt.whenPressed(shooterStop);
	}
	
	/**
	 * Throttle Methods
	 * (throttle +1)/2 sets the throttle range from 0 to 1 rather than
	 * 	-1 to 1
	 * TODO: Figure out which joystick we actually want to get this value from
	 */
	
	public static double getShooterThrottle()
	{
		double throttle = rightJoy.getThrottle() * RobotMap.SHOOTER_THROTTLE_FORWARD_CONSTANT;
		return (throttle +1)/2;
	}
}
