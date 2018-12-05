package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;
import org.usfirst.frc.team703.robot.commands.ControlElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	// Constants
	private static final double AUTO_MOVE_SPEED = 0.80;
	private static final double ELEVATOR_DEADBAND = 0.30;
	public static final int ELEVATOR_TIMEOUT_SWITCH = 1100;
	public static final int ELEVATOR_TIMEOUT_SCALE = 2750;
	
	// Actuators
	private WPI_TalonSRX liftMotor1 = RobotMap.elevatorMotor1;
	private WPI_VictorSPX liftMotor2 = RobotMap.elevatorMotor2;
	private Solenoid brake = RobotMap.elevatorBrake;
	
	// Limit switch
	private DigitalInput lowerLimitSwitch = RobotMap.elevatorLowerLimitSwitch;
	
    public void initDefaultCommand() {
        setDefaultCommand(new ControlElevator());
    }
    
    public Elevator() {
    	liftMotor2.follow(liftMotor1);
    }
    
    public void move(double axis) {
    	if (Math.abs(axis) > ELEVATOR_DEADBAND && (axis < 0 || lowerLimitSwitch.get())) {
			liftMotor1.set(axis);
			brake.set(false);
    	} else {
    		liftMotor1.set(0);
    		brake.set(true);
    	}
    }
    
    public void up() {
    	move(-AUTO_MOVE_SPEED);
    }
    
    public void down() {
    	move(AUTO_MOVE_SPEED);
    }
    
    public void stop() {
    	move(0);
    }
    
    public boolean getLowerLimitSwitch() {
    	return !lowerLimitSwitch.get();
    }
}

