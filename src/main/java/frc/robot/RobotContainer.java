/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.climb.ClimbCommand;
import frc.robot.commands.intake.IntakeMoveCommand;
import frc.robot.commands.intake.IntakeRotateCommand;
import frc.robot.commands.intake.IntakeSolenoidCommand;
import frc.robot.commands.mammoth.MammothPush;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static Joystick Left_DJoystick = new Joystick(1);
  public static Joystick Right_DJoystick = new Joystick(2);
  public static XboxController Controller = new XboxController(3);
  
  public static JoystickButton aButton = new JoystickButton(Controller, 1);
  public static JoystickButton bButton = new JoystickButton(Controller, 2);
  public static JoystickButton cButton = new JoystickButton(Controller, 3);
  public static JoystickButton dButton = new JoystickButton(Controller, 4);
  public static JoystickButton eButton = new JoystickButton(Controller, 5);
  public static JoystickButton fButton = new JoystickButton(Controller, 6);
  public static JoystickButton gButton = new JoystickButton(Controller, 7);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    aButton.whenPressed(new ClimbCommand());
    bButton.whileHeld(new MammothPush());
    
    eButton.whileHeld(new IntakeSolenoidCommand());

    cButton.whileHeld(new IntakeMoveCommand(1));
    dButton.whileHeld(new IntakeRotateCommand(1));
    fButton.whileHeld(new IntakeMoveCommand(-1));
    gButton.whileHeld(new IntakeRotateCommand(-1));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
