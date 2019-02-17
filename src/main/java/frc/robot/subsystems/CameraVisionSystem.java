package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraVisionSystem extends Subsystem {

	private static final CameraVisionSystem INSTANCE = new CameraVisionSystem();

	public CameraVisionSystem() {

		initializeCameraVisionSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static CameraVisionSystem getInstance() {

		return INSTANCE;
	}

	private void initializeCameraVisionSystem() {

	}

}