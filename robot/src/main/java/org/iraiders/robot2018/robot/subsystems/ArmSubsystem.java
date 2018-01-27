package org.iraiders.robot2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import lombok.Getter;
import org.iraiders.robot2018.robot.OI;
import org.iraiders.robot2018.robot.RobotMap;
import org.iraiders.robot2018.robot.commands.arm.ArmCommand;


public class ArmSubsystem extends Subsystem {
  @Getter private WPI_TalonSRX lowerJoint = new WPI_TalonSRX(RobotMap.lowerJointTalonPort);
  @Getter private WPI_TalonSRX upperJoint = new WPI_TalonSRX(RobotMap.upperJointTalonPort);
  @Getter private Potentiometer lowerPot = new AnalogPotentiometer(RobotMap.lowerJointTalonPort, 360, 30);
  @Getter private Potentiometer upperPot = new AnalogPotentiometer(RobotMap.upperJointTalonPort, 360, 30);
  
  public void startTeleop() {
    Joystick arcade = OI.getArcadeController();
    JoystickButton condense = new JoystickButton(arcade, 8);
    JoystickButton reachBlock = new JoystickButton(arcade, 4);
    JoystickButton reachUp = new JoystickButton(arcade, 7);
    
    condense.whenPressed(new ArmCommand(this, 1));
    reachBlock.whenPressed(new ArmCommand(this, 2));
    reachUp.whenPressed(new ArmCommand(this, 3));
  }
  
  @Override
  protected void initDefaultCommand() {
    
  }
}