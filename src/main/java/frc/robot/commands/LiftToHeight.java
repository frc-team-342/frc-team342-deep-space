/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSystem;

/**
 * An example command. You can replace me with your own command.
 */
public class LiftToHeight extends Command {

  private LiftSystem lift;
  private double CurrentHeight = 0;
  private double Goal;
  private boolean UnderGoal;
  private double init_Lift;

  private double BufferZone = 100.0;


  public enum LiftHeight {
    LowRocket(15000), MiddleRocket(30000), HighRocket(35000), HatchonCargoShip(-261);
    public final int value;

    LiftHeight(int InitValue) {
      this.value = InitValue;
    }

  }

  public LiftToHeight(LiftHeight Height) {
    lift = LiftSystem.getInstance();
    Goal = Height.value;
    // requires(lift);
  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    init_Lift = lift.getLiftEncoders();
    UnderGoal = CurrentHeight <= Goal;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    CurrentHeight = (lift.getLiftEncoders() - init_Lift) + lift.getDistanceToZero();
   // System.out.println("Current Height: " + CurrentHeight);


    // System.out.println("Goal is " + Goal);
    UnderGoal = (CurrentHeight >= Goal);


    if (UnderGoal) {
      lift.liftUp(.5);
      lift.setIsLifting(true);
      SmartDashboard.putNumber("Height", CurrentHeight);
      // System.out.println("height " + CurrentHeight);
    } else {
      lift.liftDown(.25);
      lift.setIsLifting(true);
      SmartDashboard.putNumber("Height: ", CurrentHeight);
      // System.out.println("Height: " + CurrentHeight);

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean IsInDeadzone = CurrentHeight > (Goal - 10.0) && CurrentHeight < (Goal + 5.0);
    //System.out.println("Checking Deadzone: " + IsInDeadzone);
    
    return IsInDeadzone;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   lift.setIsLifting(false);
    lift.liftDown(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
