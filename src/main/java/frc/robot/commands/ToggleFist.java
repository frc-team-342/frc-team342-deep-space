/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.subsystems.PneumaticFist;

/* 
 * Controls whether the fist is closed or open to collect hatches 
 */

public class ToggleFist extends Command {
  private PneumaticFist Cylinder = PneumaticFist.getInstance();

  public ToggleFist() {
    requires(Robot.m_subsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Cylinder.isOut()) {
      Cylinder.pneumaticIn();
    } else {
      Cylinder.pneumaticOut();
    }

  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
