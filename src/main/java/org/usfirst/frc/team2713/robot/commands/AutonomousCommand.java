package org.usfirst.frc.team2713.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.usfirst.frc.team2713.robot.RobotMap;

public class AutonomousCommand extends CommandGroup {
  public AutonomousCommand() {
    switch(RobotMap.autonomousMode.getSelected()) {
      default:
      case 0:
      
      
    }
  }
  
  private void autoCalculate() {
    MatchData.OwnedSide side = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
  }
}
