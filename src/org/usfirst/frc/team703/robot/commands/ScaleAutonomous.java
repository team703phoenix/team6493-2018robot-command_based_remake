package org.usfirst.frc.team703.robot.commands;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleAutonomous extends CommandGroup {

    public ScaleAutonomous() {
        if (Robot.scaleIsAdjacent()) {
        	addSequential(new DriveForward(305));
        	addSequential(new Turn(Robot.scaleIsLeft() ? 90 : -90));
        	addSequential(new LiftElevatorToScale());
        	addSequential(new ShootCube());
        	addSequential(new LowerElevatorToBottom());
        } else {
        	addSequential(new CrossBaseline());
        }
    }
}
