package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

/**
 *
 */
public class DriveTowardCube extends DriveTowardObject {
	// Constants
	private static final int PIPELINE_TIMEOUT = 250;
	private static final int FINAL_PERCENTAGE = 35;

    public DriveTowardCube(boolean scanLeft) {
    	super(FINAL_PERCENTAGE, scanLeft);
    	// In super:
        // requires(Robot.vision);
    	// requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setCubePipeline(PIPELINE_TIMEOUT);
    }
    
    // Inherits the execute, isFinished, end, and interrupted methods from super
}
