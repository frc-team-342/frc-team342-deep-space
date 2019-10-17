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
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command. You can replace me with your own command.
 */
public class PneumaticsWithCANifier extends Command {

  private Knuckles Cylinder = Knuckles.getInstance();
  // TODO put these into RobotMap
  CANifier canifierLimits = new CANifier(RobotMap.CAN_CANI);
  PigeonIMU pigeon = new PigeonIMU(RobotMap.CAN_PIMU);

  public PneumaticsWithCANifier() {
   

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    // System.out.println("pigeon testing:");
    // System.out.println("\t compasheading :" + pigeon.getCompassHeading());
    // System.out.println("\t accelorometer data :");
    // double[] accelerometer = new double[3];
    // pigeon.getAccelerometerAngles(accelerometer);
    // System.out.println("\t\t x: " + accelerometer[0] + "\t y: " +
    // accelerometer[1] + "\t z: " + accelerometer[2]);

    // System.out.println("Limit Switch Test");
    
    if (!canifierLimits.getGeneralInput(GeneralPin.LIMF) && !canifierLimits.getGeneralInput(GeneralPin.LIMR)) {
      Cylinder.pneumaticOut();
      // System.out.println("One of the two limit switches are being pressed");
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
