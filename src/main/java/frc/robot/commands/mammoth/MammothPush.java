/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.mammoth;

import frc.robot.subsystems.MammothSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class MammothPush extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  MammothSubsystem mammothSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public MammothPush() {
    mammothSubsystem = MammothSubsystem.getInstance();
    addRequirements(mammothSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mammothSubsystem.setIntakeMotor(1);
  }
  
  @Override
  public void end(boolean interrupted) {
    mammothSubsystem.setIntakeMotor(0);
  }
}