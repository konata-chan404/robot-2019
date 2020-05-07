/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.mammoth;

import frc.robot.RobotContainer;
import frc.robot.subsystems.MammothSubsystem;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class MammothMoveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private MammothSubsystem mammothSubsystem;

  private double setpoint;
  private boolean isIdle;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

    public MammothMoveCommand() {
    mammothSubsystem = MammothSubsystem.getInstance();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mammothSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mammothSubsystem.setIntakeMotor(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.Controller.getY(Hand.kLeft) > 0.1 || RobotContainer.Controller.getY(Hand.kLeft) < -0.1) {
      mammothSubsystem.setMovementMotor(RobotContainer.Controller.getY(Hand.kLeft));
      isIdle = false;
    }
    else if (isIdle) {
      mammothSubsystem.setMovementMotor(mammothSubsystem.getEncoderPID(setpoint));
    }
    else {
      setpoint = mammothSubsystem.getEncoder();
      isIdle = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mammothSubsystem.setIntakeMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
