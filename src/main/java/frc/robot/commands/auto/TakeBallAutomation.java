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
import frc.robot.subsystems.MammothSubsystem;
import frc.robot.commands.elevator.ElevatorPID;
import frc.robot.commands.intake.IntakePID;
import frc.robot.commands.mammoth.MammothPID;
import frc.robot.commands.mammoth.MammothPull;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TakeBallAutomation extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private CommandBase elevatorPID, intakePID, mammothPID;
  private IntakeSubsystem intakeSubsystem;
  private ElevatorSubsystem elevatorSubsystem;
  private MammothSubsystem mammothSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public TakeBallAutomation(AutomationSubsystem auto) {
    intakeSubsystem = IntakeSubsystem.getInstance();
    elevatorSubsystem = ElevatorSubsystem.getInstance();
    mammothSubsystem = MammothSubsystem.getInstance();

    elevatorPID = new ElevatorPID(0, 2);
    intakePID = new IntakePID(-200, 2);
    mammothPID = new MammothPID(-200, 2);

    addRequirements(auto);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakePID.schedule(false);
    if(intakeSubsystem.getEncoder() >= 15){
      mammothPID.schedule(false);
    }
    if(mammothSubsystem.getEncoder() >= 15){
      elevatorPID.schedule(false);
    }
    if(elevatorSubsystem.getEncoder() >= 15){
      intakeSubsystem.setIntakeMotors(-1);
      new MammothPull();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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