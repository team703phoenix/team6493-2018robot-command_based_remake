package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

/**
 *
 */
public class DriveTowardCubeWithoutStopping extends DriveTowardObjectWithoutStopping {
	// Constants
	private static final int PIPELINE_TIMEOUT = 250;
	private static final int FINAL_PERCENTAGE = 35;

    public DriveTowardCubeWithoutStopping(boolean scanLeft) {
        super(FINAL_PERCENTAGE, scanLeft);
        // In super:
        // requires(Robot.vision);
    	// requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setCubePipeline(PIPELINE_TIMEOUT);
    }
    
    // Inherits execute, isFinished, end, and interrupted methods from DriveTowardObjectWithoutStopping
}
