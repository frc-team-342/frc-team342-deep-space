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
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // JoystickAxis
  public static final int X_LEFT_AXIS = 0; 
  public static final int Y_LEFT_AXIS = 1;
  public static final int X_RIGHT_AXIS = 4;
  public static final int X_LEFT_AXIS = 5;

  /* 
  //this is stuff for logitech that we might not use this year
  public static final int X_AXIS_LOGITECH = 0;
	public static final int Y_AXIS_LOGITECH = 1;
	public static final int Z_AXIS_LOGITECH = 2;
  public static final int SLIDER_AXIS_LOGITECH = 3;
  
  //Logitech Axis
	public static final int X_AXIS = 0;
  public static final int Y_AXIS = 1;
  
  //basic pneumatic stuff
  public static final int PNEUMATICCLAW_OPEN = 2;
  public static final int PNEUMATICCLAW_CLOSED = 3; 
  public static final int PNEUMATICCLAW_RELEASE_OPEN = 4;
  */ 


}
