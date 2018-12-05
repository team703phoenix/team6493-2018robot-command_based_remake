package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;
import org.usfirst.frc.team703.robot.subsystems.ArmIntake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCube extends Command {
	// Constants
	private static final int ARM_DISPENSE_TIMEOUT = 700;
	
	// Timer
	private static final Timer shootTimer = new Timer();

    public ShootCube() {
        requires(Robot.armIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shootTimer.reset();
    	shootTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armIntake.setSpeed(-ArmIntake.AUTO_SHOOT_SPEED, -ArmIntake.AUTO_SHOOT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shootTimer.get() >= ARM_DISPENSE_TIMEOUT;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armIntake.setSpeed(0, 0);
    	shootTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
