/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class LiftToBottom extends Command {
  private LiftSystem lift;
  //private final int Hatch = -220;
  private final int Hatch = -274;
  private final int Cargo = -348;

  public LiftToBottom(){
    lift = LiftSystem.getInstance();
  }
 
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(" hatchmode: "+ lift.getHatchMode());
    if (lift.getHatchMode()){
      lift.liftUpWithPosition(this.Hatch);
      System.out.print(" Position: " +this.Hatch);
    }else {
      lift.liftUpWithPosition(this.Cargo);
      System.out.print(" Position: " +this.Cargo);
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
    lift.liftStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
