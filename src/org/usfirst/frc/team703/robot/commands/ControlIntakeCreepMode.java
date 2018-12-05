package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlIntakeCreepMode extends Command {

    public ControlIntakeCreepMode() {
    	// requires(Robot.armIntake);
    	// Doesn't actually require the arm intake in order to allow creep mode to be set while running the intake
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armIntake.setCreepMode(true);
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
    	Robot.armIntake.setCreepMode(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
