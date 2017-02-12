package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.commands.Climb;
import org.usfirst.frc.team2399.robot.commands.Shift;
import org.usfirst.frc.team2399.robot.commands.ShooterStop;
import org.usfirst.frc.team2399.robot.commands.GearCollect;
import org.usfirst.frc.team2399.robot.commands.JoyShoot;
import org.usfirst.frc.team2399.robot.commands.Climb;

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
	private static Joystick shooterJoy = new Joystick(RobotMap.JOYDRIVE_SHOOTER_STICK_PORT);

	/**
	 * BUTTONS
	 */
	
	private static Button climbUpButt = new JoystickButton(leftJoy, 3);
	private static Button climbDownButt = new JoystickButton(leftJoy, 2);
	private static Button shiftToHotButt = new JoystickButton(leftJoy, 4);
	private static Button shiftToDangerousButt = new JoystickButton(leftJoy, 5);
	private static Button gearOutButt = new JoystickButton(rightJoy, 3);
	private static Button gearInButt = new JoystickButton(rightJoy, 2);

	/**
	 * COMMAND INSTANCES
	 * Sets instances of commands and speeds for use with buttons
	 */

	private static Shift shiftToDangerous = new Shift(!RobotMap.SHIFTER_SOLENOID_HOT,RobotMap.SHIFTER_SOLENOID_DANGEROUS);
	private static Shift shiftToHot = new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS);
	private static Climb climbUpSpeed = new Climb(RobotMap.CLIMB_UP);
	private static Climb climbDownSpeed = new Climb(RobotMap.CLIMB_DOWN);
	private static GearCollect moveGearOut = new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT);
	private static GearCollect moveGearIn = new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT);
	
	/**
	 * OI CONSTRUCTOR
	 * 
	 * gearOutButt: when pressed, move the gear collecting mechanism out
	 * gearInButt: when pressed, move the gear collecting mechanism in
	 * climbUpButt: While held down, go up at the preset speed (see above)
	 * climbDownButt: While held down, go down at the preset speed (see above)
	 * climbStopButt: When pressed, stop (emergency stop button)
	 * shiftToHotButt: When pressed, shift to hot (low) gear
	 * shiftToDangerousButt: When pressed, shift to dangerous (high) gear
	 */
	
	public OI(){
		gearOutButt.whenPressed(moveGearOut);
		gearInButt.whenPressed(moveGearIn);
		climbUpButt.whileHeld(climbUpSpeed);
		climbDownButt.whileHeld(climbDownSpeed);
		shiftToHotButt.whenPressed(shiftToHot);
		shiftToDangerousButt.whenPressed(shiftToDangerous);
		shootButt.whileHeld(shooterOn);
	}

	
	/**
	 * JOYSTICK METHODS
	 */

	/**
	 * Gets values from the left joystick for setting speeds in other
	 * commands/subsystems
	 * @return the y-value from the Joystick
	 */
	public static double getLeftY() {
		return leftJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}
	
	/**
	 * Gets values from the right for setting speeds in other
	 * commands/subsystems
	 * @return the y-value from the Joystick
	 */

	public static double getRightY() {
		return rightJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}
	
	/**
	 * Modifies the joystick output so that they aren't too sensitive/insensitive to driver control
	 * 
	 * joystickInputNoDirection/joystickInputWithDirection: Sets the input to be either 1 or negative one (forwards or backwards)
	 * joystickInputNoDirection-RobotMap.DEADBAND: Removes  the deadband area from the joystick input to calculate the current actual input
	 * 1-RobotMap.DEADBAND: Removes the deadband area form the total possible joystick input
	 * joystickInputWithoutDeadband/totalJoystickInputPossibleWithDeadband: Calculates the amount of current input as a fraction of the total possible
	 * 
	 * If the joystick input is less than the deadband width, don't move the robot
	 * Otherwise, perform the output scaling calculation to take deadband into account
	 * @param input
	 * @return
	 */
	
	public static double modifyJoyOutputWithDeadband(double joystickInputWithDirection)
	{
		double joystickInputNoDirection = Math.abs(joystickInputWithDirection);
		
		
		double joystickInputDirection = joystickInputNoDirection/joystickInputWithDirection;
		double joystickInputWithDeadband = joystickInputNoDirection-RobotMap.DEADBAND;
		double totalJoystickInputPossibleWithDeadband = 1-RobotMap.DEADBAND;
		
		if(joystickInputNoDirection < RobotMap.DEADBAND)
		{
			return 0;
		}
		else
		{
			return (joystickInputDirection * joystickInputWithDeadband/totalJoystickInputPossibleWithDeadband);
		}
	}
	
	public static double getShooterY(){
		return shooterJoy.getY()* RobotMap.JOYDRIVE_FORWARD_CONSTANT;
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
	
	private static Button shootButt = new JoystickButton(shooterJoy, 1);


	/**
	 * Presets
	 */
	private static JoyShoot shooterOn = new JoyShoot(RobotMap.SHOOTER_SPEED_CONSTANT);
	private static ShooterStop shooterStop = new ShooterStop();


	
	/**
	 * Throttle Methods
	 * (throttle +1)/2 sets the throttle range from 0 to 1 rather than
	 * 	-1 to 1
	 */
	
	public static double getShooterThrottle()
	{
		double throttle = shooterJoy.getThrottle() * RobotMap.SHOOTER_THROTTLE_FORWARD_CONSTANT;
		return (throttle +1)/2;
	}
}
