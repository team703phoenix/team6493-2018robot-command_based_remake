package org.usfirst.frc.team703.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaseline extends CommandGroup {

    public CrossBaseline() {
        addSequential(new DriveForward(120));
    }
}
