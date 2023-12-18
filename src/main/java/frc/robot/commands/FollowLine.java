package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPLineSensor;
import frc.robot.types.LineColor;
import frc.robot.Constants.LineSensorConstants;

public class FollowLine extends Command {
    private final XRPDrivetrain m_drivetrain;
    private final XRPLineSensor m_lineSensor;
    private final LineColor m_lineColor;

    public FollowLine(XRPDrivetrain drivetrain, XRPLineSensor lineSensor) {
        this(drivetrain, lineSensor, LineColor.black);
    }

    public FollowLine(XRPDrivetrain drivetrain, XRPLineSensor lineSensor, LineColor lineColor) {
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
        boolean moveRight = false;
        boolean moveLeft = false;

        switch (m_lineColor) {
            case white: {
                if (m_lineSensor.getRightValue() <= LineSensorConstants.kThreshold)
                    moveLeft = true;
                if (m_lineSensor.getLeftValue() <= LineSensorConstants.kThreshold)
                    moveRight = true;
            }
            case black:
            default: {
                if (m_lineSensor.getRightValue() >= 5 - LineSensorConstants.kThreshold)
                    moveLeft = true;
                if (m_lineSensor.getLeftValue() >= 5 - LineSensorConstants.kThreshold)
                    moveRight = true;
            }
        }

        double zAxis = 0;

        if (moveLeft)
            zAxis += 0.6;
        if (moveRight)
            zAxis -= 0.6;

        // m_drivetrain.arcadeDrive(0.8, zAxis);
        m_drivetrain.arcadeDrive(0.6, zAxis);
    }

    @Override
    public void end(boolean interrupted) {
        // Stationary
        m_drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        // return false;
        switch (m_lineColor) {
            case white:
                return m_lineSensor.getRightValue() >= 5 - LineSensorConstants.kThreshold && m_lineSensor.getLeftValue() >= 5 - LineSensorConstants.kThreshold;
            case black:
            default:
                return m_lineSensor.getRightValue() <= LineSensorConstants.kThreshold && m_lineSensor.getLeftValue() <= LineSensorConstants.kThreshold;
        }
    }
}
