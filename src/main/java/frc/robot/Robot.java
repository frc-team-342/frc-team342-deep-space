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
import frc.robot.commands.HatchGrab;
import frc.robot.commands.PneumaticsWithCANifier;
import frc.robot.commands.LiftWithJoystick;
import frc.robot.commands.Autonomous.DriveOffPlatform;
import frc.robot.subsystems.BlockPneumatics;
import frc.robot.subsystems.CameraVisionSystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.WristWithJoystick;
import frc.robot.subsystems.LiftSystem;
import frc.robot.subsystems.DriveSystem;
import edu.wpi.cscore.VideoMode.PixelFormat;
import frc.robot.commands.FistIntake;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;







public class Robot extends TimedRobot {
  public static BlockPneumatics blockPneu;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi; 
  //private static CameraVisionSystem cameravisionsystem;
  private Command driveNow;
  private Command PneumaticsWithCANifier;
  private Command drive_off_platform;
  private Command liftNow;
  private Command wristNow;
  private Command Test;
  private Command HatchGrab;
  private Command m_autonomousCommand;
  private LiftSystem lift;
  private DriveSystem drive;
  private Command fistIntake;
  AHRS NavX;

  

  
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  DigitalInput limitSwitch;

 
 

  SendableChooser<Boolean> arcade_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    
    blockPneu = BlockPneumatics.getInstance();
    m_oi = OI.getInstance();
   // cameravisionsystem = CameraVisionSystem.getInstance();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    //limitSwitch = new DigitalInput(1);

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
    drive = DriveSystem.getInstance();
    drive.resetGyro();
   // Command test2 = Test;
    HatchGrab = new HatchGrab();
    wristNow = new WristWithJoystick();
    fistIntake = new FistIntake();
    lift.SetTrueZero();
    NavX = drive.getNavX();
    //NavX = new AHRS(SPI.Port.kMXP);
    //NavX.reset();
  

    // liftNow = new LiftToHeight(LiftHeight.HighRocket);
    // CameraServer.getInstance().startAutomaticCapture();

    // getWatchdog().setEnable(true);
    // }
    //drive_off_platform = new DriveOffPlatform();
  

    // liftNow = new LiftToHeight(LiftHeight.LowRocket);
    //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    //camera.setResolution(160, 120);
    //camera.setFPS(20);
    //camera.setPixelFormat(PixelFormat.kMJPEG);
    //System.out.println(camera.enumerateVideoModes().toString());

  
    //HatchGrab = new PneumaticsWithCANifier();
    //wristNow = new WristWithJoystick();
    //drive_off_platform = new DriveOffPlatform();
    // liftNow = new LiftToHeight(LiftHeight.HighRocket);
    // liftNow = new LiftToHeight(LiftHeight.LowRocket);
    
   //CameraServer.getInstance().startAutomaticCapture().setVideoMode(PixelFormat.kMJPEG, 600, 300, 20);
  



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
    SmartDashboard.putNumber("Roll: ", NavX.getRoll());
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
    lift.resetHoldPosition();
    System.out.println("Starting Commands: ");
    driveNow.start();
    liftNow.start();
    HatchGrab.start();
    wristNow.start();
    fistIntake.start();
    System.out.println("You mad bro?: ");
    /*m_autonomousCommand = m_chooser.getSelected();

   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }*/
    
    drive.resetGyro();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    driveNow.start();
    liftNow.start();
    HatchGrab.start();
    wristNow.start();
    fistIntake.start();
    
    

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
    lift.resetHoldPosition();
    System.out.println("Starting Commands: ");
    driveNow.start();
    liftNow.start();
    HatchGrab.start();
    wristNow.start();
    fistIntake.start();
    System.out.println("You mad bro?: ");
    

    //HatchGrab.start();

    // while (lift.GetWristAngle()>= -90){
    // lift.wristUp(.5);
    // }


  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Roll: ", NavX.getRoll());
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
