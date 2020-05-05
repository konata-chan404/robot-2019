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
public class MammothPID extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private MammothSubsystem mammothSubsystem;
    private double setpoint;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public MammothPID() {
    mammothSubsystem = MammothSubsystem.getInstance();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mammothSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setpoint = mammothSubsystem.getEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mammothSubsystem.setMovementMotor(mammothSubsystem.getEncoderPID(setpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}