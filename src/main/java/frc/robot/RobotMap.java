/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

  /* Joysticks */
  public static final int XBOX_LEFT_X = 0;
  public static final int XBOX_LEFT_Y = 1;
  public static final int XBOX_LEFT_T = 2;
  public static final int XBOX_RIGHT_X = 4;
  public static final int XBOX_RIGHT_Y = 5;
  public static final int XBOX_RIGHT_T = 3;
  public static final int XBOX_A = 1;
  public static final int XBOX_B = 2;
  public static final int XBOX_X = 3;
  public static final int XBOX_Y = 4;
  public static final int XBOX_RIGHT_B = 5;
  public static final int XBOX_LEFT_B = 6;

  public static final int LOGI_LEFT_X = 0;
  public static final int LOGI_LEFT_Y = 1;
  public static final int LOGI_LEFT_T = 2;
  public static final int LOGI_RIGHT_X = 4;
  public static final int LOGI_RIGHT_Y = 5;
  public static final int LOGI_RIGHT_T = 3;
  public static final int LOGI_A = 1;
  public static final int LOGI_B = 2;
  public static final int LOGI_X = 3;
  public static final int LOGI_Y = 4;
  public static final int LOGI_RIGHT_B = 6;
  public static final int LOGI_LEFT_B = 5;

  /* Can addresses */
  // Motors
  public static final int DRV_LEFT_MASTER = 1;
  public static final int DRV_LEFT_FOLLOW_1 = 2;
  public static final int DRV_LEFT_FOLLOW_2 = 3;
  public static final int DRV_RIGHT_MASTER = 4;
  public static final int DRV_RIGHT_FOLLOW_1 = 5;
  public static final int DRV_RIGHT_FOLLOW_2 = 6;

  public static final int LFT_MASTER = 7;
  public static final int LFT_FOLLOW_1 = 8;
  public static final int LFT_WRIST = 9;

  public static final int INTAKE_T = 10;
  public static final int INTAKE_B = 11;

  public static final int CLIMB = 12;

  public static final int CLIMBDRIVE = 13;

  // Support
  public static final int CAN_PDP = 16;
  public static final int CAN_PCM = 17;
  public static final int CAN_PIMU = 18;
  public static final int CAN_CANI = 19;

  /* Pneumatic outputs */
  public static final int HATCH_GRP_O = 0;
  public static final int HATCH_GRP_C = 1;

  public static final int CLIMB_E = 2;
  public static final int CLIMB_R = 3;

  // LIMIT SWITCHES
  public static final int ELEVATOR_LIMIT_SWITCH_UP = 1;
  public static final int ELEVATOR_LIMIT_SWITCH_DOWN = 0;

}
