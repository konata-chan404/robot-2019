/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   * 
   */

  private static DriveSubsystem driveSubsystem;

  private CANSparkMax SM_LeftUp;
  private CANSparkMax SM_LeftDown;
  private CANSparkMax SM_RightUp;
  private CANSparkMax SM_RightDown;

  private DriveSubsystem() {

  SM_LeftUp = new CANSparkMax(Constants.DriveLeftUpPort, MotorType.kBrushless);
  SM_LeftDown = new CANSparkMax(Constants.DriveLeftDownPort, MotorType.kBrushless);
  SM_RightUp = new CANSparkMax(Constants.DriveRightUpPort, MotorType.kBrushless);
  SM_RightDown = new CANSparkMax(Constants.DriveRightDownPort, MotorType.kBrushless);
  
  SM_LeftUp.setInverted(true);
  SM_LeftDown.setInverted(true);

  SM_LeftDown.follow(SM_LeftUp);
  SM_RightDown.follow(SM_RightUp);

  }

  public static DriveSubsystem getInstance() {
    if (driveSubsystem == null) {
      driveSubsystem = new DriveSubsystem();
    }
    return driveSubsystem;
  }

  public void setLeftMotors(double power) {
    SM_LeftUp.set(power);
  }

  public void setRightMotors(double power) {
    SM_RightUp.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
