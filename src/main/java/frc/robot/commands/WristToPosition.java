/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.LiftSystem;
import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.OI;

/**
 * An example command.  You can replace me with your own command.
 */
public class WristToPosition extends Command {
  
  private LiftSystem lift;
  private Double CurrentAngle;
  private Double Goal;
  private Double Speed;
  private Double BufferZone = 20.0;
  

 

  public enum WristPosition {
 
    Hatch(180.0), Cargo(90.0);
    public final Double value;

	  WristPosition(Double InitValue) {
        this.value = InitValue;
    }

  }
  
  
  public WristToPosition(WristPosition targetAngle) {
    lift = LiftSystem.getInstance();
    Speed = 0.75;
    Goal = targetAngle.value;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {



   if (Goal == WristPosition.Cargo.value){

     lift.wristUp(Speed);

   } else if (Goal == WristPosition.Hatch.value){

     lift.wristDown(Speed);

   }else {
     lift.wristStop();
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
    lift.wristStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    lift.wristStop();
  }


}
