/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.subsystems.AutomationSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TakeBallAutomation extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  IntakeSubsystem intakeSubsystem;
  ElevatorSubsystem elevatorSubsystem;

  AutomationSubsystem automation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public TakeBallAutomation(AutomationSubsystem auto) {
    intakeSubsystem = IntakeSubsystem.getInstance();
    elevatorSubsystem = ElevatorSubsystem.getInstance();

    automation = auto;
    addRequirements(automation);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeSubsystem.setIntakeRotate(1);
  }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      intakeSubsystem.setIntakeRotate(0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }