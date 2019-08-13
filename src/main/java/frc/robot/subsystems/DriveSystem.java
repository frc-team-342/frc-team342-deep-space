/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
import com.revrobotics.CANPIDController; 
import com.revrobotics.ControlType;

public class DriveSystem extends Subsystem {

  public SendableChooser<Boolean> arcade_chooser = new SendableChooser<>();

  // NavX
  AHRS NavX;

  // Instance of the class
  private static final DriveSystem INSTANCE = new DriveSystem();

  private DifferentialDrive myRobot; 

  //setting the motors
  private CANSparkMax leftLead;
  private CANSparkMax leftFollow1;
  private CANSparkMax leftFollow2;
  private CANSparkMax rightLead;
  private CANSparkMax rightFollow1;
  private CANSparkMax rightFollow2; 

  private CANPIDController pidController;
  public double kP, kD, kIz, kFF, kMaxOutput, kMinOutput;  

  // Current Variables
  private static final int AMPS = 35;
  private static final int TIMEOUT_MS = 10;
  private static final int PEAK_CURRENT_TIME = 2000;
  private static final int ZERO = 0;
  private static final double RAMP_TIME = 0.2;

  // Stuff For Slow Button
  private static final double SLOW_DOWN_SCALAR = 2.0;
  private boolean slow;
  private boolean turbo;

  // Encoder Positions
  public static int init_Left;
  public static int init_Right;

  // NavX
  private boolean arcade;

  public DriveSystem() {

    NavX = new AHRS(SPI.Port.kMXP);
    System.out.println("Constructer NavX: " +NavX.getAngle());

    myRobot = new DifferentialDrive(leftLead, rightLead);


    //instantiate motor controllers
    leftLead = new CANSparkMax(DRV_LEFTLEAD, MotorType.kBrushless);
    leftFollow1 = new CANSparkMAx(DRV_LEFTFOLLOW_1, MotorType.kBrushless);
    leftFollow2 = new CANSparkMAx(DRV_LEFTFOLLOW_2, MotorType.kBrushless); 
    rightLead = new CANSparkMax(DRV_RIGHTLEAD, MotorType.kBrushless);
    rightFollow1 = new CANSparkMax(DRV_RIGHTFOLLOW_1, MotorType.kBrushless);
    rightFollow2 = new CANSparkMax(DRV_RIGHTFOLLOW_2, MotorType.kBrushless);

    pidController = leftLead.getPIDController(); 
    pidController = rightLead.getPIDController(); 

    //PID coefficients
    //TODO: find out if these are the values we want
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;                        

    climbDrive = new VictorSPX(RobotMap.CLIMBDRIVE);

    inititalizeDriveSystem();

    arcade_chooser.setDefaultOption("Off", false);
    arcade_chooser.addOption("Arcade", true);
    SmartDashboard.putData("Arcade Mode", arcade_chooser);
    arcade = true;

  }

  @Override
  public void initDefaultCommand() {
  }

  public Boolean getArcade() {
    return arcade;
  }

  public void setArcadeDrive(boolean enable) {
    arcade = enable;
  }

  public static DriveSystem getInstance() {
    return INSTANCE;
  }

  private void inititalizeDriveSystem() {

    // Not Current Limiting DriveSystem() anymore this year.

    /*
     * leftMaster.configPeakCurrentLimit(ZERO, ZERO);
     * leftMaster.configPeakCurrentDuration(ZERO, ZERO);
     * leftMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * leftMaster.enableCurrentLimit(true);
     * 
     * leftSlave1.configPeakCurrentLimit(ZERO, ZERO);
     * leftSlave1.configPeakCurrentDuration(ZERO, ZERO);
     * leftSlave1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * leftSlave1.enableCurrentLimit(true);
     * 
     * rightMaster.configPeakCurrentLimit(ZERO, ZERO);
     * rightMaster.configPeakCurrentDuration(ZERO, ZERO);
     * rightMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * rightMaster.enableCurrentLimit(true);
     * 
     * rightSlave1.configPeakCurrentLimit(ZERO, ZERO);
     * rightSlave1.configPeakCurrentDuration(ZERO, ZERO);
     * rightSlave1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * rightSlave1.enableCurrentLimit(true);
     * 
     * leftSlave2.configPeakCurrentLimit(ZERO, ZERO);
     * leftSlave2.configPeakCurrentDuration(ZERO, ZERO);
     * leftSlave2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * leftSlave2.enableCurrentLimit(true);
     * 
     * leftSlave3.configPeakCurrentLimit(ZERO, ZERO);
     * leftSlave3.configPeakCurrentDuration(ZERO, ZERO);
     * leftSlave3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * leftSlave3.enableCurrentLimit(true);
     * 
     * rightSlave2.configPeakCurrentLimit(ZERO, ZERO);
     * rightSlave2.configPeakCurrentDuration(ZERO, ZERO);
     * rightSlave2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * rightSlave2.enableCurrentLimit(true);
     * 
     * rightSlave3.configPeakCurrentLimit(ZERO, ZERO);
     * rightSlave3.configPeakCurrentDuration(ZERO, ZERO);
     * rightSlave3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
     * rightSlave3.enableCurrentLimit(true);
     */
    
    //climbDrive.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
    //climbDrive.configPeakCurrentDuration(PEAK_CURRENT_TIME, TIMEOUT_MS);
    //climbDrive.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    //climbDrive.enableCurrentLimit(true);

    /*
      TODO: Transfer this over to SparkMax
    Configures the open loop ramp to set the motor to ramp up to speed after a
    specifiec time other than jerking to full speed.

    leftMaster.configOpenloopRamp(RAMP_TIME, 0);
    leftSlave1.configOpenloopRamp(RAMP_TIME, 0);
    leftSlave2.configOpenloopRamp(RAMP_TIME, 0);

    rightMaster.configOpenloopRamp(RAMP_TIME, 0);
    rightSlave1.configOpenloopRamp(RAMP_TIME, 0);
    rightSlave2.configOpenloopRamp(RAMP_TIME, 0);
    */

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
    leftSlave2.set(ControlMode.PercentOutput, 0.0);

    rightMaster.set(ControlMode.PercentOutput, 0.0);
    rightSlave1.set(ControlMode.PercentOutput, 0.0);
    rightSlave2.set(ControlMode.PercentOutput, 0.0);

    //sets the follows to follow the leads
    leftFollow1.follow(leftLead); 
    leftFollow2.follow(leftLead); 
    rightFollow1.follow(rightLead);
    rightFollow2.follow(rightLead); 

    //set PID coefficients 
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);
    
