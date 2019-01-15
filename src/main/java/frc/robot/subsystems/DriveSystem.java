/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSystem extends Subsystem {
  
    //Instance of the class
    private static final DriveSystem INSTANCE = new DriveSystem();


    TalonSRX rightMotor = new TalonSRX(RobotMap.RIGHT_MOTOR);
    TalonSRX leftMotor = new TalonSRX(RobotMap.LEFT_MOTOR);
    private final RobotDrive robotDrive = new robotDrive(leftMotor, rightMotor);

   
     public DriveSystem() {

        inititalizeDriveSystem();
    }

    @Override
    public void initDefaultCommand() {

  }
  public void takeJoystickInputs(Joystick left, Joystick right) {
    robotDrive.tankDrive(left,right);
  }
  
  public void stopDrive(){
    robotDrive.drive(0,0); 
   }
   
  public static DriveSystem getInstance() {

    return INSTANCE;
  }

  private void inititalizeDriveSystem() {

    //Instantiate Motor Controllers
    Left = new TalonSRX(2);
    //Right = new TalonSRX(2);
  }
}
