/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSystem;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 * Been tested on
 */

public class DriveToDistance extends Command {

	private DriveSystem Bob;

	private double goal;
	
	//private boolean backwards;
	
	private double init_Left;
	private double init_Right;
	private double current_Left;
	private double current_Right; 
	private double left_rotation_count;
	private double right_rotation_count;
	
	//private double degrees_off_zero;
	private double left_speed;
	private double right_speed;
	
	//private static final double kP = -50;
    private static final double SPEED_CONST = 0.5;
    public static final double TEST_DISTANCE = 1.5;
	


    public DriveToDistance(double distance) {
    /*
    taking out this parameter because we aren't using the gyro right now
    
    , boolean backwards)
    
    */

		Bob = DriveSystem.getInstance();
		//requires(Bob);

		goal = distance;
    /*
    also moving this out of code for now
        
     this.backwards = backwards;

    */

	}

	protected void initialize() {
		
		init_Left = Bob.getLeftMasterEncoder();
		init_Right = Bob.getRightMasterEncoder(); 
		
    }
    
    protected void execute() {

        left_speed = SPEED_CONST;
        right_speed = SPEED_CONST;

        current_Left = Bob.getLeftMasterEncoder() - init_Left;
        current_Right = Bob.getRightMasterEncoder() - init_Right;

        left_rotation_count = Math.abs(current_Left / 4096);
        right_rotation_count = Math.abs(current_Right / 4096);

    /*    if(backwards) {
			Bob.driveSetSpeed(left_speed * -1.0, right_speed * -1.0);
		}else {               
			Bob.driveSetSpeed(left_speed, right_speed);
        }
    */
        Bob.drive(left_speed, right_speed * -1);

        SmartDashboard.putNumber("left", left_rotation_count);
        SmartDashboard.putNumber("right", right_rotation_count);

        System.out.println("left: " + left_rotation_count);
        System.out.println("right: " + right_rotation_count);
   
    }

    @Override
	protected boolean isFinished() {
        
        if(left_rotation_count >= goal || right_rotation_count >= goal) {

            return true;
        
        }else {
            return false;
        }
    }

    protected void end() {
        
        Bob.stopDrive();
    }

    protected void interrupted() {
        
        end();
    }
}