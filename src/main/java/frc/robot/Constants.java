/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int DoubleSolenoidFoward = 1;
    public static int DoubleSolenoidReverse = 2;

    public static int DriveLeftUpPort = 3;
    public static int DriveLeftDownPort = 4;
    public static int DriveRightUpPort = 5;
    public static int DriveRightDownPort = 6;

    public static int ElevatorTalonPort = 7;
    public static int ElevatorFowardEncoderPort = 14;
    public static int ElevatorReverseEncoderPort = 15;
    public static int ElevatorLimitPort = 16;

    public static int IntakeLeftTalonPort = 8;
    public static int IntakeRightTalonPort = 9;
    public static int IntakeVictorPort = 10;
    public static int IntakeSolenoidPort = 11;
    public static int IntakeFowardEncoderPort = 16;
    public static int IntakeReverseEncoderPort = 17;
    public static int IntakeLimitPort = 18;

    public static int MammothTalonPort = 12;
    public static int MammothVictorPort = 13;
    public static int MammothFowardEncoderPort = 19;
    public static int MammothReverseEncoderPort = 20;
    public static int MammothLimitPort = 21;

    public static double ElevatorKp = 1;
    public static double ElevatorKi = 1;
    public static double ElevatorKd = 1;

    public static double IntakeKp = 1;
    public static double IntakeKi = 1;
    public static double IntakeKd = 1;
    
    public static double MammothKp = 1;
    public static double MammothKi = 1;
    public static double MammothKd = 1;

    public static int LeftJoystickPort = 1;
    public static int RightJoystickPort = 2;
    public static int ControllerPort = 3;

    public static int AButton = 1;
    public static int BButton = 2;
    public static int Xbutton = 3;
    public static int YButton = 4;
    public static int L1Button = 5;
    public static int R1Button = 6;
    public static int LeftMiddleButton = 7;
    public static int RightMiddleButton = 8;
}
