/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private ElevatorSubsystem elevatorSubsystem;
  private boolean isIdle;
  private double setpoint;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

    public ElevatorCommand() {
    elevatorSubsystem = ElevatorSubsystem.getInstance();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.Controller.getY(Hand.kLeft) > 0.1 || RobotContainer.Controller.getY(Hand.kLeft) < -0.1) {
      elevatorSubsystem.setMotor(RobotContainer.Controller.getY(Hand.kLeft));
      isIdle = false;
    }
    else if (isIdle) {
      elevatorSubsystem.setMotor(elevatorSubsystem.getEncoderPID(setpoint));
    }
    else {
      setpoint = elevatorSubsystem.getEncoder();
      isIdle = true;
    }
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