
package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * An example command.  You can replace me with your own command.
 */
public class DriveOffPlatform extends CommandGroup {

private DriveToDistance drive_to_distance;
private DriveWithJoystick drive_with_joystick;


  public DriveOffPlatform() {

   drive_to_distance = new DriveToDistance(10);
   //drive_with_joystick = new DriveWithJoystick();

   addSequential(drive_to_distance);
   //addSequential(drive_with_joystick);
 
}
}
