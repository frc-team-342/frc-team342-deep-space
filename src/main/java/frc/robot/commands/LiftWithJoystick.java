/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.LiftSystem;
import frc.robot.OI;
import edu.wpi.first.wpilibj.DigitalInput;


public class LiftWithJoystick extends Command {

  private LiftSystem lift;
  private OI oi;

  private static final double DEADZONE = 0.1;
  private static final double ZERO = 0.0;

  private double RightJoystickValue;

  DigitalInput limitSwitch1;
  DigitalInput limitSwitch2;

  public LiftWithJoystick() {
    System.out.println("In Lift With Joystick Constructor");
    limitSwitch1 =  new DigitalInput(RobotMap.ELEVATOR_LIMIT_SWITCH_UP);
    limitSwitch2 =  new DigitalInput(RobotMap.ELEVATOR_LIMIT_SWITCH_DOWN);
    oi = OI.getInstance();
    lift = LiftSystem.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //TODO make piston activated by a button

   
   RightJoystickValue = oi.getJoystickManipulatorRightYAxis() * -1.0;


        

      if (RightJoystickValue > DEADZONE ){
      if (limitSwitch1.get()){
        lift.liftUp(Math.abs(RightJoystickValue)*.3);
        SmartDashboard.putNumber("encoder", lift.getLiftEncoders());
        // System.out.println("encoder: " + lift.getLiftEncoders());
      } else  {
        lift.liftStop();
      }
    } else if (RightJoystickValue < DEADZONE ){
      if(limitSwitch2.get()){
      lift.liftDown(Math.abs(RightJoystickValue)*.3);
      SmartDashboard.putNumber("encoder", lift.getLiftEncoders());
      //System.out.println("encoder: " + lift.getLiftEncoders());
    } else {
      lift.liftStop(); 
    }
  }
    
    /*if(triggerValue < (DEADZONE * -1.0)){
      lift.liftUp(Math.abs(triggerValue));
    } else if (triggerValue > DEADZONE) {
      lift.liftDown(Math.abs(triggerValue)); 
    } else if (triggerValue < DEADZONE && triggerValue > (DEADZONE * -1.0)) {
      lift.liftUp(ZERO);
    } else {
      lift.liftUp(ZERO);
    }

    if(leftTriggerValue && rightTriggerValue < DEADZONE){
      lift.liftDown(Z)
    }
    System.out.println(leftTriggerValue); */
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    lift.liftStop();
  }

  @Override
  protected void interrupted() {
    end(); 
  }
}
