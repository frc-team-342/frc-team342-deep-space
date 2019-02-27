/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimbSystem extends Subsystem {

  private DoubleSolenoid pneumaticSuspension;
  private static final ClimbSystem INSTANCE = new ClimbSystem();

  private TalonSRX wench;

  private static final int AMPS = 20;
  private static final int TIMEOUT_MS = 1;
  private static final int ZERO = 0;
  private static final double RAMP_TIME = 0.2;

  private OI oi;

  private DriveSystem drive;

  public ClimbSystem() {
    wench = new TalonSRX(RobotMap.CLIMB);
    initializeClimbSystem();
    oi = OI.getInstance();
    drive = DriveSystem.getInstance();
  }

  @Override
  public void initDefaultCommand() {
  }

  public static ClimbSystem getInstance() {
    return INSTANCE;
  }

  public void initializeClimbSystem() {
    pneumaticSuspension = new DoubleSolenoid(RobotMap.CAN_PCM, RobotMap.CLIMB_E, RobotMap.CLIMB_R);

    wench.configPeakCurrentLimit(ZERO, ZERO);
    wench.configPeakCurrentDuration(ZERO, ZERO);
    wench.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
    wench.enableCurrentLimit(true);

    wench.configOpenloopRamp(RAMP_TIME, 0);

    wench.config_kP(0, TIMEOUT_MS);
    wench.config_kI(0, TIMEOUT_MS);
    wench.config_kD(0, TIMEOUT_MS);
    wench.config_kF(0, TIMEOUT_MS);

    wench.set(ControlMode.PercentOutput, 0.0);
  }

  public void hookIn() {
    pneumaticSuspension.set(Value.kReverse);
  }

  public void hookOut() {
    pneumaticSuspension.set(Value.kForward);
  }

  public boolean isOut() {
    if (pneumaticSuspension.get().equals(Value.kForward)) {
      return true;
    } else {
      return false;
    }
  }

  public void extend(double WenchSpeed) {
    double pitch = drive.getTilt();
    wench.set(ControlMode.PercentOutput, WenchSpeed);
    if (pitch > 10.0) {
      wench.set(ControlMode.PercentOutput, WenchSpeed);
    } else if (pitch > -5) {
      wench.set(ControlMode.PercentOutput, WenchSpeed * 0.5);
    } else {
      wench.set(ControlMode.PercentOutput, 0.0);
    }

  }

  public void extendSetSpeed(double Wench_Speed) {

    wench.set(ControlMode.Velocity, Wench_Speed);

  }

  public void stopExtend() {
    wench.set(ControlMode.PercentOutput, 0.0);
  }

  public void stop() {
    wench.set(ControlMode.PercentOutput, 0.0);
  }
}
