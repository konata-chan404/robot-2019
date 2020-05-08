/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.mammoth;

import frc.robot.subsystems.MammothSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * An example command that uses an example subsystem.
 */
public class MammothPID extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private MammothSubsystem mammothSubsystem;
  private double lastTimeOnTarget;
  private double waitTime;
  private double setPoint;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public MammothPID(double setpoint, double waittime) {
    mammothSubsystem = MammothSubsystem.getInstance();
    setPoint = setpoint;
    waitTime = waittime;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mammothSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      mammothSubsystem.setMovementMotor(mammothSubsystem.getEncoderPID(setPoint));
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      mammothSubsystem.setMovementMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!mammothSubsystem.atSetpoint()){
      lastTimeOnTarget = Timer.getFPGATimestamp();
    }
    return mammothSubsystem.atSetpoint() && Timer.getFPGATimestamp() - lastTimeOnTarget > waitTime;
  }
}