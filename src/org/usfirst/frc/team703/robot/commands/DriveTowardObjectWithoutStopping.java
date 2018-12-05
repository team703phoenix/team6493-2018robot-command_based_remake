package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTowardObjectWithoutStopping extends Command {
	// Constants
	private static final double TURNING_SCALER = 0.015;
	
	// Control variables
	private double finalPercentage;
	private boolean scanLeft;

    public DriveTowardObjectWithoutStopping(double finalPercentage, boolean scanLeft) {
        requires(Robot.vision);
        requires(Robot.driveTrain);
        
        this.finalPercentage = finalPercentage;
        this.scanLeft = scanLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.vision.hasValidTarget()) // Scan for cube
        	Robot.driveTrain.arcadeDrive(0, (scanLeft) ? -0.5 : 0.5);
    	else // Drive toward cube
    		Robot.driveTrain.arcadeDrive(0.6, Robot.vision.getErrorX() * TURNING_SCALER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.vision.getPercentageOfScreen() >= finalPercentage;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
