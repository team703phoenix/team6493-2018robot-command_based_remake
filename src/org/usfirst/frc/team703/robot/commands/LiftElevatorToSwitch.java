package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;
import org.usfirst.frc.team703.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftElevatorToSwitch extends Command {
	// Timer
	private static final Timer liftTimer = new Timer();

    public LiftElevatorToSwitch() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftTimer.reset();
    	liftTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return liftTimer.get() >= Elevator.ELEVATOR_TIMEOUT_SWITCH;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    	liftTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
