package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	// Constants
	private final double MIN_SPEED = 0.5;
	private final double MAX_SPEED = 0.85;
	private final double kP = 0.01;
	
	// Control variables
	private double angle;

    public Turn(double angleInDegrees) {
        requires(Robot.driveTrain);
        angle = angleInDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = (angle - Math.abs(Robot.driveTrain.getGyroAngle())) * kP;
    	if (angle < 0)
    		error = -error;
    	
    	if (Math.abs(error) >= MIN_SPEED && Math.abs(error) <= MAX_SPEED) {
			Robot.driveTrain.forcedArcadeDrive(0, error);
    	} else if (Math.abs(error) < MIN_SPEED) {
			if (angle < 0)
				Robot.driveTrain.forcedArcadeDrive(0, -MIN_SPEED);
			else
				Robot.driveTrain.forcedArcadeDrive(0, MIN_SPEED);
    	} else {
    		if (angle < 0)
				Robot.driveTrain.forcedArcadeDrive(0, -MAX_SPEED);
			else
				Robot.driveTrain.forcedArcadeDrive(0, MAX_SPEED);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.getGyroAngle()) >= Math.abs(angle);
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
