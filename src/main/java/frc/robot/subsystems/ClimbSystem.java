/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class ClimbSystem extends Subsystem {
  private static ClimbSystem INSTANCE = new ClimbSystem();

  private static final int TIMEOUT_MS = 1;

  // Navx
  private AHRS NavX;

  // Talons
  private TalonSRX frontLeft;
  private TalonSRX frontRight;
  private TalonSRX back;
  private TalonSRX wheel;


  // Targets for Skis
  private double targetRightFrontHeight = 0;
  private double targetLeftFrontHeight = 0;
  //private double targetBackHeight;

  //Holding Values
  private double holdPositionLeft;
  private double holdPositionRight;
  

  private ClimbSystem(){
    // NavX Instantiation and Angle Print
    NavX = DriveSystem.getInstance().getNavX();
    System.out.println("Constructer NavX: " + NavX.getAngle());

    // Talon Instantiation
    frontLeft = new TalonSRX(RobotMap.ski_left);
    frontRight = new TalonSRX(RobotMap.ski_right);
    back = new TalonSRX(RobotMap.back);
    wheel = new TalonSRX(RobotMap.wheel);

    initializeClimbSystem();
  }
  @Override
  public void initDefaultCommand() {
  }

  private void initializeClimbSystem(){
    frontLeft.config_kP(0,1.0, TIMEOUT_MS);
		frontLeft.config_kI(0,0.0, TIMEOUT_MS);
		frontLeft.config_kD(0,0.0, TIMEOUT_MS);
    frontLeft.config_kF(0,1.0, TIMEOUT_MS);
    frontLeft.configAllowableClosedloopError(0, 1, 10);
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
    frontLeft.setInverted(false);
    frontLeft.setSensorPhase(true);
    frontLeft.selectProfileSlot(0, 0);

    frontRight.config_kP(0,1.0, TIMEOUT_MS);
		frontRight.config_kI(0,0.0, TIMEOUT_MS);
		frontRight.config_kD(0,0.0, TIMEOUT_MS);
    frontRight.config_kF(0,1.0, TIMEOUT_MS);
    frontRight.configAllowableClosedloopError(0, 1, 10);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
    frontRight.setInverted(false);
    frontRight.setSensorPhase(true);
    frontRight.selectProfileSlot(0, 0);

    setTargetFrontHeight();
  }

  public void setTargetFrontHeight(){
    
    double leftEncoder = frontLeft.getSensorCollection().getPulseWidthPosition();
    double rightEncoder = frontRight.getSensorCollection().getPulseWidthPosition();

    targetLeftFrontHeight = leftEncoder;
    targetRightFrontHeight = rightEncoder;

    System.out.println("Resetting");
    System.out.println("Left Target: " + targetLeftFrontHeight);
    System.out.println("Right Target: " + targetRightFrontHeight);
  }

  public void setPositionZero(){
    frontLeft.set(ControlMode.Position, 0.0);
    //frontRight.set(ControlMode.Position, 0);

    System.out.println(frontLeft.getSelectedSensorPosition());
    //System.out.println(frontRight.getSensorCollection().getPulseWidthPosition());
  }
  // 0 = between 2 and 0
  public void liftFrontMotors(){
    double temp = NavX.getRoll();
    double temp2 = frontLeft.getSensorCollection().getPulseWidthPosition();
    double temp3 = frontRight.getSensorCollection().getPulseWidthPosition();

    System.out.println("Target Left Front Height: " +targetLeftFrontHeight);
    System.out.println("Left Encoder: "+temp2);
    System.out.println("Right Encoder: "+temp3);

    // If the pitch is greater than 5 degrees, move the talons down 10 ticks
    if(NavX.getRoll() < -1){
      
      targetLeftFrontHeight -= 15;
      targetRightFrontHeight -= 15;
     
      frontLeft.set(ControlMode.Position, -targetLeftFrontHeight / 2.0);
      frontRight.set(ControlMode.Position, -targetRightFrontHeight / 2.0); 
      
      System.out.println("Dropping: "+temp);
    }
    // If the pitch is less than -5 degrees, move the talons up 10 ticks
    else if(NavX.getRoll() > 3){
      
      targetLeftFrontHeight += 15;
      targetRightFrontHeight += 15;

      frontLeft.set(ControlMode.Position,  -targetLeftFrontHeight / 2.0);
      frontRight.set(ControlMode.Position, -targetRightFrontHeight / 2.0);

      System.out.println("Lifting: " +temp);
    }
    else{
      frontLeft.set(ControlMode.Position, -targetLeftFrontHeight / 2.0);
      frontRight.set(ControlMode.Position, -targetRightFrontHeight / 2.0);
      System.out.println("At Target: " +temp);
    }
   
    
    
  }
  public void liftBackMotor(){
    back.set(ControlMode.PercentOutput, .3);
  }
 
  public void climbStop(){

    frontLeft.set(ControlMode.Position, frontLeft.getSensorCollection().getPulseWidthPosition());

  }
  public void spinBackMotor(){
    wheel.set(ControlMode.PercentOutput, -.3);
  }
  public void STOPBACK(){
    wheel.set(ControlMode.PercentOutput, 0.0);
  }

  public double getPitch() {
    //System.out.println("Pitch: " + NavX.getPitch());
    SmartDashboard.putNumber("Pitch: ", NavX.getPitch());
    return NavX.getPitch();
    
  }

  public double getYaw() {
    //System.out.println("Yaw: " + NavX.getYaw());
    SmartDashboard.putNumber("Yaw: ", NavX.getYaw());
    return NavX.getYaw();
  }

  public double getRoll() {
    //System.out.println("Roll: " + NavX.getRoll());
    SmartDashboard.putNumber("Roll: ", NavX.getRoll());
    return NavX.getRoll();
  }
  public void STOP(){
    frontLeft.set(ControlMode.PercentOutput, 0.0);
    frontRight.set(ControlMode.PercentOutput, 0.0);
    back.set(ControlMode.PercentOutput, 0.0);
    //back.set(ControlMode.PercentOutput, 0.0);
  }
  
  public static ClimbSystem getInstance(){
    return INSTANCE;
  }

}

