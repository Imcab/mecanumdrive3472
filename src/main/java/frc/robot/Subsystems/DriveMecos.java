package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.constants.driveconst;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Rotation2d;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DriveMecos extends SubsystemBase{

    CANSparkMax FrentIzq, FrentDer, AtrasIzq, AtrasDer;

    private int frenteIzquid = driveconst.fi_id;
    private int FrenteDerid = driveconst.fd_id;
    private int AtrasIzqid = driveconst.ai_id;
    private int AtrasDerid = driveconst.ad_id;

    Rotation2d gyroangle;

    private AHRS navX;

    public DriveMecos(){

        FrentIzq = new CANSparkMax(frenteIzquid, MotorType.kBrushless);
        FrentDer = new CANSparkMax(FrenteDerid, MotorType.kBrushless);
        AtrasIzq = new CANSparkMax(AtrasIzqid, MotorType.kBrushless);
        AtrasDer = new CANSparkMax(AtrasDerid, MotorType.kBrushless);

        FrentIzq.setInverted(true);
        AtrasIzq.setInverted(true);
    
        navX = new AHRS(SPI.Port.kMXP);

        navX.setAngleAdjustment(-180);

        //CameraServer.startAutomaticCapture("Camera", 0);
        CameraServer.startAutomaticCapture("Camera2", 0);
    }

    public void resetGyro() {
        navX.reset();
    }

    public Rotation2d anglecampo (){
        return new Rotation2d(navX.getAngle());
    }

    public double angle(){
       return navX.getAngle();
    }

    public void driveMecos(double theta, double power, double turn){
        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        double fIv = power * cos / max + turn;
        double fDv = power * sin / max - turn;
        double aIv = power * sin / max + turn;
        double aDv = power * cos / max - turn;
        
        if ((power + Math.abs(turn)) > 1) {
            fIv /= power + Math.abs(turn);
            fDv /= power + Math.abs(turn);
            aIv /= power + Math.abs(turn);
            aDv /= power + Math.abs(turn);

        }
        FrentIzq.set(fIv);
        FrentDer.set(fDv);
        AtrasIzq.set(aIv);
        AtrasDer.set(aDv);

}

    public void tankauto (double speedder, double speedizq){
        FrentIzq.set(speedizq);
        FrentDer.set(speedder);
        AtrasIzq.set(speedizq);
        AtrasDer.set(speedder);     
    }    

    public void mecanumauto (double speedfi, double speedfd, double speedai,double speedad){
        FrentIzq.set(speedfi);
        FrentDer.set(speedfd);
        AtrasIzq.set(speedai);
        AtrasDer.set(speedad);
    }

    @Override
    public void periodic(){

        SmartDashboard.putNumber("NavX", angle());

    }
  
}
