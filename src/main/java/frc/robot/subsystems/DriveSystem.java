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

    // Current Variables
    private static final int AMPS = 35;
    private static final int TIMEOUT_MS = 1;
    private static final int PEAK_DURATION = 200;
    private static final int AMPS_CENTER = 35;
    private static final int ZERO = 0;
    private static final double RAMP_TIME = 0.2;
    private static final double SLOW_DOWN = 2.0;



   
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

    leftMaster.configPeakCurrentLimit(ZERO, ZERO);
    leftMaster.configPeakCurrentDuration(ZERO, ZERO);
    leftMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    leftMaster.enableCurrentLimit(true);

    leftSlave1.configPeakCurrentLimit(ZERO, ZERO);
    leftSlave1.configPeakCurrentDuration(ZERO, ZERO);
    leftSlave1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    leftSlave1.enableCurrentLimit(true);

    leftSlave2.configPeakCurrentLimit(ZERO, ZERO);
    leftSlave2.configPeakCurrentDuration(ZERO, ZERO);
    leftSlave2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    leftSlave2.enableCurrentLimit(true);

    leftSlave3.configPeakCurrentLimit(ZERO, ZERO);
    leftSlave3.configPeakCurrentDuration(ZERO, ZERO);
    leftSlave3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    leftSlave3.enableCurrentLimit(true);

    rightMaster.configPeakCurrentLimit(ZERO, ZERO);
    rightMaster.configPeakCurrentDuration(ZERO, ZERO);
    rightMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    rightMaster.enableCurrentLimit(true);

    rightSlave1.configPeakCurrentLimit(ZERO, ZERO);
    rightSlave1.configPeakCurrentDuration(ZERO, ZERO);
    rightSlave1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    rightSlave1.enableCurrentLimit(true);

    rightSlave2.configPeakCurrentLimit(ZERO, ZERO);
    rightSlave2.configPeakCurrentDuration(ZERO, ZERO);
    rightSlave2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    rightSlave2.enableCurrentLimit(true);

    rightSlave3.configPeakCurrentLimit(ZERO, ZERO);
    rightSlave3.configPeakCurrentDuration(ZERO, ZERO);
    rightSlave3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    rightSlave3.enableCurrentLimit(true);
    
    //Configures the open loop ramp to set the motor to ramp up to speed after a
    // specifiec time other tha jerking to full speed.
    leftMaster.configOpenloopRamp(RAMP_TIME, 0);
    leftSlave1.configOpenloopRamp(RAMP_TIME, 0);
    leftSlave2.configOpenloopRamp(RAMP_TIME, 0);
    leftSlave3.configOpenloopRamp(RAMP_TIME, 0);

    rightMaster.configOpenloopRamp(RAMP_TIME, 0);
    rightSlave1.configOpenloopRamp(RAMP_TIME, 0);
    rightSlave2.configOpenloopRamp(RAMP_TIME, 0);
    rightSlave3.configOpenloopRamp(RAMP_TIME, 0);

    // Setting the PID loop for the master controllers
		rightMaster.config_kP(0, TIMEOUT_MS);
		rightMaster.config_kI(0, TIMEOUT_MS);
		rightMaster.config_kD(0, TIMEOUT_MS);
		rightMaster.config_kF(0, TIMEOUT_MS);
		
		leftMaster.config_kP(0, TIMEOUT_MS);
		leftMaster.config_kI(0, TIMEOUT_MS);
		leftMaster.config_kD(0, TIMEOUT_MS);
		leftMaster.config_kF(0, TIMEOUT_MS);
		
		leftMaster.set(ControlMode.PercentOutput, 0.0);
		leftSlave1.set(ControlMode.PercentOutput, 0.0);
    leftSlave1.follow(leftMaster);
    leftSlave2.set(ControlMode.PercentOutput, 0.0);
    leftSlave2.follow(leftMaster);
    leftSlave3.set(ControlMode.PercentOutput, 0.0);
    leftSlave3.follow(leftMaster);
    
		
		rightMaster.set(ControlMode.PercentOutput, 0.0);
		rightSlave1.set(ControlMode.PercentOutput, 0.0);
    rightSlave1.follow(rightMaster);
    rightSlave2.set(ControlMode.PercentOutput, 0.0);
    leftSlave2.follow(leftMaster);
    leftSlave3.set(ControlMode.PercentOutput, 0.0);
    leftSlave3.follow(leftMaster);
    

  }

  


  public void drive(Double LeftSpeed, Double RightSpeed) {
   
   // System.out.println(RightSpeed);
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
