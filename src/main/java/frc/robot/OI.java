/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final OI INSTANCE = new OI();

    private Joystick xbox_drive = new Joystick(0);

    private OI() {

        xbox_drive = new Joystick(0);
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    // Methods to get the multiple axis on the xbox_drive Joystick
    public double getJoystickDriveLeftYAxis() {

        return xbox_drive.getRawAxis(RobotMap.LEFT_Y_AXIS);
    }

    public double getJoystickDriveLeftXAxis() {

        return xbox_drive.getRawAxis(RobotMap.LEFT_X_AXIS);
    }

    public double getJoystickDriveRightYAxis() {

        return xbox_drive.getRawAxis(RobotMap.RIGHT_Y_AXIS);
    }

    public double getJoystickDriveRightXAxis() {

        return xbox_drive.getRawAxis(RobotMap.RIGHT_X_AXIS);
    }
    
    public double getJoystickDriveLeftTrigger(){
        return xbox_drive.getRawAxis(RobotMap.LEFT_TRIGGER);
    }
    public double getJoystickDriveRightTrigger(){
        return xbox_drive.getRawAxis(RobotMap.RIGHT_TRIGGER);
    }
}