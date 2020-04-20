/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  
  public static ClimbSubsystem climbSubsystem;
  
  private DoubleSolenoid climbSolenoid;

  private ClimbSubsystem() {
    climbSolenoid = new DoubleSolenoid(1, 2);
  }

  public static ClimbSubsystem getInstance() {
    if (climbSubsystem == null) {
      climbSubsystem = new ClimbSubsystem();
    }
    return climbSubsystem;
  }

  public void setFoward() {
    climbSolenoid.set(Value.kForward);
  }
  public void setReverse() {
    climbSolenoid.set(Value.kReverse);
  }
  public boolean getSolenoid() {
    return climbSolenoid.get() == Value.kForward;
  }
}
