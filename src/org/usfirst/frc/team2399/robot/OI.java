package org.usfirst.frc.team2399.robot;



import org.usfirst.frc.team2399.robot.commands.DriveAnglePAdjustment;
import org.usfirst.frc.team2399.robot.commands.DriveDistancePAdjustment;
import org.usfirst.frc.team2399.robot.commands.Climb;
import org.usfirst.frc.team2399.robot.commands.Shift;
import org.usfirst.frc.team2399.robot.commands.GearCollect;


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

	public static double getShooterY()
	{
		return shooterJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
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
	private static Button driveDistanceIncrementButt = new JoystickButton(shooterJoy, 5);
	private static Button driveDistanceDecrementButt = new JoystickButton(shooterJoy, 3);
	private static Button driveAngleIncrementButt = new JoystickButton(shooterJoy, 6);
	private static Button driveAngleDecrementButt = new JoystickButton(shooterJoy, 4);

	
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
	
<<<<<<< HEAD
	//preset speeds
	
	//References RobotMap for speed values; to be used to set speed when buttons are pressed
	private static JoyClimb climbUpSpeed = new JoyClimb(RobotMap.CLIMB_UP);
	private static JoyClimb climbDownSpeed = new JoyClimb(RobotMap.CLIMB_DOWN);
	private static JoyClimb climbStopSpeed = new JoyClimb(RobotMap.CLIMB_STOP);
	
	private static DriveDistancePAdjustment incrementDistanceP = new DriveDistancePAdjustment(true);
	private static DriveDistancePAdjustment decrementDistanceP = new DriveDistancePAdjustment(false);
	private static DriveAnglePAdjustment incrementAngleP = new DriveAnglePAdjustment(true);
	private static DriveAnglePAdjustment decrementAngleP = new DriveAnglePAdjustment(false);
	
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
		driveDistanceIncrementButt.whenPressed(incrementDistanceP);
		driveDistanceDecrementButt.whenPressed(decrementDistanceP);
		driveAngleIncrementButt.whenPressed(incrementAngleP);
		driveAngleDecrementButt.whenPressed(decrementAngleP);

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
}
