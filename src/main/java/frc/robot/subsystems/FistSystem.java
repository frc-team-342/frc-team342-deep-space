/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class FistSystem extends Subsystem {

  // Instance of the class
  private static final FistSystem INSTANCE = new FistSystem();

  // Motor Controllers
  private TalonSRX intakeMaster;
  private TalonSRX intakeFollow;

  // boolean
  

  // Current Variables
  // Peak Duration: how long it stays at its max amps
  // Ramp Time: how long it takes to full power
  private static final int AMPS = 35;
  private static final int TIMEOUT_MS = 1;
  private static final int PEAK_DURATION = 200;
  // amps center ???
  private static final int AMPS_CENTER = 35;
  private static final int ZERO = 0;
  private static final double RAMP_TIME = 0.2;
  

  public FistSystem() {

    // Instantiate Motor Controllers

    intakeMaster = new TalonSRX(RobotMap.INTAKE_T);
    intakeFollow = new TalonSRX(RobotMap.INTAKE_B);


    inititalizeFistSystem();

  }

  @Override
  public void initDefaultCommand() {

  }

  public static FistSystem getInstance() {

    return INSTANCE;
  }

  private void inititalizeFistSystem() {

    intakeFollow.configPeakCurrentLimit(ZERO, ZERO);
    intakeFollow.configPeakCurrentDuration(ZERO, ZERO);
    intakeFollow.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    intakeFollow.enableCurrentLimit(true);

    intakeMaster.configPeakCurrentLimit(ZERO, ZERO);
    intakeMaster.configPeakCurrentDuration(ZERO, ZERO);
    intakeMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    intakeMaster.enableCurrentLimit(true);

    // Configures the open loop ramp to set the motor to ramp up to speed after a
    // specifiec time other tha jerking to full speed.
    intakeMaster.configOpenloopRamp(RAMP_TIME, 0);
    intakeFollow.configOpenloopRamp(RAMP_TIME, 0);

    // Setting the PID loop for the master controllers
    // what is the difference between the k:p,i,d,f- how electrical signals are sent
    // to the robot
    intakeMaster.config_kP(0, TIMEOUT_MS);
    intakeMaster.config_kI(0, TIMEOUT_MS);
    intakeMaster.config_kD(0, TIMEOUT_MS);
    intakeMaster.config_kF(0, TIMEOUT_MS);

    intakeFollow.config_kP(0, TIMEOUT_MS);
    intakeFollow.config_kI(0, TIMEOUT_MS);
    intakeFollow.config_kD(0, TIMEOUT_MS);
    intakeFollow.config_kF(0, TIMEOUT_MS);

    intakeMaster.set(ControlMode.PercentOutput, 0.0);
    intakeFollow.set(ControlMode.PercentOutput, 0.0);
    intakeFollow.follow(intakeMaster);

  }

  public void intake(double SPEED) {
    intakeMaster.set(ControlMode.PercentOutput, SPEED * 0.75);

  }

 

  public void stop() {
    intakeMaster.set(ControlMode.PercentOutput, 0.0);
  }

  public void fistSetSpeed(double Left_Speed, double Right_Speed) {
    // argument is in position change per 100ms
    intakeMaster.set(ControlMode.Velocity, Right_Speed);
  }
  
 
}
