package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPUltrasonic extends SubsystemBase {
    private final AnalogPotentiometer m_ultrasonic;

    public XRPUltrasonic() {
        // Ultrasonic sensor is exposed as an analog input where 0 volts corresponds to 20 mm and 5 volts corresponds to 4000 mm
        m_ultrasonic = new AnalogPotentiometer(2, 4000, 20);
    }

    @Override
    public void periodic() {}

    public double getDistance() {
        return m_ultrasonic.get();
    }
}
