package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.subsystems.Agitator;
import org.usfirst.frc.team2399.robot.OI;
import org.usfirst.frc.team2399.robot.subsystems.Climber;
import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2399.robot.subsystems.Shooter;
import org.usfirst.frc.team2399.robot.subsystems.Shifter;
import org.usfirst.frc.team2399.robot.subsystems.GearCollector;


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
		  oi = new OI();
		  agitator = new Agitator();
		  shooter = new Shooter();
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
		autonomousCommand = chooser.getSelected();
		driveTrain.resetDriveTrainGyro();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

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
		SmartDashboard.putData("PIDController", driveTrain.getPIDController());
		}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("  ", oi.deadOrAlive());
		SmartDashboard.putNumber("Yaw", driveTrain.getCurrentAngle());
		SmartDashboard.putNumber(" ", getRobotTemperature());
		SmartDashboard.putBoolean("   ", shifter.getShifterHotSolenoid());
		SmartDashboard.putBoolean("    ", shifter.getShifterDangerousSolenoid());
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
