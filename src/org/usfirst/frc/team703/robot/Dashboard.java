package org.usfirst.frc.team703.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	// Sendable choosers
 	private SendableChooser<String> positionInput = new SendableChooser<>();
 	private SendableChooser<Boolean> destinationTypeInput = new SendableChooser<>();
 	private SendableChooser<Boolean> crossFieldInput = new SendableChooser<>();
 	private SendableChooser<Integer> destinationInput = new SendableChooser<>();
 	private SendableChooser<Boolean> priorityInput = new SendableChooser<>();
 	private SendableChooser<String> switchSideInput = new SendableChooser<>();
 	private SendableChooser<String> scaleSideInput = new SendableChooser<>();
 	private SendableChooser<Integer> numOfCubesInput = new SendableChooser<>();
 	
 	// Dashboard inputs
 	public String startingPos, gameData;
 	public boolean isAbsolute, crossField, prioritizeSwitch;
 	public int destination, numOfCubes;
 	
 	public Dashboard() {
		// Publish starting position chooser
		positionInput.setName("Starting position");
		positionInput.addObject("Left position", "L");
		positionInput.addDefault("Center position", "C");
		positionInput.addObject("Right position", "R");
		SmartDashboard.putData(positionInput);
		
		// Publish autonomous destination type chooser
		destinationTypeInput.setName("Autonomous destination type (left & right paths only)");
		destinationTypeInput.addObject("Absolute target", true);
		destinationTypeInput.addDefault("Best option on current side", false);
		SmartDashboard.putData(destinationTypeInput);
		
		// Publish destination priority chooser
		priorityInput.setName("Destination priority (best option only)");
		priorityInput.addDefault("Switch", true);
		priorityInput.addObject("Scale", false);
		SmartDashboard.putData(priorityInput);
		
		// Publish cross field chooser
		crossFieldInput.setName("Can the robot cross the field? (absolute target only)");
		crossFieldInput.addObject("Robot can cross field", true);
		crossFieldInput.addDefault("Robot cannot cross field", false);
		SmartDashboard.putData(crossFieldInput);
		
		// Publish autonomous destination chooser
		destinationInput.setName("Autonomous destination");
		destinationInput.addObject("Cross baseline", 0);
		destinationInput.addObject("Near switch", 1);
		destinationInput.addObject("Scale", 2);
		destinationInput.addDefault("Don't move", -1);
		SmartDashboard.putData(destinationInput);
		
		// Publish switch side chooser
		switchSideInput.setName("Switch side (for testing only)");
		switchSideInput.addObject("Left switch", "Left");
		switchSideInput.addObject("Right switch", "Right");
		switchSideInput.addDefault("Field default", "Field default");
		SmartDashboard.putData(switchSideInput);
		
		// Publish scale side chooser
		scaleSideInput.setName("Scale side (for testing only)");
		scaleSideInput.addObject("Left scale", "Left");
		scaleSideInput.addObject("Right scale", "Right");
		scaleSideInput.addDefault("Field default", "Field default");
		SmartDashboard.putData(scaleSideInput);
		
		// Publish number of cubes chooser
		numOfCubesInput.setName("Number of cubes (center path only)");
		numOfCubesInput.addDefault("1", 1);
		numOfCubesInput.addObject("2", 2);
		numOfCubesInput.addObject("3", 3);
		SmartDashboard.putData(numOfCubesInput);
 	}
 	
 	public void readFromDashboard() {
		// Find starting position
		if (positionInput.getSelected() != null)
			startingPos = positionInput.getSelected();
		
		// Find destination type
		if (destinationTypeInput.getSelected() != null)
			isAbsolute = destinationTypeInput.getSelected();
		
		// Find destination priority
		if (priorityInput.getSelected() != null)
			prioritizeSwitch = priorityInput.getSelected();
		
		// Find cross field instruction
		if (crossFieldInput.getSelected() != null)
			crossField = crossFieldInput.getSelected();
		
		// Find destination
		if (destinationInput.getSelected() != null)
			destination = destinationInput.getSelected();
		
		// Find game data
		if (Robot.getGameData().length() > 0)
			gameData = Robot.getGameData();
		else
			gameData = "LLL";
		
		// Find switch side
		if (switchSideInput.getSelected() != null && !switchSideInput.getSelected().equals("Field default"))
			gameData = switchSideInput.getSelected().charAt(0) + gameData.substring(1);
		
		// Find scale side
		if (scaleSideInput.getSelected() != null && !scaleSideInput.getSelected().equals("Field default"))
			gameData = gameData.substring(0, 1) + scaleSideInput.getSelected().charAt(0) + gameData.substring(2);
		
		// Find number of cubes
		if (numOfCubesInput.getSelected() != null)
			numOfCubes = numOfCubesInput.getSelected();
		
		System.out.println("Current position: " + startingPos);
		System.out.println("Absolute destination: " + isAbsolute);
		System.out.println("Destination: " + destination);
		System.out.println("Game data: " + gameData);
	}
}
