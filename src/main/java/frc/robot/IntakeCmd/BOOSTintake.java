
package frc.robot.IntakeCmd;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.subposintake;

public class BOOSTintake extends Command{

    subposintake posintake;
    double angle;


    public BOOSTintake(subposintake posintake,double angle){
        this.posintake = posintake;
        this.angle = angle;

        addRequirements(posintake);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute(){

        if (posintake.angle() > 105) {
            posintake.position_intake(angle);
            SmartDashboard.putBoolean("Intake Ready", false);
            
        }
        else if (posintake.angle() > 25 && posintake.angle() < 35) {
            posintake.vel(-0.03472);
            SmartDashboard.putBoolean("Intake Ready", false);
        }
        else {
            posintake.vel(0);
            if (posintake.angle() < 25) {
                SmartDashboard.putBoolean("Intake Ready", true);
            }
        }
        

    }

  
    @Override
    public boolean isFinished(){
        return false;
    }
}
