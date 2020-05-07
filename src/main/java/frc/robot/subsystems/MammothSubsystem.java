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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class MammothSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private static MammothSubsystem mammothSubsystem;

  private WPI_TalonSRX mammothTalon;
  private WPI_VictorSPX mammothVictor;
  private Encoder mammothEncoder;
  private PIDController mammothPID;

  private MammothSubsystem() {
    mammothTalon = new WPI_TalonSRX(Constants.MammothTalonPort); 
    mammothVictor = new WPI_VictorSPX(Constants.MammothVictorPort);
    mammothEncoder = new Encoder(Constants.MammothFowardEncoderPort, Constants.MammothReverseEncoderPort, false, EncodingType.k4X);

    mammothPID = new PIDController(Constants.MammothKp , Constants.MammothKi, Constants.MammothKd);
    mammothPID.setTolerance(1);

    mammothTalon.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    mammothEncoder.setDistancePerPulse(1);
  }

  public static MammothSubsystem getInstance() {
    if (mammothSubsystem == null) {
      mammothSubsystem = new MammothSubsystem();
    }
    return mammothSubsystem;
  }

  public void setMovementMotor(double power) {
    mammothTalon.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeMotor(double power) {
    mammothVictor.set(ControlMode.PercentOutput, power);
  }

  public double getEncoderPID(double setpoint) {
      return mammothPID.calculate(MathUtil.clamp( getEncoder(), -1, 1), setpoint);
  }

  public double getEncoder() { 
    return mammothEncoder.getDistance();
  }

  public boolean getLimitSwitch() {
    return mammothTalon.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public void resetEncoder() {
    mammothEncoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (getLimitSwitch()) {
      resetEncoder();
    }
    
    SmartDashboard.putNumber("Mammoth encoder value:", getEncoder());
    SmartDashboard.putBoolean("Mammoth limit switch value:", getLimitSwitch());
  }
}
