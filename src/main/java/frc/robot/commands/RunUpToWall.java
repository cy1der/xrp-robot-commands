package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RunUpToWallCommandConstants;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPUltrasonic;

public class RunUpToWall extends Command {
    private final XRPDrivetrain m_drivetrain;
    private final XRPUltrasonic m_ultrasonic;

    public RunUpToWall(XRPDrivetrain drivetrain, XRPUltrasonic ultrasonic) {
        m_drivetrain = drivetrain;
        m_ultrasonic = ultrasonic;

        addRequirements(drivetrain, ultrasonic);
    }

    @Override
    public void initialize() {
        // Stationary
        m_drivetrain.arcadeDrive(0, 0);
        m_drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        // Move forward
        m_drivetrain.arcadeDrive(0.8, 0);
    }

    @Override
    public void end(boolean interrupted) {
        // Stationary
        m_drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return m_ultrasonic.getDistance() <= RunUpToWallCommandConstants.kWallDistanceThreshold;
    }
}
