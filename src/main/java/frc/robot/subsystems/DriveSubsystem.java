/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   * 
   */

  private static DriveSubsystem DriveSubsystem;

  private CANSparkMax SM_LeftUp;
  private CANSparkMax SM_LeftDown;
  private CANSparkMax SM_RightUp;
  private CANSparkMax SM_RightDown;

  private DriveSubsystem() {

  SM_LeftUp = new CANSparkMax(1, MotorType.kBrushless);
  SM_LeftDown = new CANSparkMax(2, MotorType.kBrushless);
  SM_RightUp = new CANSparkMax(3, MotorType.kBrushless);
  SM_RightDown = new CANSparkMax(4, MotorType.kBrushless);
  
  SM_LeftUp.setInverted(true);
  SM_LeftDown.setInverted(true);

  SM_LeftDown.follow(SM_LeftUp);
  SM_RightDown.follow(SM_RightUp);

  }

  public static DriveSubsystem getInstance() {
    if (DriveSubsystem == null) {
      DriveSubsystem = new DriveSubsystem();
    }
    return DriveSubsystem;
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

    SmartDashboard.putNumber("left power:", SM_LeftUp.get());
    SmartDashboard.putNumber("right power:", SM_RightUp.get());
  }
}
