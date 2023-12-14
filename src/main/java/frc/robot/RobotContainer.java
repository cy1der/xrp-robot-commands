// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.RunUpToLine;
import frc.robot.commands.RunUpToWall;
import frc.robot.commands.RunUpToLine.LineColor;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPLineSensor;
import frc.robot.subsystems.XRPUltrasonic;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();
  private final XRPUltrasonic m_xrpUltrasonic = new XRPUltrasonic();
  private final XRPLineSensor m_xrpLineSensor = new XRPLineSensor();

  private final RunUpToWall m_runUpToWall = new RunUpToWall(m_xrpDrivetrain, m_xrpUltrasonic);
  private final RunUpToLine m_runUpToBlackLine = new RunUpToLine(m_xrpDrivetrain, m_xrpLineSensor, LineColor.black);
  private final RunUpToLine m_runUpToWhiteLine = new RunUpToLine(m_xrpDrivetrain, m_xrpLineSensor, LineColor.white);

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_chooser.setDefaultOption("Run up to wall", m_runUpToWall);
    m_chooser.addOption("Run up to black line", m_runUpToBlackLine);    
    m_chooser.addOption("Run up to white line", m_runUpToWhiteLine);    

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
