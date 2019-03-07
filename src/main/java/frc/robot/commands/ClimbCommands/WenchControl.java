/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ClimbCommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.ClimbSystem;

/**
 * An example command. You can replace me with your own command.
 */
public class WenchControl extends Command {

  private ClimbSystem climb;
  private OI oi;

  private double WenchSpeed = 0;

  public WenchControl() {
    climb = ClimbSystem.getInstance();
    oi = OI.getInstance();
    
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    WenchSpeed = oi.getJoystickDriveRightTrigger();
    WenchSpeed = WenchSpeed - oi.getJoystickDriveLeftTrigger();

    climb.extend(WenchSpeed);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climb.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
