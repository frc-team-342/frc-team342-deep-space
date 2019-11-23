/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class BlockPneumatics extends Subsystem {
  private static BlockPneumatics INSTANCE = new BlockPneumatics();

  // Compressor and Pneumatics
  private DoubleSolenoid stopPneu;
  private boolean isOut = true;
  private Compressor stopComp = new Compressor(RobotMap.pneuCanID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public BlockPneumatics(){
    stopPneu = new DoubleSolenoid(RobotMap.pneuCanID, RobotMap.stopPneuIn, RobotMap.stopPneuOut);
    stopComp.setClosedLoopControl(true);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void PneuIn(){
    System.out.println("Sending Stop Pneumatics In");
    stopPneu.set(DoubleSolenoid.Value.kForward);
    isOut = false;
  }
  public void PneuOut(){
    System.out.println("Sending Stop Pneumatics Out");
    stopPneu.set(DoubleSolenoid.Value.kReverse);
    isOut = true;
  }
  public static BlockPneumatics getInstance(){
    return INSTANCE;
  }

  public boolean IsOut(){
    return isOut;
  }
} 
