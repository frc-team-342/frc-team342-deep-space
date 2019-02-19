package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;

public class RotateToAngle extends Command {
	
	private DriveSystem drive;
	
	private double angle;
	private double gyro_angle;
	
	private boolean TurnRight;
	
	private static final double RotateSpeed = 1.0;
	private static final double RotateSlowSpeed=0.4;
	private static final double margin = 10;
	private static final double slowmargin=120;
	
	/**
	 * @param angle
	 *            converted to int in degrees if the angle is greater than or equal
	 *            to 360 it will do a full loop and then turn to the correct angle
	 */
	public RotateToAngle(int angle) {
		
		drive = DriveSystem.getInstance();
		
		
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		drive.resetGyro();
		
		gyro_angle = drive.getGyro(false);

		if (Math.abs(angle) > 180) {

			TurnRight = false;
		} else {
			TurnRight = true;

		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		double CurrentDriveSpeed;
		boolean slowdown = (gyro_angle) <= (angle) + slowmargin && (gyro_angle) >= (angle) - slowmargin;
		
		gyro_angle = drive.getGyro(false);
		
		if (slowdown) {
			CurrentDriveSpeed=RotateSlowSpeed;
		}else {
			CurrentDriveSpeed=RotateSpeed;
		}
	
		if (TurnRight) {
			drive.drive(CurrentDriveSpeed, CurrentDriveSpeed * -1.0);
		} else {
			drive.drive(CurrentDriveSpeed * -1.0, CurrentDriveSpeed);
		}
	}
		

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	
		boolean isFinished = (gyro_angle) <= (angle) + margin && (gyro_angle) >= (angle) - margin;
		
		if (isFinished) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		
		drive.stopDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
		end();
	}
}