/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team703.robot;

import org.usfirst.frc.team703.robot.commands.CrossBaseline;
import org.usfirst.frc.team703.robot.commands.ScaleAutonomous;
import org.usfirst.frc.team703.robot.commands.SideSwitchAutonomous;
import org.usfirst.frc.team703.robot.subsystems.ArmClamp;
import org.usfirst.frc.team703.robot.subsystems.ArmIntake;
import org.usfirst.frc.team703.robot.subsystems.ArmLift;
import org.usfirst.frc.team703.robot.subsystems.DriveTrain;
import org.usfirst.frc.team703.robot.subsystems.Elevator;
import org.usfirst.frc.team703.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static ArmIntake armIntake;
	public static ArmClamp armClamp;
	public static ArmLift armLift;
	public static Vision vision;
	public static Dashboard dashboard;
	
	public Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		armIntake = new ArmIntake();
		armClamp = new ArmClamp();
		armLift = new ArmLift();
		vision = new Vision();
		dashboard = new Dashboard();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Read from dashboard
		dashboard.readFromDashboard();
		
		// Determine autonomous path
		selectAutonomous();
		
		// Begin autonomous
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public static String getGameData() {
		return DriverStation.getInstance().getGameSpecificMessage();
	}
	
	public static boolean switchIsAdjacent() {
 		return getGameData().charAt(0) == dashboard.startingPos.charAt(0);
 	}
	
	public static boolean switchIsLeft() {
		return getGameData().charAt(0) == 'L';
	}
	
	public static boolean scaleIsAdjacent() {
		return getGameData().charAt(1) == dashboard.startingPos.charAt(0);
	}
	
	public static boolean scaleIsLeft() {
		return getGameData().charAt(1) == 'L';
	}
	
	public void selectAutonomous() {
		if (dashboard.destination != -1) { // -1 means don't move
			if (dashboard.destination == 0) { // Cross baseline
				autonomousCommand = new CrossBaseline();
			} else {
				if (getGameData().charAt(dashboard.destination - 1) == dashboard.startingPos.charAt(0) || dashboard.isAbsolute) { 
	    			switch (dashboard.destination) {
	    				case 1: autonomousCommand = new SideSwitchAutonomous(); break;
	    				case 2: autonomousCommand = new ScaleAutonomous(); break;
	    				default: throw new RuntimeException("Valid inputs for the auton selection are -1, 0, 1, or 2.");
	    			}
	    		} else {
	    			if ((dashboard.destination == 2 && dashboard.prioritizeSwitch) || (dashboard.destination == 1 && !dashboard.prioritizeSwitch))
	    				autonomousCommand = new CrossBaseline();
	    			else {
	    				if (dashboard.prioritizeSwitch)
	    					dashboard.destination += 1;
	    				else
	    					dashboard.destination -= 1;
	    				selectAutonomous();
	    			}
	    		}
			}
		}
	}
}
