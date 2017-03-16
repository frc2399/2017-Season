package org.usfirst.frc.team2399.robot;


import org.usfirst.frc.team2399.robot.commands.AdjustDistanceF;
import org.usfirst.frc.team2399.robot.commands.AdjustDriveDistanceP;
import org.usfirst.frc.team2399.robot.commands.AngleErrorPAdjust;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLift;
import org.usfirst.frc.team2399.robot.commands.DriveDistanceHoldAngle;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLiftRed;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToCenterLift;
import org.usfirst.frc.team2399.robot.commands.Agitate;
import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.DriveAngle;
import org.usfirst.frc.team2399.robot.commands.DriveEncoderReset;
import org.usfirst.frc.team2399.robot.commands.DriveTrainGyroReset;
import org.usfirst.frc.team2399.robot.commands.Climb;
import org.usfirst.frc.team2399.robot.commands.Shift;
import org.usfirst.frc.team2399.robot.commands.GearCollect;
import org.usfirst.frc.team2399.robot.commands.Shoot;

import edu.wpi.first.wpilibj.DriverStation;
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
	 * TODO: Set all buttons on all Joysticks
	 */
	private static Button climbUpButt = new JoystickButton(rightJoy, 1);
	
	private static Button shiftToHotButt = new JoystickButton(leftJoy, 2);
	private static Button shiftToDangerousButt = new JoystickButton(leftJoy, 3);

	private static Button agitatorForwardButt = new JoystickButton(leftJoy, 11);
	private static Button agitatorBackwardButt = new JoystickButton(leftJoy, 10);

	private static Button gearOutButt = new JoystickButton(rightJoy, 3);
	private static Button gearInButt = new JoystickButton(rightJoy, 2);
	
	private static Button shootButt = new JoystickButton(shooterJoy, 1);
	
	private static Button resetDriveEncodersButt = new JoystickButton(rightJoy, 10);
	
	private static Button resetDriveGyroButt = new JoystickButton(rightJoy, 11);
	
		
	/**
	 * COMMAND INSTANCES
	 * Sets instances of commands and speeds for use with buttons
	 */
	private static Agitate agitatorForwardSpeed = new Agitate(RobotMap.AGITATOR_FORWARD);
	private static Agitate agitatorBackwardSpeed = new Agitate(RobotMap.AGITATOR_BACKWARDS);
	
	private static Shift shiftToDangerous = new Shift(!RobotMap.SHIFTER_SOLENOID_HOT,RobotMap.SHIFTER_SOLENOID_DANGEROUS);
	private static Shift shiftToHot = new Shift(RobotMap.SHIFTER_SOLENOID_HOT,!RobotMap.SHIFTER_SOLENOID_DANGEROUS);
	
	private static Climb climbUpSpeed = new Climb(RobotMap.CLIMBER_FORWARD);
	
	private static GearCollect moveGearOut = new GearCollect(!RobotMap.GEAR_SOLENOID_IN,RobotMap.GEAR_SOLENOID_OUT);
	private static GearCollect moveGearIn = new GearCollect(RobotMap.GEAR_SOLENOID_IN,!RobotMap.GEAR_SOLENOID_OUT);
	
	private static Shoot shooterOn = new Shoot(RobotMap.SHOOTER_SPEED_MIN, RobotMap.SHOOTER_SPEED_MAX);
		
	private static DriveEncoderReset driveEncoderReset = new DriveEncoderReset();
	private static DriveTrainGyroReset driveGyroReset = new DriveTrainGyroReset();

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
	 * agitatorForwardButt: When pressed, moves agitator forward
	 * agitatorBackwardButt: When pressed, moves agitator backward
	 * shootButt: While held, shoot
	 * driveAngleTestButts: Drive to designated angle when pressed
	 */
	public OI(){
		gearOutButt.whenPressed(moveGearOut);
		gearInButt.whenPressed(moveGearIn);
		climbUpButt.whileHeld(climbUpSpeed);
		shiftToHotButt.whenPressed(shiftToHot);
		shiftToDangerousButt.whenPressed(shiftToDangerous);
		agitatorForwardButt.whenPressed(agitatorForwardSpeed);
		agitatorBackwardButt.whenPressed(agitatorBackwardSpeed);
		shootButt.whileHeld(shooterOn);
		resetDriveEncodersButt.whenPressed(driveEncoderReset);
		resetDriveGyroButt.whenPressed(driveGyroReset);
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
	 * Gets values from the right joystick for setting speeds in other
	 * commands/subsystems
	 * @return the y-value from the Joystick
	 */
	public static double getRightY() {
		return rightJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}

	/**
	 * Gets values from the shooter joystick for setting speeds in other
	 * commands/subsystems
	 * @return the y-value from the Joystick
	 */
	public static double getShooterY()
	{
		return shooterJoy.getY() * RobotMap.JOYDRIVE_FORWARD;
	}
	
	public static double getShooterTwist()
	{
		return shooterJoy.getTwist();
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
	
	public static double rawThrottle()
	{
		return shooterJoy.getThrottle();
	}
	
	/**
	 * Checks to see if there has been an emergency and the main driver is incapacitated
	 * @return State of the driver
	 */
	public boolean deadOrAlive()
	{
		if(getLeftY() == 0 || getRightY() == 0)
		{
			return true;
		}
		
		return false;
	}
}

