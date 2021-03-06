package org.iraiders.robot2018.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.iraiders.robot2018.robot.commands.auto.PathfindingAuto;

import javax.measure.Measure;
import javax.measure.quantity.Length;
import javax.measure.unit.NonSI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final boolean DEBUG = true;
  
  // Motors
  public static final int frontLeftTalonPort = 1;
  public static final int frontRightTalonPort = 2;
  public static final int backLeftTalonPort = 3;
  public static final int backRightTalonPort = 4;
  public static final int lowerJointTalonPort = 5; // Shoulder
  public static final int upperJointTalonPort = 6; // Elbow
  public static final int winchTalonPort = 8;
  public static final int grabberTalonPort = 9;
  
  // Pneumatics
  public static final int grabberOpenNodeId = 0;
  public static final int grabberCloseNodeId = 1;
	
  // Controllers
  public static final int BACKUP_XBOX_PORT = 0;
  public static final int BACKUP_ARCADE_PORT = 1;
  public static final int ATTACK_LEFT_PORT = 2;
  public static final int ATTACK_RIGHT_PORT = 3;
  public static final String XBOX_NAME = "Controller (XBOX 360 For Windows)";
  public static final String ARCADE_NAME = "Mayflash Arcade Stick";
  public static final String ATTACK_NAME = "Logitech Attack 3";
  
  // Globally useful Sensors
  public static final ADIS16448_IMU imu = new ADIS16448_IMU();
  public static final Ultrasonic frontUltrasonic = new Ultrasonic(9, 8);
  
  // SmartDash Settings
  public static SendableChooser<PathfindingAuto.MatchStartPosition> startPosition = new SendableChooser<>();
  public static SendableChooser<Robot.AutoModes> whichAuto = new SendableChooser<>();
  
  // Measurements
  public static final Measure<Double, Length> WHEEL_DIAMETER = Measure.valueOf(6d, NonSI.INCH);
  public static final int TICKS_PER_REVOLUTION = 1440;
  
  // Misc
  /**
   * If all else fails, set this to true to enable Minimum Viable Autonomous, which literally only goes forward for a bit
   * For if our sensors are gone, the code is broke, or the robot is on fire
   */
  public static boolean USE_MINIMUM_VIABLE_AUTO = false;
  
}
