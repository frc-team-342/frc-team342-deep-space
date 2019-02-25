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
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class LiftToHeightPID extends Command {
  private LiftSystem lift;
  private LiftPosition liftposition;

  public enum LiftPosition {
    HatchLowRocket(-296 /*-220*/), HatchMiddleRocket(-737), HatchHighRocket(-1250), 
    HatchonCargoShip(-261),CargoCargoShip(-591),CargoLowRocket(-348), 
    CargoMiddleRocket(-787), CargoHighRocket(-1270);
    public final int value;

    LiftPosition(int InitValue) {
      this.value = InitValue;
    }

  }
  public LiftToHeightPID(LiftPosition Position) {
    // Use requires() here to declare subsystem dependencies
    liftposition = Position;
    lift = LiftSystem.getInstance();
    //requires(lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    lift.liftUpWithPosition(liftposition.value);
    System.out.println("liftposition " + liftposition.value +" hatchmode: "+ lift.getHatchMode());
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
