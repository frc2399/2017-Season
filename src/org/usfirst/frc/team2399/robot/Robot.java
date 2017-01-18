package org.usfirst.frc.team2399.robot;

import org.usfirst.frc.team2399.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * Creates an instance of OI
	 */
	public static OI oi = new OI();;

	/**
	 * 
	 */
	public static DriveTrain driveTrain = new DriveTrain();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// UsbCamera camera = new UsbCamera("cam0", 0);
		// CameraServer.getInstance().startAutomaticCapture(camera);
		CameraServer.getInstance().startAutomaticCapture("cam", 0);
		// new Thread(() -> {
		// UsbCamera camera = CameraServer.getInstance()
		// .startAutomaticCapture();
		// camera.setResolution(640, 480);
		//
		// CvSink cvSink = CameraServer.getInstance().getVideo();
		// CvSource outputStream = CameraServer.getInstance().putVideo("Blur",
		// 640, 480);
		//
		// Mat source = new Mat();
		// Mat output = new Mat();
		//
		// Point center = new Point(310.0, 240.0);
		// Point vStart = new Point(310.0, 230.0);
		// Point vEnd = new Point(310.0, 250.0);
		// Point hStart = new Point(300.0, 240.0);
		// Point hEnd = new Point(320.0, 240.0);
		//
		// Scalar color1 = new Scalar(0, 238, 255);
		// Scalar color2 = new Scalar(255, 0, 195);
		//
		// while (true) {
		//
		// cvSink.grabFrame(source);
		//
		// Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
		// Imgproc.circle(source, center, 10, color1);
		// Imgproc.line(source, vStart, vEnd, color2);
		// Imgproc.line(source, hStart, hEnd, color2);
		//
		// outputStream.putFrame(output);
		// }
		//
		// }).start();

		// chooser.addObject("My Auto", new MyAutoCommand());

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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
