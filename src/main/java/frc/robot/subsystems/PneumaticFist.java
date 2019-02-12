/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;

/**
 * Sets up the fist(the thing that grabs the hatch)
 */

public class PneumaticFist extends Subsystem {
  private DoubleSolenoid pneumaticSuspension;
  private static final PneumaticFist INSTANCE = new PneumaticFist();

  public PneumaticFist() {
    initializePneumatics();
  }

  @Override
  public void initDefaultCommand() {
  }

  public static PneumaticFist getInstance() {
    return INSTANCE;
  }

  private void initializePneumatics() {
    pneumaticSuspension = new DoubleSolenoid(RobotMap.GRIPPER_EXTRACT, RobotMap.GRIPPER_RETRACT);
  }

  public void pneumaticIn() {
    pneumaticSuspension.set(Value.kReverse);
  }

  public void pneumaticOut() {
    pneumaticSuspension.set(Value.kForward);
  }

  public boolean isOut() {
    if (pneumaticSuspension.get().equals(Value.kForward)) {
      return true;
    } else {
      return false;
    }

  }

}