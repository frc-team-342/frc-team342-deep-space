/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ToggleSlowDrive;
import frc.robot.commands.WristToPosition;
import frc.robot.commands.XboxRumble;
import frc.robot.commands.ClimbCommands.DriveControl;
import frc.robot.commands.ClimbCommands.LowerWithPnuematics;
import frc.robot.commands.ClimbCommands.RiseWithPnuematics;
import frc.robot.commands.LiftToHeight.LiftHeight;
import frc.robot.commands.LiftToHeightPID.LiftPosition;
import frc.robot.commands.WristToPosition.WristPosition;
import frc.robot.commands.TogglePneumatics;
import frc.robot.commands.LiftToHeight;


import frc.robot.commands.HatchGrab;
import frc.robot.commands.HatchGrabOverride;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.Knuckles;

import frc.robot.commands.PneumaticsWithCANifier;
import frc.robot.commands.RotateToAngle;
import frc.robot.commands.HatchRelease;
import frc.robot.commands.FistIntake;
import frc.robot.commands.LiftToHeightPID;
import frc.robot.commands.LiftToMiddle;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.Knuckles;

import frc.robot.commands.LiftToBottom;
import frc.robot.commands.LiftToTop;
import frc.robot.commands.Launch;
import frc.robot.commands.EngageOveride;


import frc.robot.commands.TurboDrive;;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final OI INSTANCE = new OI();

    private Joystick xbox_drive = new Joystick(0);
    private Joystick logitech_manipulator = new Joystick(1);
    private Joystick angle_pad = new Joystick(2);
    private LiftSystem lift;



    private Command toggleSlowDrive = new ToggleSlowDrive();

    private Command HatchGrab = new HatchGrab();
    private Command hatchGrabOverride = new HatchGrabOverride();
    private Command FistIntake = new FistIntake();
    private Command Launch = new Launch();


    private Command RotateToAngle0 = new RotateToAngle(0);
    private Command RotateToAngle90 = new RotateToAngle(90);
    private Command RotateToAngle180 = new RotateToAngle(180);
    private Command RotateToAngle270 = new RotateToAngle(270);
    private Command RotateToAngle30 = new RotateToAngle(30);
    private Command RotateToAngle150 = new RotateToAngle(150);
    private Command RotateToAngle210 = new RotateToAngle(210);
    private Command RotateToAngle330 = new RotateToAngle(330);

   

    //private Command HatchGrab = new PneumaticsWithCANifier();
   // private Command togglePneumatics = new TogglePneumatics();
  
    private Command xboxRumble = new XboxRumble();


   
