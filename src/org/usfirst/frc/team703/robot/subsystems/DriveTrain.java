package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;
import org.usfirst.frc.team703.robot.commands.DriveWithController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Drive constants
	private static final double DRIVE_DEADBAND = 0.10;
	private static final double CREEP_MODE_SCALER = 0.50;
	private static final double ACCELERATION_RATE = 0.06;
	private static final double GYRO_SCALER = 1.04;
	private static final double GYRO_CORRECTION_SCALER = 0.08;
	
	// Drive motors
	private WPI_TalonSRX left1 = RobotMap.frontLeftDrive, right1 = RobotMap.frontRightDrive;
	private WPI_VictorSPX left2 = RobotMap.rearLeftDrive, right2 = RobotMap.rearRightDrive;
	
	// Encoders
	private Encoder leftEnc = new Encoder(left1), rightEnc = new Encoder(right1);
	
	// Gyro
	private ADXRS450_Gyro gyro = RobotMap.gyro;
	
	// Control variables
	private boolean creepMode = false;

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithController());
    }
    
    public DriveTrain() {
    	left2.follow(left1);
    	right2.follow(right1);
    }
    
    public void forcedTankDrive(double leftDrive, double rightDrive) {
		if (Math.abs(leftDrive) > DRIVE_DEADBAND) {
			if (!creepMode)
				left1.set(leftDrive);
			else
				left1.set(leftDrive * CREEP_MODE_SCALER);
	 	} else
			left1.set(0);
		
		if (Math.abs(rightDrive) > DRIVE_DEADBAND) {
			if (!creepMode)
				right1.set(-rightDrive);
			else
				right1.set(-rightDrive * CREEP_MODE_SCALER);
		} else
			right1.set(0);
	}
    
    public void forcedArcadeDrive(double forwardDrive, double turnDrive) {
		forcedTankDrive(forwardDrive + turnDrive, forwardDrive - turnDrive);
	}
    
    public void tankDrive(double leftDrive, double rightDrive) {
    	forcedTankDrive(accelDecel(left1, leftDrive), accelDecel(right1, rightDrive));
    }
    
    public void arcadeDrive(double forwardDrive, double turnDrive) {
    	tankDrive(forwardDrive + turnDrive, forwardDrive - turnDrive);
    }
    
    private double accelDecel(WPI_TalonSRX motor, double speed) {
		double acceptedRange = ACCELERATION_RATE / 2;
		
		if (motor.get() < speed - acceptedRange)
			return motor.get() + ACCELERATION_RATE;
		else if (motor.get() > speed + acceptedRange)
			return motor.get() - ACCELERATION_RATE;
		else
			return speed;
	}
    
	public void gyroAssistedDrive(double speed) {
		arcadeDrive(speed, -getGyroCorrectionAngle());
	}
    
    public int getLeftEncPosition() {
    	return leftEnc.getPosition();
    }
    
    public int getRightEncPosition() {
    	return rightEnc.getPosition();
    }
    
    public void resetEncoders() {
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public double getGyroAngle() {
    	return gyro.getAngle() * GYRO_SCALER;
    }
    
    public double getGyroCorrectionAngle() {
    	return getGyroAngle() * GYRO_CORRECTION_SCALER;
    }
    
    public void resetGyro() {
    	gyro.reset();
    }
    
    public void setCreepMode(boolean creepModeOn) {
    	creepMode = creepModeOn;
    }
    
    public static int inchesToTicks(double inches) {
    	return (int)(inches / (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) * RobotMap.ENCODER_TICKS_PER_ROTATION);
    }
}

