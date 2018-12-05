package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;
import org.usfirst.frc.team703.robot.subsystems.ArmIntake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabCube extends Command {
	// Constants
	private static final int PICKUP_TIMEOUT = 1100;
	private static final int PICKUP_PAUSE_TIMEOUT = 100;
	private static final int TOTAL_TIMEOUT = PICKUP_TIMEOUT + PICKUP_PAUSE_TIMEOUT;
	
	// Timer
	private static final Timer pickupTimer = new Timer();

    public GrabCube() {
        requires(Robot.driveTrain);
        requires(Robot.armIntake);
        requires(Robot.armClamp);
        requires(Robot.armLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pickupTimer.reset();
    	pickupTimer.start();
    	Robot.armClamp.open();
    	Robot.armLift.down();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (pickupTimer.get() < PICKUP_TIMEOUT) {
    		Robot.armIntake.setSpeed(ArmIntake.AUTO_INTAKE_SPEED, ArmIntake.AUTO_INTAKE_SPEED);
    	} else {
    		Robot.driveTrain.forcedTankDrive(0, 0);
    		Robot.armIntake.setSpeed(0, 0);
    		Robot.armClamp.close();
    		Robot.armLift.up();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pickupTimer.get() >= TOTAL_TIMEOUT;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.forcedTankDrive(0, 0);
		Robot.armIntake.setSpeed(0, 0);
		Robot.armClamp.close();
		Robot.armLift.up();
		pickupTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
