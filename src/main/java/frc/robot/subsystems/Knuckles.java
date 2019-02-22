/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;

public class Knuckles extends Subsystem {

  private DoubleSolenoid pneumaticSuspension;
  private static final Knuckles INSTANCE = new Knuckles();
  Compressor c = new Compressor(RobotMap.CAN_PCM);
  private boolean isOut = false;
  private boolean isOpening = false;



  public Knuckles() {
    initializePneumatics();
    c.setClosedLoopControl(true);
    System.out.println("In Knuckles Constructor");
    System.out.println(c.enabled());
    //SmartDashboard.putData(c);
  }

  @Override
  public void initDefaultCommand() {

  }

  public static Knuckles getInstance() {

    return INSTANCE;
  }

  // @Override
  private void initializePneumatics() {
    pneumaticSuspension = new DoubleSolenoid(RobotMap.CAN_PCM,RobotMap.HATCH_GRP_O, RobotMap.HATCH_GRP_C);
  }

  public void pneumaticIn() {
    pneumaticSuspension.set(Value.kReverse);
    isOut = false;
  }

  public void pneumaticOut() {
    pneumaticSuspension.set(Value.kForward);
    isOut = true;
  }

  public boolean isOut() {
   return isOut;

  }
  public boolean isOpening(){
    return isOpening;
  }

  public void setIsOpening (boolean choice){
    isOpening = choice;
  }

}
