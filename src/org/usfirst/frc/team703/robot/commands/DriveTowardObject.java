package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

/**
 *
 */
public class DriveTowardObject extends DriveTowardObjectWithoutStopping {

    public DriveTowardObject(double finalPercentage, boolean scanLeft) {
    	super(finalPercentage, scanLeft);
    	// In super:
        // requires(Robot.vision);
    	// requires(Robot.driveTrain);
    }
    
    // Inherits initialize, execute, and isFinished methods from DriveTowardObjectWithoutStopping

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.forcedTankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
