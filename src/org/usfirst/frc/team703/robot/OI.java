/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team703.robot;

import org.usfirst.frc.team703.robot.commands.ControlClamp;
import org.usfirst.frc.team703.robot.commands.ControlLift;
import org.usfirst.frc.team703.robot.commands.ControlVisionLight;
import org.usfirst.frc.team703.robot.commands.FollowTarget;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	// Axis constants
	public static final int LEFT_DRIVE_AXIS = 1;
	public static final int RIGHT_DRIVE_AXIS = 3;
	public static final int ELEVATOR_DRIVE_AXIS = 1;
	public static final int LEFT_ARM_INTAKE_AXIS = 2;
	public static final int RIGHT_ARM_INTAKE_AXIS = 3;
	
	// Button constants
	public static final int[] ARM_SHOOT_BTNS = {5, 6};
	public static final int TOGGLE_ARM_CLAMP_BTN = 10;
	public static final int TOGGLE_ARM_LIFT_BTN = 9;
	public static final int FOLLOW_TARGET_BTN = 9;
	public static final int FOLLOW_TARGET_STOP_BTN = 1;
	public static final int TOGGLE_VISION_LIGHT_BTN = 3;
	
	// Controllers
	public static Joystick driverCont = new Joystick(0);
	public static Joystick operatorCont = new Joystick(1);
	
	public OI() {
		JoystickButton toggleArmClamp = new JoystickButton(operatorCont, TOGGLE_ARM_CLAMP_BTN),
					   toggleArmLift = new JoystickButton(operatorCont, TOGGLE_ARM_LIFT_BTN),
					   followTarget = new JoystickButton(driverCont, FOLLOW_TARGET_BTN),
					   followTargetStop = new JoystickButton(driverCont, FOLLOW_TARGET_STOP_BTN),
					   toggleVisionLight = new JoystickButton(driverCont, TOGGLE_VISION_LIGHT_BTN);
		
		JoystickButton[] armShoot = {new JoystickButton(operatorCont, ARM_SHOOT_BTNS[0]), new JoystickButton(operatorCont, ARM_SHOOT_BTNS[1])};
		
		toggleArmClamp.toggleWhenPressed(new ControlClamp());
		toggleArmLift.toggleWhenPressed(new ControlLift());
		
		followTarget.whenPressed(new FollowTarget());
		followTargetStop.cancelWhenPressed(new FollowTarget());
		
		toggleVisionLight.toggleWhenPressed(new ControlVisionLight());
		
		Robot.armIntake.setReverse(armShoot[0].get() && armShoot[1].get());
	}
	
	public double getLeftDrive() {
		return driverCont.getRawAxis(LEFT_DRIVE_AXIS);
	}
	
	public double getRightDrive() {
		return driverCont.getRawAxis(RIGHT_DRIVE_AXIS);
	}
	
	public double getElevatorDrive() {
		return operatorCont.getRawAxis(ELEVATOR_DRIVE_AXIS);
	}
	
	public double getLeftArmIntake() {
		return operatorCont.getRawAxis(LEFT_ARM_INTAKE_AXIS);
	}
	
	public double getRightArmIntake() {
		return operatorCont.getRawAxis(RIGHT_ARM_INTAKE_AXIS);
	}
}
