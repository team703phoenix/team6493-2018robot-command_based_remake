package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlVisionLight extends Command {

    public ControlVisionLight() {
    	// requires(Robot.vision);
    	// Doesn't actually require vision in order to allow the light to be toggled while using vision
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.turnOffLED();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.vision.turnOnLED();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
