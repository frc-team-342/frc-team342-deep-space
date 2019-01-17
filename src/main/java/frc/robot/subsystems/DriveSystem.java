/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;


/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveSystem extends Subsystem {
  
    //Instance of the class
    private static final DriveSystem INSTANCE = new DriveSystem();


    // Motor Controllers
    private TalonSRX leftMaster;
    private TalonSRX rightMaster;
    private TalonSRX leftSlave1;
    private TalonSRX leftSlave2;
    private TalonSRX leftSlave3;
    private TalonSRX rightSlave1;
    private TalonSRX rightSlave2;
    private TalonSRX rightSlave3;


   
     public DriveSystem() {

      //Instantiate Motor Controllers
      leftMaster = new TalonSRX(RobotMap.LEFTMASTER);
      rightMaster = new TalonSRX(RobotMap.RIGHTMASTER);
      leftSlave1 = new TalonSRX(RobotMap.LEFTSLAVE1);
      leftSlave2 = new TalonSRX(RobotMap.LEFTSLAVE2);
      leftSlave3 = new TalonSRX(RobotMap.LEFTSLAVE3);
      rightSlave1 = new TalonSRX(RobotMap.RIGHTSLAVE1);
      rightSlave2 = new TalonSRX(RobotMap.RIGHTSLAVE2);
      rightSlave3 = new TalonSRX(RobotMap.RIGHTSLAVE3);


      inititalizeDriveSystem();
        
    }

    @Override
    public void initDefaultCommand() {


  }
  
   
  public static DriveSystem getInstance() {

    return INSTANCE;
  }

  private void inititalizeDriveSystem() {


  }

  


  public void drive(Double LeftSpeed, Double RightSpeed) {
   
    System.out.println(RightSpeed);
    RightSpeed = RightSpeed * .75;
    rightMaster.set(ControlMode.PercentOutput, RightSpeed);
    rightSlave1.set(ControlMode.PercentOutput, RightSpeed);
    rightSlave2.set(ControlMode.PercentOutput, RightSpeed);
    rightSlave3.set(ControlMode.PercentOutput, RightSpeed);

    LeftSpeed = LeftSpeed * .75;
    leftMaster.set(ControlMode.PercentOutput, LeftSpeed);
    leftSlave1.set(ControlMode.PercentOutput, LeftSpeed);
    leftSlave2.set(ControlMode.PercentOutput, LeftSpeed);
    leftSlave3.set(ControlMode.PercentOutput, LeftSpeed);

  }





  public void stopDrive(){
  
    rightMaster.set(ControlMode.PercentOutput, 0.0);
    rightSlave1.set(ControlMode.PercentOutput, 0.0);
    rightSlave2.set(ControlMode.PercentOutput, 0.0);
    rightSlave3.set(ControlMode.PercentOutput, 0.0);

    leftMaster.set(ControlMode.PercentOutput, 0.0);
    leftSlave1.set(ControlMode.PercentOutput, 0.0);
    leftSlave2.set(ControlMode.PercentOutput, 0.0);
    leftSlave3.set(ControlMode.PercentOutput, 0.0);
   }


}
