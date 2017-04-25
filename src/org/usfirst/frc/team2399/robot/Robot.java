package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLiftBlue;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLiftRed;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToCenterLift;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToFeederLiftBlue;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToFeederLiftRed;
import org.usfirst.frc.team2399.robot.commands.AutoCrossBaseLineAndReverseBlue;
import org.usfirst.frc.team2399.robot.commands.AutoCrossBaseLineAndReverseRed;
import org.usfirst.frc.team2399.robot.commands.AutoNeutralZoneFeederBlue;
import org.usfirst.frc.team2399.robot.commands.AutoNeutralZoneFeederRed;
import org.usfirst.frc.team2399.robot.subsystems.Agitator;
import org.usfirst.frc.team2399.robot.subsystems.Climber;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	public static Climber climber;
	public static Shifter shifter;
	public static DriveTrain driveTrain;
	public static GearCollector gearCollector;
	public static OI oi;
	public static Agitator agitator;
	public static Shooter shooter;
	DigitalInput autoGearRedBoilerLiftSelect;
	DigitalInput autoGearCenterLiftSelect;
	DigitalInput autoGearBlueBoilerLiftSelect;
	DigitalInput autoNeutralZoneFeederBlueSelect;
	DigitalInput autoNeutralZoneFeederRedSelect;
	DigitalInput autoGearRedFeederLiftSelect;
	DigitalInput autoGearBlueFeederLiftSelect;
	DigitalInput autoDriveToBaseLineAndReverseRed;
	DigitalInput autoDriveToBaseLineAndReverseBlue;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 * All instances of subsystems should be initialized here
	 * OI must be instantiated after the others so the buttons register the subsystems
	 * used by the commands they're attached to
	 */
	@Override
	public void robotInit() {
		  climber = new Climber();
		  shifter = new Shifter();
		  driveTrain = new DriveTrain();
		  gearCollector = new GearCollector();
		  agitator = new Agitator();
		  shooter = new Shooter();

		//  SmartDashboard.putBoolean("Red", false);
		  oi = new OI();

		  autoGearRedBoilerLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_RED_BOILER_LIFT_SELECT_PORT);
		  autoGearBlueBoilerLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_BLUE_BOILER_LIFT_SELECT_PORT);
		  autoGearCenterLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_CENTER_LIFT_SELECT_PORT);
		  autoNeutralZoneFeederBlueSelect = new DigitalInput(RobotMap.AUTO_DRIVE_NEUTRAL_FEEDER_BLUE_PORT);
		  autoNeutralZoneFeederRedSelect = new DigitalInput(RobotMap.AUTO_DRIVE_NEUTRAL_FEEDER_RED_PORT);
		  autoGearRedFeederLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_RED_FEEDER_LIFT_SELECT_PORT);
		  autoGearBlueFeederLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_BLUE_FEEDER_LIFT_SELECT_PORT);
		  autoDriveToBaseLineAndReverseRed = new DigitalInput(RobotMap.AUTO_DRIVE_BASE_LINE_AND_REVERSE_RED);
		  autoDriveToBaseLineAndReverseBlue = new DigitalInput(RobotMap.AUTO_DRIVE_BASE_LINE_AND_REVERSE_BLUE);

		  driveTrain.resetDriveTrainGyro();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		driveTrain.resetDriveTrainGyro();

		if (autoGearRedBoilerLiftSelect.get() == false){ // case RED BOILER SIDE LIFT
			autonomousCommand = new AutoDriveToBoilerLiftRed();
		} else if (autoGearBlueBoilerLiftSelect.get() == false){ // case BLUE BOILER SIDE LIFT
			autonomousCommand = new AutoDriveToBoilerLiftBlue();
		} else if (autoGearCenterLiftSelect.get() == false){ // case CENTER LIFT
			autonomousCommand = new AutoDriveToCenterLift();
		} else if (autoNeutralZoneFeederBlueSelect.get() == false){ //case BLUE NEUTRAL ZONE
			autonomousCommand = new AutoNeutralZoneFeederBlue();
		} else if (autoNeutralZoneFeederRedSelect.get() == false){ //case RED NEUTRAL ZONE
			autonomousCommand = new AutoNeutralZoneFeederRed();
		} else if (autoGearBlueFeederLiftSelect.get() == false){ //case BLUE HOPPER SIDE LIFT
			autonomousCommand = new AutoDriveToFeederLiftBlue();
		} else if (autoGearRedFeederLiftSelect.get() == false){ //case RED HOPPER SIDE LIFT
			autonomousCommand = new AutoDriveToFeederLiftRed();
		} else if (autoDriveToBaseLineAndReverseRed.get() == false){ //case RED CROSS BASELINE AND REVERSE
			autonomousCommand = new AutoCrossBaseLineAndReverseRed();
		} else if (autoDriveToBaseLineAndReverseBlue.get() == false){
			autonomousCommand = new AutoCrossBaseLineAndReverseBlue();
		}
		else { // case jumper elsewhere
			autonomousCommand = null;
		}

		// schedule the autonomous command
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
		/**
		 * Puts the PIDController on the SmartDashboard to input values
		 */
		//SmartDashboard.putData("PIDController", driveTrain.getPIDController());
		}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		/**
		 * These are the methods to put information on the SmartDashboard
		 * It works based on key-value pairs
		 * The key is the String and the value is the information you want to put on the Dashboard
		 */
		SmartDashboard.putBoolean("  ", oi.deadOrAlive());
		SmartDashboard.putNumber("Joy Climb", Math.abs(OI.modifyJoyOutputWithDeadband(OI.getShooterY())));
		SmartDashboard.putNumber(" ", getRobotTemperature());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * Gets the temperature of the Robot to ensure that we are not overheating our electrical system and ruining valuable equipment
	 * @return Average temperature of devices wired with temperature monitoring
	 */
	public double getRobotTemperature()
	{
		return 8;
	}
}
