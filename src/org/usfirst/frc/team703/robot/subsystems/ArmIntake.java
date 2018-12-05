package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;
import org.usfirst.frc.team703.robot.commands.ControlIntake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmIntake extends Subsystem {
	// Constants
	public static final double AUTO_INTAKE_SPEED = 0.70;
	private static final double CREEP_MODE_SCALER = 0.50;
	private static final double ARM_DEADBAND = 0.10;
	public static final double AUTO_SHOOT_SPEED = 1.00;
	
	// Intake motors
	private WPI_TalonSRX leftArm = RobotMap.armLeftIntake, rightArm = RobotMap.armRightIntake;
	
	// Control variables
	private boolean creepMode = false;
	public boolean reverse = false;

    public void initDefaultCommand() {
    	setDefaultCommand(new ControlIntake());
    }
    
    public void setSpeed(double speedLeft, double speedRight) {
    	if(Math.abs(speedLeft) < ARM_DEADBAND){
            leftArm.set(0);
        }else{
        	if (!creepMode)
        		leftArm.set(speedLeft);
        	else
        		leftArm.set(speedLeft * CREEP_MODE_SCALER);
        }
        
        if(Math.abs(speedRight) < ARM_DEADBAND){
            rightArm.set(0);
        }else{
        	if (!creepMode)
        		rightArm.set(-speedRight);
        	else
        		rightArm.set(-speedRight * CREEP_MODE_SCALER);
        }
    }

    public void setSpeed(double speedLeft, double speedRight, boolean reverse){
        if (reverse)
        	setSpeed(-speedLeft, -speedRight);
        else
        	setSpeed(speedLeft, speedRight);
    }
    
    public void setCreepMode(boolean creepModeOn) {
    	creepMode = creepModeOn;
    }
    
    public void setReverse(boolean reverse) {
    	this.reverse = reverse;
    }
}

