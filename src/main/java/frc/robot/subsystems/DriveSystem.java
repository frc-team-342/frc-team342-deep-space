/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

/*
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
*/

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMaxLowLevel;
//import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
import com.revrobotics.CANPIDController; 
import com.revrobotics.ControlType;
import frc.robot.RobotMap;



public class DriveSystem extends Subsystem {

  //Smart Dashboard arcade mode chooser
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

  private CANPIDController rightPidController;
  private CANPIDController leftPidController;
  private double kP, kD, kI, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;  

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
    System.out.println("Constructer NavX: " + NavX.getAngle());

    //instantiate motor controllers
    leftLead = new CANSparkMax(RobotMap.DRV_LEFTLEAD, MotorType.kBrushless);
    leftFollow1 = new CANSparkMax(RobotMap.DRV_LEFTFOLLOW_1, MotorType.kBrushless);
    leftFollow2 = new CANSparkMax(RobotMap.DRV_LEFTFOLLOW_2, MotorType.kBrushless); 
    rightLead = new CANSparkMax(RobotMap.DRV_RIGHTLEAD, MotorType.kBrushless);
    rightFollow1 = new CANSparkMax(RobotMap.DRV_RIGHTFOLLOW_1, MotorType.kBrushless);
    rightFollow2 = new CANSparkMax(RobotMap.DRV_RIGHTFOLLOW_2, MotorType.kBrushless);

    leftPidController = leftLead.getPIDController(); 
    rightPidController = rightLead.getPIDController(); 

    //leftLead.setParameter(4, 1);

    // PID Coefficients
    kP = 4.8e-5; 
    kI = 5.0e-7;
    kD = 0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;                        

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
    leftLead.restoreFactoryDefaults();
    leftFollow1.restoreFactoryDefaults();
    leftFollow2.restoreFactoryDefaults();

    rightLead.restoreFactoryDefaults();
    rightFollow1.restoreFactoryDefaults();
    rightFollow2.restoreFactoryDefaults();


    // Setting the PID loop for the master controllers
    /*rightPidController.setP(kP);
    rightPidController.setI(kI);
    rightPidController.setD(kD);
    rightPidController.setIZone(kIz);
    rightPidController.setFF(kFF);
    //rightPidController.setOutputRange(kMinOutput, kMaxOutput);

    leftPidController.setP(kP);
    leftPidController.setI(kI);
    leftPidController.setD(kD);
    leftPidController.setIZone(kIz);
    leftPidController.setFF(kFF);
    //leftPidController.setOutputRange(kMinOutput, kMaxOutput);*/

    leftLead.set(0.0);
    leftFollow1.set(0.0);
    leftFollow2.set(0.0);

    rightLead.set(0.0);
    rightFollow1.set(0.0);
    rightFollow2.set(0.0);

    //sets the follows to follow the leads
    leftFollow1.follow(leftLead); 
    leftFollow2.follow(leftLead); 
    rightFollow1.follow(rightLead);
    rightFollow2.follow(rightLead); 
  

    slow = false;
    turbo = false;

    rightLead.setSmartCurrentLimit(30);
    rightFollow1.setSmartCurrentLimit(30);
    rightFollow2.setSmartCurrentLimit(30);

    leftLead.setSmartCurrentLimit(30);
    leftFollow1.setSmartCurrentLimit(30);
    leftFollow2.setSmartCurrentLimit(30);

    rightLead.enableVoltageCompensation(12.0);
    rightFollow1.enableVoltageCompensation(12.0);
    rightFollow2.enableVoltageCompensation(12.0);

    leftLead.enableVoltageCompensation(12.0);
    leftFollow1.enableVoltageCompensation(12.0);
    leftFollow2.enableVoltageCompensation(12.0);
  }

  public void drive(double LeftSpeed, double RightSpeed) {
    setArcadeDrive(arcade_chooser.getSelected());
    if (slow) {
      //System.out.println("Changing Speeds to Slow Speeds");
      LeftSpeed = (LeftSpeed * .3) / SLOW_DOWN_SCALAR;
      RightSpeed = (RightSpeed * .3) / SLOW_DOWN_SCALAR;
    }else if(turbo){
      //System.out.println("Changing Speeds to turbo Speeds");
      LeftSpeed = LeftSpeed *.8;
      RightSpeed = RightSpeed *.8;
    }else {
      RightSpeed = RightSpeed * .3;
      LeftSpeed = LeftSpeed * .3;
    }
    System.out.println("Right Speeds: " + RightSpeed + "\nLeft Speeds: " + LeftSpeed);
    rightLead.set(RightSpeed);
    //rightFollow1.set(RightSpeed);
    //rightFollow2.set(RightSpeed);

    leftLead.set(LeftSpeed);
    //leftFollow1.set(LeftSpeed);
    //leftFollow2.set(LeftSpeed); 

  }

  public void stopDrive() {

    rightLead.set(0.0);
    //rightFollow1.set(0.0);
    //rightFollow2.set(0.0);

    leftLead.set(0.0);
    //leftFollow1.set(0.0);
    //leftFollow2.set(0.0);
  }

  public void driveSetSpeed(double LeftSpeed, double RightSpeed) {

    // argument is in position change per 100ms
    rightLead.set(RightSpeed);
    leftLead.set(LeftSpeed);

  }

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

    // Gets pitch, yaw, and roll of robot using navx
  public double getPitch() {
    //System.out.println("Pitch: " + NavX.getPitch());
    return NavX.getPitch();
  }
  public double getYaw() {
    //System.out.println("Yaw: " + NavX.getYaw());
    return NavX.getYaw();
  }
  public double getRoll() {
   // System.out.println("Roll: " + NavX.getRoll());
    return NavX.getRoll();
  }

  public boolean isInSlowMode() {

    return slow;
  }

  public void setSlow(boolean slowSetting) {

    this.slow = slowSetting;
  }

  public void setTurbo(boolean turboSetting) {

    this.turbo = turboSetting;
  }

  public boolean isInTurboMode() {

    return turbo;
  }



}
