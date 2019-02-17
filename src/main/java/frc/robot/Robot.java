/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;

import frc.robot.commands.PneumaticsWithCANifier;
import frc.robot.commands.LiftWithJoystick;
import frc.robot.commands.Autonomous.DriveOffPlatform;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.WristWithJoystick;
import frc.robot.subsystems.LiftSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  private Command driveNow;

  private Command PneumaticsWithCANifier;
  private Command drive_off_platform;
  private Command liftNow;
  private Command wristNow;
  private Command HatchGrab;
  private LiftSystem lift;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  DigitalInput limitSwitch;

  SendableChooser<Boolean> arcade_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = OI.getInstance();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    limitSwitch = new DigitalInput(1);
    // chooser.addOption("My Auto", new MyAutoCommand());

    /*
     * arcade_chooser.setDefaultOption("Off", false);
     * arcade_chooser.addOption("Arcade", true);
     * SmartDashboard.putData("Arcade Mode", arcade_chooser);
     * SmartDashboard.putData("Auto mode", m_chooser);
     */
    driveNow = new DriveWithJoystick();
    // driveNow = new DriveToDistance();
    liftNow = new LiftWithJoystick();
    lift = LiftSystem.getInstance();
    HatchGrab = new PneumaticsWithCANifier();
    wristNow = new WristWithJoystick();
    drive_off_platform = new DriveOffPlatform();
    // liftNow = new LiftToHeight(LiftHeight.HighRocket);
    

   
  
   

    // liftNow = new LiftToHeight(LiftHeight.LowRocket);
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(160, 120);
    camera.setFPS(20);
    camera.setPixelFormat(PixelFormat.kMJPEG);
    System.out.println(camera.enumerateVideoModes().toString());

   // getWatchdog().setEnable(true);
     
  }

  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    // drive_off_platform.start();
    // driveNow.start();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    drive_off_platform.start();

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    /*
     * System.out.println("Arcade Chooser Is: "+arcade_chooser.getSelected());
     * ((DriveWithJoystick) driveNow).setArcadeDrive(arcade_chooser.getSelected());
     */

    driveNow.start();
    liftNow.start();
    lift.SetTrueZero();
  
    wristNow.start();
    HatchGrab.start();

    // while (lift.GetWristAngle()>= -90){
    // lift.wristUp(.5);
    // }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
