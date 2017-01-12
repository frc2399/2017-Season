package org.usfirst.frc.team2399.robot.subsystems;

import org.usfirst.frc.team2399.robot.RobotMap;
import org.usfirst.frc.team2399.robot.commands.JoyDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private CANTalon leftFrontTalon;
	private CANTalon rightFrontTalon;
	private CANTalon leftBackTalon;
	private CANTalon rightBackTalon;

	public DriveTrain() {
		leftFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_TALON_ADDRESS);
		rightFrontTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_TALON_ADDRESS);
		leftBackTalon = new CANTalon(RobotMap.DRIVETRAIN_LEFT_BACK_TALON_ADDRESS);
		rightBackTalon = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON_ADDRESS);
	}

	public void driveLeft(double leftSpeed) {
		leftFrontTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
		leftBackTalon.set(leftSpeed * RobotMap.DRIVETRAIN_FORWARD_LEFT_CONSTANT);
	}

	public void driveRight(double rightSpeed) {
		rightFrontTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
		rightBackTalon.set(rightSpeed * RobotMap.DRIVETRAIN_FORWARD_RIGHT_CONSTANT);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}

}
