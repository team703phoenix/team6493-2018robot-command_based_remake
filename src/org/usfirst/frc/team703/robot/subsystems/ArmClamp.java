package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;
import org.usfirst.frc.team703.robot.commands.ControlClamp;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmClamp extends Subsystem {
	// Clamp piston
	private Solenoid clamp = RobotMap.armClamp;

    public void initDefaultCommand() {
    }
    
    public void open(){
        clamp.set(false);
    }
    
    public void close() {
    	clamp.set(true);
    }
}

