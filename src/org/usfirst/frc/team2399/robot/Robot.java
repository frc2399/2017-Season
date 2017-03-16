package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.subsystems.Agitator;
import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.commands.AutoDriveHopperToBoilerLift;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLift;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToBoilerLiftRed;
import org.usfirst.frc.team2399.robot.commands.AutoDriveToCenterLift;
import org.usfirst.frc.team2399.robot.commands.DriveTrainGyroReset;
import org.usfirst.frc.team2399.robot.subsystems.Climber;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
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
	DigitalInput autoGearLeftLiftSelect;
	DigitalInput autoGearCenterLiftSelect;
	DigitalInput autoGearRightLiftSelect;

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
		  
		  autoGearLeftLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_LEFT_LIFT_SELECT_PORT);
		  autoGearRightLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_RIGHT_LIFT_SELECT_PORT);
		  autoGearCenterLiftSelect = new DigitalInput(RobotMap.AUTO_GEAR_CENTER_LIFT_SELECT_PORT);
		 
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
		/**
		 * If we ARE on the red alliance, all angle values will be multiplied by -1
		 * TODO: Make sure this works
		 */
		//boolean isRed = SmartDashboard.getBoolean("Red",false);
		
		//autonomousCommand = (Command) chooser.getSelected();
		driveTrain.resetDriveTrainGyro();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		

			if (autoGearLeftLiftSelect.get() == false){
				SmartDashboard.putString("auto", "auto running");
				autonomousCommand = new AutoDriveToBoilerLift(false);
			} else if (autoGearRightLiftSelect.get() == false){
				autonomousCommand = new AutoDriveToBoilerLift(true);
			} else if (autoGearCenterLiftSelect.get() == false){
				autonomousCommand = new AutoDriveToCenterLift();
			} else {
				autonomousCommand = null;
			}
		

		// schedule the autonomous command (example)
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
		SmartDashboard.putNumber("Yaw", driveTrain.getCurrentAngle());
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
