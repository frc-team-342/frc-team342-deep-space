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
public class TurboDrive extends Command { 
  

 
    private DriveSystem Bob;
  
      public TurboDrive() {
     
      
        Bob = DriveSystem.getInstance();

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Bob.isInTurboMode()) {
        System.out.println("Setting Slow to " + Bob.isInTurboMode());
        Bob.setTurbo(false);  

      }else {
        Bob.setTurbo(true);
        System.out.println("Setting Slow to "+ Bob.isInTurboMode());
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
