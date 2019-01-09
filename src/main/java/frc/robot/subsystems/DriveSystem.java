package frc.robot.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {
    
    private Talon Left;
    private Talon Right;

    public DriveSystem() {

        Left = new Talon(1);
        Right = new Talon(2);

    }

    @Override
    public void initDefaultCommand() {

    }

    public void driveJoystick(double left, double right) {
        
        Left.setValue(left);
        Right.setValue(right);
        
    }
}