package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowTarget extends Command {
	// Constants
	private static final double SCAN_SPEED = 0.50;
	private static final double FOLLOWING_DISTANCE = 5;
	private static final double kP = 0.25;
	private static final double MAX_SPEED = 0.6;

    public FollowTarget() {
        requires(Robot.vision);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setTargetPipeline();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.vision.hasValidTarget()) {
    		double forwardDrive = (FOLLOWING_DISTANCE - Robot.vision.getPercentageOfScreen()) * kP;
			if (forwardDrive < MAX_SPEED)
				Robot.driveTrain.arcadeDrive(forwardDrive, Robot.vision.getErrorX() * Robot.vision.VISION_TURNING_SCALER);
			else
				Robot.driveTrain.arcadeDrive(MAX_SPEED, Robot.vision.getErrorX() * Robot.vision.VISION_TURNING_SCALER);
    	} else
    		Robot.driveTrain.arcadeDrive(0, (Robot.vision.getLatestErrorX() < 0) ? -SCAN_SPEED : SCAN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

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
