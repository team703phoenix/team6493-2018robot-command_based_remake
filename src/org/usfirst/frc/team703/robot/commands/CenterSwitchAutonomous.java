package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutonomous extends CommandGroup {

    public CenterSwitchAutonomous() {
    	addSequential(new DriveForward(20));
    	addSequential(new Turn(Robot.switchIsLeft() ? -45 : 45));
    	addSequential(new DriveForward(Robot.switchIsLeft() ? 65 : 59));
    	addSequential(new Turn(Robot.switchIsLeft() ? 45 : -45));
    	
    	addSequential(new DriveTowardTarget(!Robot.switchIsLeft()));
    	addSequential(new LiftElevatorToSwitch());
    	addSequential(new ShootCube());
    	
    	for (int i = 2; i <= Robot.dashboard.numOfCubes; i++) {
    		addSequential(new LowerElevatorToBottom());
    		
    		addSequential(new DriveBackward(40));
    		addParallel(new LowerArm());
    		addSequential(new Turn(Robot.switchIsLeft() ? 45 : -45));
    		addSequential(new PickupCube());
    		
    		addSequential(new DriveBackward(30));
    		addSequential(new Turn(Robot.switchIsLeft() ? -30 : 30));
    		addSequential(new DriveTowardTarget(Robot.switchIsLeft()));
    		
    		addSequential(new LiftElevatorToSwitch());
        	addSequential(new ShootCube());
    	}
    }
}
