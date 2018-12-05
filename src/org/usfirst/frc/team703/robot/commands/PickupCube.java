package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickupCube extends CommandGroup {

    public PickupCube(boolean scanLeft) {
    	addSequential(new DriveTowardCubeWithoutStopping(scanLeft));
    	addSequential(new GrabCube());
    }
    
    public PickupCube() {
    	this(Robot.vision.getLatestErrorX() < 0);
    }
}
