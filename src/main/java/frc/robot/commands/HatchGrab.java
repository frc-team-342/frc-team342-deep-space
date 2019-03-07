/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.GeneralPin;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.Knuckles;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.LiftSystem;

/**
 * An example command. You can replace me with your own command.
 */
public class HatchGrab extends Command {

  private Knuckles Cylinder = Knuckles.getInstance();
  private LiftSystem lift;
  // TODO put these into RobotMap
  CANifier canifierLimits = new CANifier(RobotMap.CAN_CANI);
  PigeonIMU pigeon = new PigeonIMU(RobotMap.CAN_PIMU);
  private  OI oi;
  private long start_time;
  private long current_time;
  private long duration_ms = 2000;
  private long trigger_time;


  public HatchGrab() {
    System.out.println("In Hatchgrab Constructor");
    oi = OI.getInstance();
    lift = LiftSystem.getInstance();

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    start_time = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("Checking: " +
    // canifierLimits.getGeneralInput(GeneralPin.LIMF));
    // System.out.println("pigeon testing:");
    // System.out.println("\t compasheading :" + pigeon.getCompassHeading());
    // System.out.println("\t accelorometer data :");
    // double[] accelerometer = new double[3];
    // pigeon.getAccelerometerAngles(accelerometer);
    // System.out.println("\t\t x: " + accelerometer[0] + "\t y: " +
    // accelerometer[1] + "\t z: " + accelerometer[2]);
    current_time = System.currentTimeMillis() - start_time;
    //System.out.println("Time is: "+current_time);

    // System.out.println("Limit Switch Test");
    if (!canifierLimits.getGeneralInput(GeneralPin.LIMF) && !canifierLimits.getGeneralInput(GeneralPin.LIMR) && !Cylinder.isOpening()) {
      Cylinder.pneumaticOut();
      if(trigger_time + duration_ms > current_time){
        oi.DriveRumble(1.0);
        System.out.println("Tarantula");
      }
      else{
        oi.DriveRumble(0.0);
        System.out.println("cow");
      }
    }
    else{
      
      trigger_time = current_time;
      oi.DriveRumble(0.0);
    }
      
    }

    @Override
    protected boolean isFinished() {
      return false;
    }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    oi.DriveRumble(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
