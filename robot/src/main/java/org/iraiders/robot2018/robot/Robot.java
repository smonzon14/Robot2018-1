
package org.iraiders.robot2018.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lombok.Getter;
import org.iraiders.robot2018.robot.commands.auto.AutonomousCommand;
import org.iraiders.robot2018.robot.subsystems.ArmSubsystem;
import org.iraiders.robot2018.robot.subsystems.DriveSubsystem;
import org.iraiders.robot2018.robot.subsystems.GrabberSubsystem;
import org.iraiders.robot2018.robot.subsystems.WinchSubsystem;

public class Robot extends IterativeRobot {
  @Getter private static Robot robotInstance;
  @Getter private static OI oi;
  public static Preferences prefs = Preferences.getInstance();
  
  @Getter private static DriveSubsystem driveSubsystem;
  @Getter private static ArmSubsystem armSubsystem;
  @Getter private static WinchSubsystem winchSubsystem;
  @Getter private static GrabberSubsystem grabberSubsystem;
  
  private AutonomousCommand autonomousCommand;
	
  private long autoStart = 0;
 
  @Override
  public void robotInit() {
	  DriverStation.reportWarning("System coming alive, captain!", false);
	  robotInstance = this;
	  oi = new OI();
	
	  initDash();
	  //initCamera();
	  initSubsystems();
  }
	
	/**
   * Initialize all subsystems here, in order of importance
   */
  private void initSubsystems() {
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    winchSubsystem = new WinchSubsystem();
    grabberSubsystem = new GrabberSubsystem();
  }
  
  /**
   * Automatically find all cameras attached to the RoboRIO
   * and start streaming video
   */
  private void initCamera() {
    CameraServer cs = CameraServer.getInstance();
    cs.startAutomaticCapture();
  }
  
  /**
   * A place to get and set values from SmartDash
   */
  private void initDash() {
    // We need to set the name for every chooser or SmartDash won't pick it up
    RobotMap.startPosition.setName("Auto Position");
    RobotMap.startPosition.addDefault("Guess", AutonomousCommand.MatchStartPosition.GUESS);
    RobotMap.startPosition.addObject("Left", AutonomousCommand.MatchStartPosition.LEFT);
    RobotMap.startPosition.addObject("Middle", AutonomousCommand.MatchStartPosition.MIDDLE);
    RobotMap.startPosition.addObject("Right", AutonomousCommand.MatchStartPosition.RIGHT);
    
    SmartDashboard.putData(RobotMap.startPosition);
  }
  
  @Override
  public void disabledInit() {
    Scheduler.getInstance().removeAll();
    
    OI.rumbleController(OI.getXBoxController(), 0, 0, GenericHID.RumbleType.kRightRumble);
    OI.rumbleController(OI.getXBoxController(), 0, 0, GenericHID.RumbleType.kLeftRumble);
  }
  
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void autonomousInit() {
    autoStart = System.currentTimeMillis();
    if (!RobotMap.USE_MINIMUM_VIABLE_AUTO && !prefs.getBoolean("DisableAutonomus", false)) {
      autonomousCommand = new AutonomousCommand(driveSubsystem, armSubsystem, grabberSubsystem);
      autonomousCommand.start();
    }
  }
  
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    
    if (RobotMap.USE_MINIMUM_VIABLE_AUTO && !prefs.getBoolean("DisableAutonomus", false)) {
      double speed = .6, timeout = 5;
      if ((System.currentTimeMillis() - autoStart) < (timeout * 1000)) driveSubsystem.setDriveSpeed(speed);
    }
  }

  @Override
  public void teleopInit() {
	  if (autonomousCommand != null) autonomousCommand.cancel();
   
	  driveSubsystem.startTeleop();
	  armSubsystem.startTeleop();
	  grabberSubsystem.startTeleop();
  }
 
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  
  }
  
  public static double normalize(double value, int min, int max) {
    return (value - min) / (max - min);
  }
}
