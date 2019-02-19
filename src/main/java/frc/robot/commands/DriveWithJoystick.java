/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

public class DriveWithJoystick extends Command {
  
  private double speed_y_left;
  private double speed_y_right;
  private static final double DEADZONE = 0.2;
	
  private OI oi;
  private DriveSystem Bob;
  protected static boolean kArcadeStandard_Reported;
  private final double SPEED_CONST = 1.0;

    public DriveWithJoystick() {
      
      System.out.println("In Drive Constructor");
      oi = OI.getInstance();
      Bob = DriveSystem.getInstance();
   

      
    //requires(Robot.m_subsystem);
  }

  
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("Driving");
    if(Bob.getArcade())
    {
      arcadeDrive();
    }
    else
    {
      tankDrive();
    }
    
      
  }

    public void arcadeDrive () 
    {
      //System.out.println("In arcade drive");
      double speed_y_axis = oi.getJoystickDriveLeftYAxis();
      double speed_x_axis = oi.getJoystickDriveRightXAxis();
      double leftSpeed;
      double rightSpeed;

     if (Math.abs(speed_y_axis) > DEADZONE || Math.abs(speed_x_axis) > DEADZONE) {
      if(speed_y_axis > 0.0) {
        
        if(speed_x_axis > 0.0) {
          
          leftSpeed = speed_y_axis - speed_x_axis;
          rightSpeed = Math.max(speed_y_axis, speed_x_axis);
        } else {
          
          leftSpeed = Math.max(speed_y_axis, -speed_x_axis);
          rightSpeed = speed_y_axis + speed_x_axis;
        }
      } else {
        
        if(speed_x_axis > 0.0) {
          
          leftSpeed = -Math.max(-speed_y_axis, speed_x_axis);
          rightSpeed = speed_y_axis + speed_x_axis;
        } else {
          
          leftSpeed = speed_y_axis - speed_x_axis;
          rightSpeed = -Math.max(-speed_y_axis, -speed_x_axis);
        }
      }
      Bob.drive(-1*leftSpeed,rightSpeed);
      } else {
      Bob.drive(0.0, 0.0);
        }
      }

  private void tankDrive()
  {
    //gets and assigns the values and speed's of the axis's and outputs 
      speed_y_left = oi.getJoystickDriveLeftYAxis() * -1.0;
		  speed_y_right = oi.getJoystickDriveRightYAxis();

      if(Math.abs(speed_y_left) > DEADZONE || Math.abs(speed_y_right) > DEADZONE) {
        
        Bob.drive(speed_y_left, speed_y_right);
      }
      else {
        Bob.drive(0.0,0.0);
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      
          Bob.stopDrive();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
