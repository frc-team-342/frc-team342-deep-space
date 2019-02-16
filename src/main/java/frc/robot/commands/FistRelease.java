/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.FistSystem;

public class FistRelease extends Command {

  private FistSystem fist;

  public FistRelease() {
    requires(Robot.m_subsystem);
    fist = FistSystem.getInstance();
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    fist.despense();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    fist.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
