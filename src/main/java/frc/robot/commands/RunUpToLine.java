package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPLineSensor;

public class RunUpToLine extends Command {
    private final XRPDrivetrain m_drivetrain;
    private final XRPLineSensor m_lineSensor;

    public RunUpToLine(XRPDrivetrain drivetrain, XRPLineSensor lineSensor) {
        m_drivetrain = drivetrain;
        m_lineSensor = lineSensor;

        addRequirements(drivetrain, lineSensor);
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
        return m_lineSensor.getRightValue() >= 4 && m_lineSensor.getLeftValue() >= 4;
    }
}
