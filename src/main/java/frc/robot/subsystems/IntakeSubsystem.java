/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private static IntakeSubsystem intakeSubsystem;

  private Solenoid intakeSolenoid;

  private TalonSRX aTalon;
  private TalonSRX bTalon;

  private VictorSPX intakeVictor;
  private Encoder intakeEncoder;
  private PIDController intakePID;


  private  IntakeSubsystem() {
    intakeSolenoid = new Solenoid(Constants.IntakeSolenoidPort);
    intakeVictor = new VictorSPX(Constants.IntakeVictorPort);
    intakeEncoder =  new Encoder(Constants.IntakeFowardEncoderPort, Constants.IntakeReverseEncoderPort, false, EncodingType.k4X);
    aTalon = new TalonSRX(Constants.IntakeLeftTalonPort);
    bTalon = new TalonSRX(Constants.IntakeRightTalonPort);

    intakePID = new PIDController(Constants.IntakeKp, Constants.IntakeKi, Constants.IntakeKd);
    intakePID.setTolerance(1);

    aTalon.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    intakeEncoder.setDistancePerPulse(1);
    bTalon.follow(aTalon);

  }

  public static IntakeSubsystem getInstance() {
    if (intakeSubsystem == null) {
      intakeSubsystem = new IntakeSubsystem();
    }
    return intakeSubsystem;
  }

  public void setIntakeMotors(double power) {
    bTalon.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeRotate(double power) {
    intakeVictor.set(ControlMode.PercentOutput, power);
  }

  public void resetEncoder() {
    intakeEncoder.reset();;
  }

  public double getEncoder() {
    return intakeEncoder.getDistance();
  }

  public double getEncoderPID(double setpoint) {
    return intakePID.calculate(MathUtil.clamp(getEncoder(), -1, 1));
  }

  public boolean atSetpoint() {
    return intakePID.atSetpoint();
  }

  public boolean getLimitSwitch() {
    return aTalon.getSensorCollection().isFwdLimitSwitchClosed();
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
    if (getLimitSwitch()) {
      resetEncoder();
    }

    SmartDashboard.putNumber("Intake encoder value:", getEncoder());
    SmartDashboard.putBoolean("Intake solenoid value:", getSolenoid());
    SmartDashboard.putBoolean("Intake limit switch value:", getLimitSwitch());
  }
}
