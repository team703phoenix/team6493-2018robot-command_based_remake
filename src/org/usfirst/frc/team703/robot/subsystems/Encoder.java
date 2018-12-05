package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Encoder extends Subsystem {
	// Attached motor
	WPI_TalonSRX attachedMotor;
	
	// Control variables
	int zeroedPosition = 0;

    public void initDefaultCommand() {
    }
    
    public Encoder(WPI_TalonSRX attachedMotor) {
    	this.attachedMotor = attachedMotor;
    	this.attachedMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    }
    
    public int getRawPosition() {
    	return attachedMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public int getPosition() {
    	return getRawPosition() - zeroedPosition;
    }
    
    public int getVelocity() {
    	return attachedMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public void reset() {
    	zeroedPosition = getRawPosition();
    }
}

