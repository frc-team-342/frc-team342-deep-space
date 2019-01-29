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


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LiftSystem extends Subsystem {
  
  private static final LiftSystem INSTANCE = new LiftSystem();

  private TalonSRX liftMaster;
  private TalonSRX liftFollow;

  // varibles for current 
  private int amps = 10;
  private int timeout = 10; 
  private int milliseconds = 2000;
  
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
    

    liftMaster.configPeakCurrentLimit(amps, timeout); 
    liftMaster.configPeakCurrentDuration(milliseconds, timeout);
    liftMaster.configContinuousCurrentLimit(amps, timeout);
    liftMaster.enableCurrentLimit(true);

    liftFollow.configPeakCurrentLimit(amps, timeout);
		liftFollow.configPeakCurrentDuration(milliseconds, timeout);
		liftFollow.configContinuousCurrentLimit(amps, timeout);
    liftFollow.enableCurrentLimit(true);
    
  }

  public void liftUp(double speed) {
    liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
  }

  public void liftDown(double speed) {
    liftMaster.set(ControlMode.PercentOutput, speed * -1.0); 
  }
  
	
	public double getLiftEncoders() {
		
		//String encoderposition = liftMaster.getSensorCollection().toString();
		
    double encoderposition = liftMaster.getSensorCollection().getPulseWidthPosition();
		
		return encoderposition;
	}
  public void liftStop(){
    liftMaster.set(ControlMode.PercentOutput, 0.0);
  }
}
