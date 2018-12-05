package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Scan extends Command {
	// Constants
	private static final double SCAN_SPEED = 0.50;
	
	// Control variables
	private boolean scanLeft;

    public Scan() {
    	this(Robot.vision.getLatestErrorX() < 0);
    }
    
    public Scan(boolean scanLeft) {
    	requires(Robot.vision);
        requires(Robot.driveTrain);
        
        this.scanLeft = scanLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.arcadeDrive(0, (scanLeft) ? -SCAN_SPEED : SCAN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.vision.hasValidTarget();
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
