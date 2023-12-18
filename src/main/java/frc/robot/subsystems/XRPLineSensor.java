package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPLineSensor extends SubsystemBase {
    private final AnalogInput m_lineRight;
    private final AnalogInput m_lineLeft;

    public XRPLineSensor() {
        m_lineRight = new AnalogInput(1);
        m_lineLeft = new AnalogInput(0);
    }

    @Override
    public void periodic() {}

    /**
     * Get voltage value of the right sensor
     *
     * @return Range from 0 to 5, 0 is pure white, 5 is pure black
     */
    public double getRightValue() {
        return m_lineRight.getVoltage();
    }

    /**
     * Get voltage value of the left sensor
     *
     * @return Range from 0 to 5, 0 is pure white, 5 is pure black
     */
    public double getLeftValue() {
        return m_lineLeft.getVoltage();
    }
}
