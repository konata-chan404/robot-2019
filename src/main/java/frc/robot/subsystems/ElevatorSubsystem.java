/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
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


  private ElevatorSubsystem() {
    elevatorTalon = new WPI_TalonSRX(Constants.ElevatorTalonPort);
    elevatorEncoder = new Encoder(Constants.ElevatorFowardEncoderPort, Constants.ElevatorReverseEncoderPort, false, EncodingType.k4X);
    elevatorLimit = new DigitalInput(Constants.elevatorLimitPort);
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
