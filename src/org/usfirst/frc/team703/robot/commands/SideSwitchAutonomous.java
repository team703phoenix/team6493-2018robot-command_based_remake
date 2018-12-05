package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideSwitchAutonomous extends CommandGroup {

    public SideSwitchAutonomous() {	
    	if (Robot.switchIsAdjacent()) {
    		addSequential(new DriveForward(148));
    		addSequential(new Turn(Robot.switchIsLeft() ? 90 : -90));
    		addSequential(new DriveForwardAndLiftElevatorToSwitch());
    		addSequential(new ShootCube());
    	} else {
    		addSequential(new CrossBaseline());
    	}
    }
}
