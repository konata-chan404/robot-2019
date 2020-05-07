/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   * 
   */

  private static ElevatorSubsystem elevatorSubsystem;
  
  private WPI_TalonSRX elevatorTalon;
  private Encoder elevatorEncoder;
  private DigitalInput elevatorLimit;
  private PIDController elevatorPID;
  private Timer timer;

  private ElevatorSubsystem() {
    elevatorTalon = new WPI_TalonSRX(Constants.ElevatorTalonPort);
    elevatorEncoder = new Encoder(Constants.ElevatorFowardEncoderPort, Constants.ElevatorReverseEncoderPort, false, EncodingType.k4X);
    elevatorLimit = new DigitalInput(Constants.ElevatorLimitPort);
    elevatorPID = new PIDController(Constants.ElevatorKp, Constants.ElevatorKi, Constants.ElevatorKd);

    elevatorPID.setTolerance(1);
    elevatorTalon.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen);
    elevatorEncoder.setDistancePerPulse(1);

    timer = new Timer();
  }

  public static ElevatorSubsystem getInstance() {
    if (elevatorSubsystem == null) {
      elevatorSubsystem = new ElevatorSubsystem();
    }
    return elevatorSubsystem;
  }

  public void setMotor(double power) {
    elevatorTalon.set(ControlMode.PercentOutput, power);
  }

  public void resetEncoder() {
    elevatorEncoder.reset();
  }

  public double getEncoder() {
    return elevatorEncoder.getDistance();
  }

  public double getEncoderPID(double setpoint) {
    return elevatorPID.calculate(MathUtil.clamp(getEncoder() , -1, 1), setpoint);
  }
  
  public boolean atSetpoint() {
    if (elevatorPID.atSetpoint()) {
      timer.start();

      if (timer.hasElapsed(2)) {
        timer.stop();
        return true;
      }
    }
    return false;
  }

  public boolean getLimitSwitch() {
    return elevatorLimit.get();
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
