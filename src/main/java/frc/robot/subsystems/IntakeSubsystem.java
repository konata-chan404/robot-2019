/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private static IntakeSubsystem intakeSubsystem;

  Solenoid intakeSolenoid;
  
  TalonSRX leftTalon;
  TalonSRX rightTalon;

  VictorSPX intakeVictor;


  private  IntakeSubsystem() {
    intakeSolenoid = new Solenoid(1);
    intakeVictor = new VictorSPX(2);

    leftTalon = new TalonSRX(1);
    rightTalon = new TalonSRX(2);
    rightTalon.follow(leftTalon);
  }

  public static IntakeSubsystem getInstance() {
    if (intakeSubsystem == null) {
      intakeSubsystem = new IntakeSubsystem();
    }
    return intakeSubsystem;
  }

  public void setIntakeMotors(double power) {
    rightTalon.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeRotate(double power) {
    intakeVictor.set(ControlMode.PercentOutput, power);
  }

  public void setSolenoid(boolean state) {
    intakeSolenoid.set(state);
  }

  public boolean getSolenoid() {
    return intakeSolenoid.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
