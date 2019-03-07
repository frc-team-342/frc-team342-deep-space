/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LiftSystem extends Subsystem {
  
  private static final LiftSystem INSTANCE = new LiftSystem();

  private TalonSRX liftMaster;
  private TalonSRX liftFollow;
  private TalonSRX liftWrist;
  private static double maxWristAngle = 187;
  private static double minWristAngle = 90;
  private static double wobble = 10;
  private boolean Override = false;
 

  // varibles for current 
  private int amps = 25;
  private int amps2 = 35;
  private int wristamps =15;
  private int timeout = 10; 
  private int milliseconds = 2000;
  public double TrueZero;
  public double DistanceFromZero;
  private double [] ypr = new double[3];
  PigeonIMU pigeon = new PigeonIMU(RobotMap.CAN_PIMU);
  private boolean isInHatchMode = false;
  private boolean isInCargoMode = false;
  private boolean isLifting = false;
   
  private double angle;

  
  DigitalInput limitSwitch1;
  DigitalInput limitSwitch2;

  private static final int TIMEOUT_MS = 1;

  private double holdPosition;
  
  

  
  public LiftSystem() {

    initializeLiftSystem();
    limitSwitch1 =  new DigitalInput(RobotMap.ELEVATOR_LIMIT_SWITCH_UP);
    limitSwitch2 =  new DigitalInput(RobotMap.ELEVATOR_LIMIT_SWITCH_DOWN);
    

  }

  @Override
  public void initDefaultCommand() {
  }
   
  public static LiftSystem getInstance(){
    return INSTANCE;
  }

  private void initializeLiftSystem() {

    liftMaster = new TalonSRX(RobotMap.LFT_MASTER);
    liftFollow = new TalonSRX(RobotMap.LFT_FOLLOW_1);
    liftFollow.follow(liftMaster);
    liftWrist = new TalonSRX(RobotMap.LFT_WRIST);
   
    

    liftMaster.configPeakCurrentLimit(amps2, timeout); 
    liftMaster.configPeakCurrentDuration(milliseconds, timeout);
    liftMaster.configContinuousCurrentLimit(amps, timeout);
    liftMaster.enableCurrentLimit(true);

    liftFollow.configPeakCurrentLimit(amps2, timeout);
		liftFollow.configPeakCurrentDuration(milliseconds, timeout);
		liftFollow.configContinuousCurrentLimit(amps, timeout);
    liftFollow.enableCurrentLimit(true);

    liftWrist.configPeakCurrentLimit(wristamps, timeout); 
    liftWrist.configPeakCurrentDuration(milliseconds, timeout);
    liftWrist.configContinuousCurrentLimit(wristamps, timeout);
    liftWrist.enableCurrentLimit(true);

      // Setting the PID loop for the master controllers
		liftMaster.config_kP(0,1, TIMEOUT_MS);
		liftMaster.config_kI(0,0.002, TIMEOUT_MS);
		liftMaster.config_kD(0,0.1, TIMEOUT_MS);
    liftMaster.config_kF(0,0.06, TIMEOUT_MS);
    liftMaster.configAllowableClosedloopError(1, 1, 10);
    liftMaster.configAllowableClosedloopError(0, 1, 10);
    liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    liftFollow.configAllowableClosedloopError(1, 1, 10);
    liftFollow.configAllowableClosedloopError(0, 1, 10);
    liftFollow.config_kP(0,1, TIMEOUT_MS);
		liftFollow.config_kI(0,0.002, TIMEOUT_MS);
		liftFollow.config_kD(0,0.1, TIMEOUT_MS);
		liftFollow.config_kF(0,0.06, TIMEOUT_MS);

    
  }
  public double GetWristAngle (){
    //get wrist angle from pigeon imu
    pigeon.getAccelerometerAngles(ypr);
    angle = ypr[1];
    angle = convertAngles360(angle);
    return angle;
  }

  
//TODO CHANGE FOR OVERRIDE
  public void liftUp(double speed) {
    if (Override){
      liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
    }else {
      
      if (limitSwitch1.get()){
        liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
        holdPosition = getLiftEncoders();
      }else{
      liftStop();
    }
    
    SmartDashboard.putBoolean("limitswitch1", limitSwitch1.get());
      SmartDashboard.putNumber("encoder", getLiftEncoders());
      SmartDashboard.putNumber("Distance to ZERO", getDistanceToZero());
  }
}

//TODO CHANGE FOR OVERRIDE
  public void liftUpWithPosition(double position){
    
      if (limitSwitch1.get() && limitSwitch2.get()){
       liftMaster.set(ControlMode.Position, position);
       holdPosition = getLiftEncoders();
      }else{
      liftStop();
     }
    }
  



//TODO Change for override
  public void liftDown(double speed) {
    if (Override){
      liftMaster.set(ControlMode.PercentOutput, speed);
    }else {

    
      if (limitSwitch2.get()){
        liftMaster.set(ControlMode.PercentOutput, speed);
        holdPosition = getLiftEncoders();
      
      }else {
        liftStop();
      }
      SmartDashboard.putBoolean("limitswitch2", limitSwitch2.get());
      SmartDashboard.putNumber("encoder", getLiftEncoders());
      SmartDashboard.putNumber("Distance to ZERO", getDistanceToZero());
    }
  }

 
//TODO Change For Override
  public void wristDown(double speed){
    //System.out.println("Down: "+ GetWristAngle());
    if (Override){
      liftWrist.set(ControlMode.PercentOutput, speed);
    }else {

    
      if (GetWristAngle()>= minWristAngle+45){
        liftWrist.set(ControlMode.PercentOutput, speed);
      } else if (GetWristAngle()>= minWristAngle + wobble){
        liftWrist.set(ControlMode.PercentOutput, speed*.75);
      }else {
        wristStop();
      }
    }
  }

  //TODO CHANGE FOR OVERRIDE
  public void wristUp(double speed){
    //System.out.println("Up: "+ GetWristAngle());
    if (Override){
      liftWrist.set(ControlMode.PercentOutput, speed * -1.0);

    }else {

    
      if (GetWristAngle()<= maxWristAngle-45){
        liftWrist.set(ControlMode.PercentOutput, speed * -1.0);
      }
      else if(GetWristAngle()<= maxWristAngle - wobble ){
        liftWrist.set(ControlMode.PercentOutput, speed * -1.0*0.75);
      }else {
        wristStop();
      }
    }
  }
  
  public void wristStop(){
    liftWrist.set(ControlMode.PercentOutput, 0.0);
  }
  public double convertAngles360(double angle){

    if(angle < 0){
      angle = 360 + angle;
    }
    return angle;
  }
	
	public double getLiftEncoders() {
		
		//String encoderposition = liftMaster.getSensorCollection().toString();
		
    double encoderposition = liftMaster.getSensorCollection().getQuadraturePosition();
		
		return encoderposition;
  }
  public void SetTrueZero(){
     this.TrueZero = liftMaster.getSensorCollection().getQuadraturePosition();
     //System.out.println("True Zero is: "+TrueZero);\
     
  }

  public double getDistanceToZero(){
    this.DistanceFromZero=  liftMaster.getSensorCollection().getQuadraturePosition() - TrueZero;
    return DistanceFromZero;

  }
  public void liftStop(){
    //System.out.println("lim1"+limitSwitch1.get()+"lim2"+limitSwitch2.get());
    if(limitSwitch1.get() && limitSwitch2.get()){
      liftMaster.set(ControlMode.Position, holdPosition);
    }
    else{
    liftMaster.set(ControlMode.PercentOutput, 0.0);
      }
    
  }


  public boolean getCargoMode(){
    return isInHatchMode;
  }

  public boolean getHatchMode(){
    return isInHatchMode;
  }
  public void setHatchMode(boolean choice){
  this.isInHatchMode=choice;
  }

  public void setIsLifting(boolean choice){

    //System.out.println("Setting isLifting to: " + choice);
    this.isLifting = choice;
  
  }

  public boolean getIsLifting(){

    return isLifting;
  
  }
  public void resetHoldPosition(){
    holdPosition = getLiftEncoders();
  }

  public void setOverride(boolean choice){
    this.Override = choice;
  }
}
