package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.RobotMap;
import org.usfirst.frc.team703.robot.commands.ControlLift;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmLift extends Subsystem {
	// Lift piston
	private Solenoid lift = RobotMap.armLift;

    public void initDefaultCommand() {
    }
    
    public void up() {
    	lift.set(false);
    }
    
    public void down() {
    	lift.set(true);
    }
}

