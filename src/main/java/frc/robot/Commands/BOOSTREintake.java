package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.subposintake;

public class BOOSTREintake extends Command{

    subposintake posintake;
    double angle;

    public BOOSTREintake(subposintake posintake, double angle){
        this.posintake = posintake;
        this.angle = angle;

        addRequirements(posintake);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){

        if (posintake.angle() < 160) {
           posintake.position_intake(angle);
            System.out.println("a");
        }
        else if (posintake.angle() > 200 && posintake.angle() < 205) {
            posintake.vel(0.03472);
             System.out.println("b");
        }
        else {
           posintake.vel(0);
             System.out.println("c");
        }
        
    }

  
    @Override
    public boolean isFinished(){
        return false;
    }
}