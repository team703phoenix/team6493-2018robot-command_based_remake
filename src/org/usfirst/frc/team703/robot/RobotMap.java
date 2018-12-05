/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team703.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	// Constants
	public static final int PCM_CHANNEL = 11;
	
	// Robot specifications
	public static final int DRIVETRAIN_WHEEL_DIAMETER = 6;
	public static final int ENCODER_TICKS_PER_ROTATION = 925;
	
	// Drive motors
	public static WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(1);
	public static WPI_VictorSPX rearLeftDrive = new WPI_VictorSPX(2);
	public static WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(3);
	public static WPI_VictorSPX rearRightDrive = new WPI_VictorSPX(4);
	
	// Gyro
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	// Elevator actuators
	public static WPI_TalonSRX elevatorMotor1 = new WPI_TalonSRX(5);
	public static WPI_VictorSPX elevatorMotor2 = new WPI_VictorSPX(6);
	public static Solenoid elevatorBrake = new Solenoid(PCM_CHANNEL, 0);
	public static DigitalInput elevatorLowerLimitSwitch = new DigitalInput(0);
	
	// Arm actuators
	public static WPI_TalonSRX armLeftIntake = new WPI_TalonSRX(2);
	public static WPI_TalonSRX armRightIntake = new WPI_TalonSRX(3);
	public static Solenoid armClamp = new Solenoid(PCM_CHANNEL, 1);
	public static Solenoid armLift = new Solenoid(PCM_CHANNEL, 2);
}
