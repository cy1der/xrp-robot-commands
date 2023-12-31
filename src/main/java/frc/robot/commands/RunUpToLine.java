package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LineSensorConstants;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPLineSensor;
import frc.robot.types.LineColor;

public class RunUpToLine extends Command {
    private final XRPDrivetrain m_drivetrain;
    private final XRPLineSensor m_lineSensor;
    private final LineColor m_lineColor;

    public RunUpToLine(XRPDrivetrain drivetrain, XRPLineSensor lineSensor) {
        this(drivetrain, lineSensor, LineColor.black);
    }

    public RunUpToLine(XRPDrivetrain drivetrain, XRPLineSensor lineSensor, LineColor lineColor) {
        m_drivetrain = drivetrain;
        m_lineSensor = lineSensor;
        m_lineColor = lineColor;

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
        switch (m_lineColor) {
            case white:
                return m_lineSensor.getRightValue() <= LineSensorConstants.kThreshold && m_lineSensor.getLeftValue() <= LineSensorConstants.kThreshold;
            case black:
            default:
                return m_lineSensor.getRightValue() >= 5 - LineSensorConstants.kThreshold && m_lineSensor.getLeftValue() >= 5 - LineSensorConstants.kThreshold;
        }
    }
}
