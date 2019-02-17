/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap;
import com.ctre.phoenix.sensors.PigeonIMU;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LiftSystem extends Subsystem {
  
  private static final LiftSystem INSTANCE = new LiftSystem();

  private TalonSRX liftMaster;
  private TalonSRX liftFollow;
  private TalonSRX liftWrist;

  // varibles for current 
  private int amps = 5;
  private int timeout = 10; 
  private int milliseconds = 2000;
  public double TrueZero;
  public double DistanceFromZero;
  private double [] ypr = new double[3];
  PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEONIMU);
  private double PickupMode= 180;
  private double HatchMode = 180;
  private double CargoMode = -90;

  
  public LiftSystem() {

    initializeLiftSystem(); 

  }

  @Override
  public void initDefaultCommand() {
  }
   
  public static LiftSystem getInstance(){
    return INSTANCE;
  }

  private void initializeLiftSystem() {

    liftMaster = new TalonSRX(RobotMap.LIFTMASTER);
    liftFollow = new TalonSRX(RobotMap.LIFTFOLLOW);
    liftFollow.follow(liftMaster);
    liftWrist = new TalonSRX(RobotMap.LIFTWRIST);
   
    

    liftMaster.configPeakCurrentLimit(amps, timeout); 
    liftMaster.configPeakCurrentDuration(milliseconds, timeout);
    liftMaster.configContinuousCurrentLimit(amps, timeout);
    liftMaster.enableCurrentLimit(true);

    liftFollow.configPeakCurrentLimit(amps, timeout);
		liftFollow.configPeakCurrentDuration(milliseconds, timeout);
		liftFollow.configContinuousCurrentLimit(amps, timeout);
    liftFollow.enableCurrentLimit(true);

    liftWrist.configPeakCurrentLimit(amps, timeout); 
    liftWrist.configPeakCurrentDuration(milliseconds, timeout);
    liftWrist.configContinuousCurrentLimit(amps, timeout);
    liftWrist.enableCurrentLimit(true);

    
  }
  public double GetWristAngle (){
    pigeon.getAccelerometerAngles(ypr);
    return ypr[1];
  }

  public void wristUp(double speed){
    liftWrist.set(ControlMode.PercentOutput, speed * -1.0);
  }

  public void liftUp(double speed) {
    liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
  }

  public void liftUpWithPosition(double position){
    liftMaster.set(ControlMode.Position, position);
  }

  public void liftDown(double speed) {
    liftMaster.set(ControlMode.PercentOutput, speed);
  }

  //TODO Use these instead of LiftDown and LiftUP for LiftToHeight
  public void liftDownWithPosition(double position){
    liftMaster.set(ControlMode.Position, position);
  }

  public void wristDown(double speed){
    liftWrist.set(ControlMode.PercentOutput, speed);
  }
  
  
	
	public double getLiftEncoders() {
		
		//String encoderposition = liftMaster.getSensorCollection().toString();
		
    double encoderposition = liftMaster.getSensorCollection().getQuadraturePosition();
		
		return encoderposition;
  }
  public void SetTrueZero(){
     this.TrueZero = liftMaster.getSensorCollection().getQuadraturePosition();
     System.out.println("True Zero is: "+TrueZero);
  }

  public double getDistanceToZero(){
    this.DistanceFromZero=  liftMaster.getSensorCollection().getQuadraturePosition() - TrueZero;
    return DistanceFromZero;

  }
  public void liftStop(){
    liftMaster.set(ControlMode.PercentOutput, 0.0);
  }
  public void wristStop(){
    liftWrist.set(ControlMode.PercentOutput, 0.0);
  }


}
