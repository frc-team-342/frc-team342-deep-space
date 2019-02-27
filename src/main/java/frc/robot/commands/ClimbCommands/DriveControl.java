//method in drive that sets the output on the wench wheel
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.ClimbSystem;
import frc.robot.subsystems.DriveSystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveControl extends Command {

  private ClimbSystem climb;
  private OI oi;
  private DriveSystem drive;

  private double driveSpeed;
  

  public DriveControl() {
    
    climb = ClimbSystem.getInstance();
    oi = OI.getInstance();
    drive = DriveSystem.getInstance();
  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    drive.driveWinch(0.5);
    drive.drive(0.5, -0.5);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.stopDrive();
    drive.driveWinch(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