private Command liftToHeightPIDLowHatch = new LiftToHeightPID(LiftPosition.HatchLowRocket);
   private Command liftToTop = new LiftToTop();
   private Command liftToMiddle = new LiftToMiddle();
   private Command liftToLow = new LiftToBottom();
   private Command driveControl = new DriveControl();
   private Command engageOveride = new EngageOveride();

 
    private Command HatchRelease = new HatchRelease();
    private Command riseWithPneumatics = new RiseWithPnuematics();
    private Command lowerWithPneumatics = new LowerWithPnuematics();
 


    private Command wristToPositionCargo = new WristToPosition(WristPosition.Cargo);
    private Command wristToPositionHatch = new WristToPosition(WristPosition.Hatch);
    private Command turboDrive = new TurboDrive();
    private Button xbox_drive_leftBumper;
    private Button xbox_drive_rightBumper;
    private Button xbox_drive_B;
    private Button xbox_drive_X;
    private Button xbox_drive_A;
    private Button xbox_drive_Y;

    private Button logitech_manipulator_A;
    private Button logitech_manipulator_B;
    private Button logitech_manipulator_Y;
    private Button logitech_manipulator_X;
    private Button logitech_manipulator_startButton;
    private Button logitech_manipulator_rightBumper;
    private Button logitech_manipulator_leftBumper;
    private Button logitech_manipulator_leftstickButton;
    private Button logitech_manipultor_rightstickButton;
    private Button logitech_manipulator_backButton;

    private Button angle_pad_1;
    private Button angle_pad_2;
    private Button angle_pad_3;
    private Button angle_pad_4;
    private Button angle_pad_5;
    private Button angle_pad_6;
    private Button angle_pad_7;
    private Button angle_pad_11;
    


    private OI() {

        
        xbox_drive = new Joystick(0);
        logitech_manipulator = new Joystick(1);
        angle_pad = new Joystick(2);
        lift = LiftSystem.getInstance();




        xbox_drive_leftBumper = new JoystickButton(xbox_drive, 5);
        xbox_drive_rightBumper = new JoystickButton(xbox_drive, 6);
        xbox_drive_B = new JoystickButton(xbox_drive, 2);
        xbox_drive_Y = new JoystickButton(xbox_drive, 4);
        xbox_drive_A = new JoystickButton(xbox_drive,1);
        xbox_drive_X = new JoystickButton(xbox_drive,3);

        
        
        angle_pad_1 = new JoystickButton(angle_pad, 1); 
        angle_pad_2 = new JoystickButton(angle_pad, 2); 
        angle_pad_3 = new JoystickButton(angle_pad, 3); 
        angle_pad_4 = new JoystickButton(angle_pad, 4); 
        angle_pad_5 = new JoystickButton(angle_pad, 5); 
        angle_pad_6 = new JoystickButton(angle_pad, 6); 
        angle_pad_7 = new JoystickButton(angle_pad, 7); 
        angle_pad_11 = new JoystickButton(angle_pad, 11); 
    
        
        

  
        // setting the manipulator buttons to do what we say
        
        logitech_manipulator_A = new JoystickButton(logitech_manipulator, 1);
        logitech_manipulator_B = new JoystickButton(logitech_manipulator,2);
        logitech_manipulator_X =new JoystickButton(logitech_manipulator, 3);
        logitech_manipulator_Y = new JoystickButton(logitech_manipulator, 4); 
        logitech_manipulator_leftBumper = new JoystickButton(logitech_manipulator, 5);
        logitech_manipulator_rightBumper = new JoystickButton(logitech_manipulator, 6);
        logitech_manipulator_backButton = new JoystickButton(logitech_manipulator, 7);
        logitech_manipulator_startButton = new JoystickButton(logitech_manipulator, 8);
        logitech_manipulator_leftstickButton = new JoystickButton(logitech_manipulator, 9);
        logitech_manipultor_rightstickButton = new JoystickButton(logitech_manipulator, 10);
       
        

        xbox_drive_leftBumper.whileHeld(turboDrive);
        //xbox_drive_B.whileHeld(driveControl);
        //xbox_drive_Y.whileHeld(riseWithPneumatics);
        //xbox_drive_A.whileHeld(lowerWithPneumatics);
        xbox_drive_rightBumper.whileHeld(Launch);
        xbox_drive_A.whileHeld(RotateToAngle180);
        xbox_drive_B.whileHeld(RotateToAngle90);
        xbox_drive_X.whileHeld(RotateToAngle270);
        xbox_drive_Y.whileHeld(RotateToAngle0);



        angle_pad_1.whenPressed(RotateToAngle0);
        angle_pad_2.whenPressed(RotateToAngle30);
        angle_pad_3.whileHeld(RotateToAngle90);
        angle_pad_4.whenPressed(RotateToAngle150);
        angle_pad_5.whenPressed(RotateToAngle180);
        angle_pad_6.whenPressed(RotateToAngle210);
        angle_pad_7.whenPressed(RotateToAngle270);
        angle_pad_11.whenPressed(RotateToAngle330);


      //  logitech_manipulator_A.whenPressed(liftToHeightLow);
       // logitech_manipulator_B.whenPressed(liftToHeightMiddle);
      //  logitech_manipulator_Y.whenPressed(liftToHeightHigh);
      //  logitech_manipulator_X.whenPressed(liftToHeightMiddle);
        //logitech_manipulator_A.whileHeld(liftToHeightHatchCargoShip);
       // logitech_manipulator_B.whileHeld(liftToHeightPIDHatchCargoShip);
      
       

       logitech_manipulator_A.whileHeld(liftToLow);
       logitech_manipulator_B.whileHeld(liftToMiddle);
       logitech_manipulator_Y.whileHeld(liftToTop);
       logitech_manipulator_X.whileHeld(liftToHeightPIDLowHatch);

        logitech_manipulator_leftBumper.whenPressed(toggleSlowDrive);
        logitech_manipulator_rightBumper.whileHeld(HatchRelease);
        logitech_manipulator_startButton.whileHeld(engageOveride);
        logitech_manipulator_backButton.whileHeld(hatchGrabOverride);
      

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

    public void DriveRumble(double rumbleAmount) {
        xbox_drive.setRumble(GenericHID.RumbleType.kRightRumble, rumbleAmount);
    }

	public double getJoystickDriveLeftTrigger() {
		return xbox_drive.getRawAxis(RobotMap.XBOX_LEFT_T);
    }
    
    public int getPOV(){
        return xbox_drive.getPOV();
    }
  /*  public void setDriveRumble(double rumbleAmount){
        xbox_drive.setRumble(GenericHID.RumbleType.kRightRumble, rumbleAmount);
    }

   public void setManipulatorRumble(double rumbleAmount){
        logitech_manipulator.setRumble(GenericHID.RumbleType.kRightRumble, rumbleAmount);
   }
   */



}