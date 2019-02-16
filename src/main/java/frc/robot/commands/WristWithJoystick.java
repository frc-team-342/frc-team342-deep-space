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
  private double angle;
  PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEONIMU);
  private double PickupMode= 180;
  private double HatchMode = 180;
  private double CargoMode = 270;


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

    //get wrist angle from pigeon imu
    pigeon.getAccelerometerAngles(ypr);
    angle = ypr[0];
    angle = convertAngles360(angle);
    //System.out.println("Angle " + angle);
    


    LeftJoystickValue= oi.getJoystickManipulatorLeftYAxis() * -1.0;

     if (LeftJoystickValue > DEADZONE && angle <=270){

      lift.wristUp(Math.abs(LeftJoystickValue));

    } else if (LeftJoystickValue < DEADZONE && angle >= 180){

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

  public double convertAngles360(double angle){

    if(angle < 0){
      angle = 360 + angle;
    }
    return angle;
  }


}
