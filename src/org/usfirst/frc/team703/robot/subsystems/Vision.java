package org.usfirst.frc.team703.robot.subsystems;

import org.usfirst.frc.team703.robot.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
	// Constants
	public final double VISION_TURNING_SCALER = 0.015;
	
	// Limelight output
	private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
	
	// Control variables
	private double latestErrorX;

    public void initDefaultCommand() {
    }
    
    /** Configures the vision pipeline to work with the cube (max exposure, LEDs off) */
	public void setCubePipeline() {
		setCubePipeline(0);
	}
	
	/** Configures the vision pipeline to work with the cube (max exposure, LEDs off) and pauses for a given amount of
	 * milliseconds */
	public void setCubePipeline(int timeoutMs) {
		turnOffLED();
		setNumber("pipeline", 0);
		Timer.delay(timeoutMs);
	} 
	
	/** Configures the vision pipeline to work with the vision target (min exposure, LEDs on) */
	public void setTargetPipeline() {
		setTargetPipeline(0);
	}
	
	/** Configures the vision pipeline to work with the vision target (min exposure, LEDs on) and pauses for a given
	 * amount of milliseconds */
	public void setTargetPipeline(int timeoutMs) {
		turnOnLED();
		setNumber("pipeline", 1);
		Timer.delay(timeoutMs);
	}
	
	/** Returns true if the limelight has a valid target in its sights */
	public boolean hasValidTarget() {
		return getNumber("tv", 0) == 1;
	}
	
	/** Returns the percentage of the screen that the highlighted target is taking up, used to find distance */
	public double getPercentageOfScreen() {
		return getDouble("ta", 0);
	}
	
	/** Returns how far off center the found object is in the X direction (positive value means the object is to the
	 * right of center, negative value means the object is to the left of center) */
	public double getErrorX() {
		return getDouble("tx", 0);
	}
	
	/** Updates the latest error in the x direction if the target is in view of the limelight */
	public void updateLatestErrorX() {
		if (hasValidTarget())
			latestErrorX = getDouble("tx", 0);
	}
	
	/** Returns the latest error in the x direction (used to enable the limelight to remember which direction to scan)
	 * in) */
	public double getLatestErrorX() {
		return latestErrorX;
	}
	
	/** Returns how far off center the found object is in the Y direction (positive value means the object is above
	 * center, negative value means the object is to below center) */
	public double getErrorY() {
		return getDouble("ty", 0);
	}
	
	/** Sets the given key to the given value */
	private void setNumber(String key, int value) {
		limelight.getEntry(key).setNumber(value);
	}
	
	/** Finds an integer from the limelight output using the given key */
	private int getNumber(String key, int defaultValue) {
		return limelight.getEntry(key).getNumber(defaultValue).intValue();
	}
	
	/** Finds a double from the limelight output using the given key */
	public double getDouble(String key, double defaultValue) {
		return limelight.getEntry(key).getDouble(defaultValue);
	}
	
	/** Turns the limelight's LEDs on */
	public void turnOnLED() {
		setLED(true);
	}
	
	/** Turns the limelight's LEDs off */
	public void turnOffLED() {
		setLED(false);
	}
	
	/** Turns the limelight's LEDs on or off based on the given input */
	public void setLED(boolean on) {
		setNumber("ledMode", on ? 0 : 1);
	}
	
	/** Returns true if the limelight's LEDs are on */
	public boolean isLEDOn() {
		return getNumber("ledMode", -1) == 0;
	}
	
	/** Takes a snapshot with the limelight */
	public void takeSnapshot() {
		setNumber("snapshot", 1);
	}
}