    pidController.setReference(rotations,ControlType.kPosition); 

    slow = false;
    turbo =false;

    init_Left = getLeftMasterEncoder();
    init_Right = getRightMasterEncoder();

  }

  //TODO convert this slow stuff to the sparks
  public void drive(Double LeftSpeed, Double RightSpeed) {
    setArcadeDrive(arcade_chooser.getSelected());
    if (slow) {
      System.out.println("Changing Speeds to Slow Speeds");
      LeftSpeed = LeftSpeed / SLOW_DOWN_SCALAR;
      RightSpeed = RightSpeed / SLOW_DOWN_SCALAR;
    }else if(turbo){
      System.out.println("Changing Speeds to turbo Speeds");
      LeftSpeed = LeftSpeed *1;
      RightSpeed = RightSpeed *1;
    }else {
      RightSpeed = RightSpeed * .8;
      LeftSpeed = LeftSpeed * .8;
    }

rightMaster.set(ControlMode.PercentOutput, RightSpeed);
rightSlave1.set(ControlMode.PercentOutput, RightSpeed);
rightSlave2.set(ControlMode.PercentOutput, RightSpeed);

leftMaster.set(ControlMode.PercentOutput, LeftSpeed);
leftSlave1.set(ControlMode.PercentOutput, LeftSpeed);
leftSlave2.set(ControlMode.PercentOutput, LeftSpeed);    

  }

  public void stopDrive() {

    rightMaster.set(ControlMode.PercentOutput, 0.0);
    rightSlave1.set(ControlMode.PercentOutput, 0.0);
    rightSlave2.set(ControlMode.PercentOutput, 0.0);

    leftMaster.set(ControlMode.PercentOutput, 0.0);
    leftSlave1.set(ControlMode.PercentOutput, 0.0);
    leftSlave2.set(ControlMode.PercentOutput, 0.0);
  }

  public void driveSetSpeed(double Left_Speed, double Right_Speed) {

    // argument is in position change per 100ms
    rightMaster.set(ControlMode.Velocity, Right_Speed);
    leftMaster.set(ControlMode.Velocity, Left_Speed);

  }

  public int getLeftMasterEncoder() {

    return leftMaster.getSensorCollection().getPulseWidthPosition();
  }

  public int getRightMasterEncoder() {

    return rightMaster.getSensorCollection().getPulseWidthPosition();
  }

/*
  public void setSlow(boolean slowSetting) {
    this.slow = slowSetting;
  }

  public boolean isInSlowMode() {
    return slow;
  }

  public void setTurbo(boolean turboSetting) {
    this.turbo = turboSetting;
  }

  public boolean isInTurboMode() {
    return turbo;
  }
*/

  public double getGyro(boolean backwards) {
    double angle;

    if (backwards) {
      angle = (((((NavX.getAngle() + 180)) % 360) + 360) % 360);
    } else {
      angle = ((((NavX.getAngle()) % 360) + 360) % 360);
    }
    return angle;
  }

  public AHRS getNavX() {
    return NavX;
  }

  public void resetGyro() {
    NavX.reset();
  }

  public double toMeters() {

    double current = getRightMasterEncoder() - init_Right;
    // Conversion Enc Value per wheel Rotation = 1.4
    // meters per wheel rotation = 0.31415
    return (current * 1.4) / 0.31415;

  }

  public void driveWinch(double speed) {
    climbDrive.set(ControlMode.PercentOutput, -speed);
  }

  public double getTilt() {
    return NavX.getPitch();
  }
}
