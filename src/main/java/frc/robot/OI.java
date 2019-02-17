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
import frc.robot.commands.WristToPosition.WristPosition;
import frc.robot.commands.TogglePneumatics;
import frc.robot.commands.LiftToHeight;


import frc.robot.commands.HatchGrab;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.Knuckles;

import frc.robot.commands.PneumaticsWithCANifier;
import frc.robot.commands.HatchRelease;
import frc.robot.commands.FistIntake;
import frc.robot.commands.FistRelease;

import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.PneumaticClaw;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final OI INSTANCE = new OI();

    private Joystick xbox_drive = new Joystick(0);
    private Joystick logitech_manipulator = new Joystick(1);



    private Command toggleSlowDrive = new ToggleSlowDrive();

    private Command test = new HatchGrab();
    private Command FistIntake = new FistIntake();
    private Command FistRelease = new FistRelease();

    //private Command HatchGrab = new PneumaticsWithCANifier();
   // private Command togglePneumatics = new TogglePneumatics();
    private Command liftToHeightHigh = new LiftToHeight(LiftHeight.HighRocket);
    private Command liftToHeightMiddle = new LiftToHeight(LiftHeight.MiddleRocket);
    private Command liftToHeightLow = new LiftToHeight(LiftHeight.LowRocket);

 
    private Command HatchRelease = new HatchRelease();
 


    private Command wristToPositionCargo = new WristToPosition(WristPosition.Cargo);
    private Command wristToPositionHatch = new WristToPosition(WristPosition.Hatch);
    private Button xbox_drive_leftBumper;
    private Button xbox_drive_rightBumper;

    private Button logitech_manipulator_A;
    private Button logitech_manipulator_B;
    private Button logitech_manipulator_Y;
    private Button logitech_manipulator_X;
    private Button logitech_manipulator_rightBumper;
    private Button logitech_manipulator_leftBumper;
    private Button logitech_manipulator_leftstickButton;
    private Button logitech_manipultor_rightstickButton;


    private OI() {

        toggleFist = new TogglePneumatics();
        xbox_drive = new Joystick(0);
        logitech_manipulator = new Joystick(1);
        



        xbox_drive_leftBumper = new JoystickButton(xbox_drive, 5);
        xbox_drive_rightBumper = new JoystickButton(xbox_drive, 6);
      
        
        logitech_manipulator_leftBumper = new JoystickButton(logitech_manipulator, 5);
        logitech_manipulator_rightBumper = new JoystickButton(logitech_manipulator, 6);
      
        
    
        
        

  
        // setting the manipulator buttons to do what we say
        xbox_drive_leftBumper = new JoystickButton(xbox_drive, 5);
        logitech_manipulator_A = new JoystickButton(logitech_manipulator, 1);
        logitech_manipulator_B = new JoystickButton(logitech_manipulator,2);
        logitech_manipulator_X =new JoystickButton(logitech_manipulator, 3);
        logitech_manipulator_Y = new JoystickButton(logitech_manipulator, 4); 
        logitech_manipulator_leftstickButton = new JoystickButton(logitech_manipulator, 9);
        logitech_manipultor_rightstickButton = new JoystickButton(logitech_manipulator, 10);
        

        xbox_drive_leftBumper.whenPressed(toggleSlowDrive);
        logitech_manipulator_A.whenPressed(liftToHeightLow);
        logitech_manipulator_B.whenPressed(liftToHeightMiddle);
        logitech_manipulator_Y.whenPressed(liftToHeightHigh);
        logitech_manipulator_X.whenPressed(liftToHeightMiddle);

        logitech_manipulator_leftBumper.whenPressed(toggleSlowDrive);
        logitech_manipulator_rightBumper.whenPressed(HatchRelease);
        logitech_manipulator_leftstickButton.whenPressed(wristToPositionCargo);
        logitech_manipultor_rightstickButton.whenPressed(wristToPositionHatch);

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

    public double getJoystickManipulatorRightXAxis(){
        return logitech_manipulator.getRawAxis(RobotMap.LIFT_RIGHT_X_AXIS);
    }

    public double getJoystickManipulatorRightYAxis(){
        return logitech_manipulator.getRawAxis(RobotMap.LIFT_RIGHT_Y_AXIS);
    }

    public double getJoystickManipulatorLeftYAxis() {

        return logitech_manipulator.getRawAxis(RobotMap.WRIST_LEFT_Y_AXIS);
    }

    public double getJoystickmanipulatorLeftXAxis() {

        return logitech_manipulator.getRawAxis(RobotMap.WRIST_LEFT_X_AXIS);
    }


    public double getJoystickManipulatorLeftTrigger() {

        return logitech_manipulator.getRawAxis(RobotMap.LEFT_TRIGGER);
    }

    public double getJoystickManipulatorRightTrigger() {

        return logitech_manipulator.getRawAxis(RobotMap.RIGHT_TRIGGER);
    }
    public double getJoystickmanipulatorLeftTrigger(){
        return logitech_manipulator.getRawAxis(RobotMap.MANIPULATOR_LEFT_TRIGGER);
    }
    public double getJoystickmanipulatorRightTrigger(){
        return logitech_manipulator.getRawAxis(RobotMap.MANIPULATOR_RIGHT_TRIGGER);
    }

    public double getLogitechPOV() {

        return logitech_manipulator.getPOV();
    }
}