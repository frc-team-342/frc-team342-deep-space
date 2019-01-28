/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class ToggleSlowDrive extends Command { 
  

 
    private DriveSystem Bob;
  
      public ToggleSlowDrive() {
     
      
        Bob = DriveSystem.getInstance();

    //requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Bob.isInSlowMode()) {
        System.out.println("Setting Slow to " + Bob.isInSlowMode());
        Bob.setSlow(false);  

      }else {

        Bob.setSlow(true);
        System.out.println("Setting Slow to "+ Bob.isInSlowMode());
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      System.out.println("Is Finished");
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
