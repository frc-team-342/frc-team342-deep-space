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
import frc.robot.OI;
import com.ctre.phoenix.sensors.PigeonIMU;

/**
 * An example command.  You can replace me with your own command.
 */
public class WristWithJoystick extends Command {
  private LiftSystem lift;
  private OI oi;
  private static final double DEADZONE = 0.2;
  private double LeftJoystickValue;
  private double [] ypr = new double[3];
  PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEONIMU);
  private double PickupMode= 180;
  private double HatchMode = 180;
  private double CargoMode = -90;


  public WristWithJoystick() {
    oi = OI.getInstance();
    lift = LiftSystem.getInstance();
   
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {


    


    LeftJoystickValue= oi.getJoystickManipulatorLeftYAxis() * -1.0;

     pigeon.getAccelerometerAngles(ypr);
     //System.out.println("Pitch is "+ypr[1]);
     //System.out.println("Roll is "+ ypr[2]);
      //System.out.println("Yaw is " + ypr[0]);
     if (LeftJoystickValue > DEADZONE ){
      lift.wristUp(Math.abs(LeftJoystickValue));
      System.out.println("Wrist Going Up");
    } else if (LeftJoystickValue < DEADZONE){
      lift.wristDown(Math.abs(LeftJoystickValue));
     
    } else {
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
    end();
  }
}
