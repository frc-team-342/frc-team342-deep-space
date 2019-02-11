/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ToggleSlowDrive;
import frc.robot.commands.LiftToHeight.LiftHeight;
import frc.robot.commands.TogglePneumatics;
import frc.robot.subsystems.PneumaticClaw;
import frc.robot.commands.LiftToHeight;
import frc.robot.subsystems.LiftSystem;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final OI INSTANCE = new OI();

    private Joystick xbox_drive = new Joystick(0);
    private Joystick logitech_manipulator = new Joystick(1);
    private Command toggleSlowDrive = new ToggleSlowDrive();
    private Button xbox_drive_leftBumper;
    private Command togglePneumatics = new TogglePneumatics();
    private Command liftToHeightHigh = new LiftToHeight(LiftHeight.HighRocket);
    private Command liftToHeightMiddle = new LiftToHeight(LiftHeight.MiddleRocket);
    private Command liftToHeightLow = new LiftToHeight(LiftHeight.LowRocket);
    private Button xbox_drive_rightBumper;
    private Button logitech_manipulator_A;
    private Button logitech_manipulator_B;
    private Button logitech_manipulator_Y;
    private Button logitech_manipulator_X;

    private OI() {

        xbox_drive = new Joystick(0);
        logitech_manipulator = new Joystick(1);
        xbox_drive_leftBumper = new JoystickButton(xbox_drive, 5);
        xbox_drive_leftBumper.whenPressed(toggleSlowDrive);
        logitech_manipulator_A = new JoystickButton(logitech_manipulator, 1);
        logitech_manipulator_B = new JoystickButton(logitech_manipulator,2);
        logitech_manipulator_X =new JoystickButton(logitech_manipulator, 3);
        logitech_manipulator_Y = new JoystickButton(logitech_manipulator, 4);
        logitech_manipulator_A.whenPressed(liftToHeightLow);
        logitech_manipulator_B.whenPressed(liftToHeightMiddle);
        logitech_manipulator_Y.whenPressed(liftToHeightHigh);
        logitech_manipulator_X.whenPressed(liftToHeightMiddle);
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

    public double getJoystickDriveLeftTrigger() {

        return xbox_drive.getRawAxis(RobotMap.LEFT_TRIGGER);
    }

    public double getJoystickDriveRightTrigger() {

        return xbox_drive.getRawAxis(RobotMap.RIGHT_TRIGGER);
    }
}