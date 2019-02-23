/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.Knuckles;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSystem;

/**
 * An example command. You can replace me with your own command.
 */
public class HatchRelease extends Command {

  private Knuckles Cylinder = Knuckles.getInstance();
  private LiftSystem lift;

  public HatchRelease() {
    
    System.out.println("In HatchRelease Constructor");
    lift = LiftSystem.getInstance();

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      System.out.println("Hatch being released");
  
        Cylinder.pneumaticIn();
        Cylinder.setIsOpening(true);
      
      
    }
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Cylinder.setIsOpening(false);
    lift.setHatchMode(false);
    System.out.println("not in Hatch Mode");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
    end();
  }
}
