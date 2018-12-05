package org.usfirst.frc.team703.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForwardAndLiftElevatorToSwitch extends CommandGroup {

    public DriveForwardAndLiftElevatorToSwitch() {
        addParallel(new DriveForward(16));
        addSequential(new LiftElevatorToSwitch());
    }
}
