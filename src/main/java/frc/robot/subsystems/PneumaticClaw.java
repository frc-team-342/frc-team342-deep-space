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


public class PneumaticClaw extends Subsystem {

  private DoubleSolenoid pneumaticSuspension; 
  private static final PneumaticClaw INSTANCE = new PneumaticClaw();
  public PneumaticClaw(){
    initializePneumatics();
  }
   
  @Override
  public void initDefaultCommand() {


}

public static PneumaticClaw getInstance() {

  return INSTANCE;
}

  //@Override
  private void initializePneumatics() {
    pneumaticSuspension = new DoubleSolenoid(RobotMap.PNEUMATICCLAW_OPEN, RobotMap.PNEUMATICCLAW_CLOSED); 
  }
 
  public void pneumaticIn() {
   pneumaticSuspension.set(Value.kReverse); 
  } 

  public void pneumaticOut(){
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
