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
import frc.robot.commands.WristToPosition;
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


import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.Knuckles;;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final OI INSTANCE = new OI();

    private Joystick xbox_drive = new Joystick(0);
    private Joystick logitech_manipulator = new Joystick(1);



    private Command toggleSlowDrive = new ToggleSlowDrive();

    private Command HatchGrab = new HatchGrab();
    private Command FistIntake = new FistIntake();
   

    //private Command HatchGrab = new PneumaticsWithCANifier();
   // private Command togglePneumatics = new TogglePneumatics();
    private Command liftToHeightHigh = new LiftToHeight(LiftHeight.HighRocket);
    private Command liftToHeightMiddle = new LiftToHeight(LiftHeight.MiddleRocket);
    private Command liftToHeightLow = new LiftToHeight(LiftHeight.LowRocket);
    private Command liftToHeightHatchCargoShip = new LiftToHeight(LiftHeight.HatchonCargoShip);

 
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
      //  logitech_manipulator_A.whenPressed(liftToHeightLow);
       // logitech_manipulator_B.whenPressed(liftToHeightMiddle);
      //  logitech_manipulator_Y.whenPressed(liftToHeightHigh);
      //  logitech_manipulator_X.whenPressed(liftToHeightMiddle);
        logitech_manipulator_A.whileHeld(liftToHeightHatchCargoShip);

        logitech_manipulator_leftBumper.whenPressed(toggleSlowDrive);
        logitech_manipulator_rightBumper.whileHeld(HatchRelease);
        logitech_manipulator_leftstickButton.whileHeld(wristToPositionCargo);
        logitech_manipultor_rightstickButton.whileHeld(wristToPositionHatch);

    }

    public static OI getInstance() {
        return INSTANCE;
    }

    // Methods to get the multiple axis on the xbox_drive Joystick
    public double getJoystickDriveLeftYAxis() {

        return xbox_drive.getRawAxis(RobotMap.XBOX_LEFT_Y);
    }

    public double getJoystickDriveLeftXAxis() {

        return xbox_drive.getRawAxis(RobotMap.XBOX_LEFT_X);
    }

    public double getJoystickDriveRightYAxis() {

        return xbox_drive.getRawAxis(RobotMap.XBOX_RIGHT_Y);
    }

    public double getJoystickDriveRightXAxis() {

        return xbox_drive.getRawAxis(RobotMap.XBOX_RIGHT_X);
    }

    public double getJoystickDriveRightTrigger() {

        return xbox_drive.getRawAxis(RobotMap.XBOX_RIGHT_T);
    }

    public double getJoystickManipulatorRightXAxis(){
        return logitech_manipulator.getRawAxis(RobotMap.LOGI_RIGHT_X);
    }

    public double getJoystickManipulatorRightYAxis(){
        return logitech_manipulator.getRawAxis(RobotMap.LOGI_RIGHT_Y);
    }

    public double getJoystickManipulatorLeftYAxis() {

        return logitech_manipulator.getRawAxis(RobotMap.LOGI_LEFT_Y);
    }

    public double getJoystickmanipulatorLeftXAxis() {

        return logitech_manipulator.getRawAxis(RobotMap.LOGI_RIGHT_X);
    }


    public double getJoystickManipulatorLeftTrigger() {

        return logitech_manipulator.getRawAxis(RobotMap.LOGI_LEFT_T);
    }

    public double getJoystickManipulatorRightTrigger() {

        return logitech_manipulator.getRawAxis(RobotMap.LOGI_RIGHT_T);
    }
    
    public double getLogitechPOV() {

        return logitech_manipulator.getPOV();
    }
    public double getCombinedManipulatorTriggers(){
        return Math.pow(logitech_manipulator.getRawAxis(RobotMap.LOGI_LEFT_T) - logitech_manipulator.getRawAxis(RobotMap.LOGI_RIGHT_T),3);
    }
}