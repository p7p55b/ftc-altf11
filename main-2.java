package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;


@TeleOp(name = "manual_final_v0.0.5")
public class main-2 extends LinearOpMode {

// setup les noms des moteurs
  private DcMotor dcom0;
  private DcMotor dcom1;
  private DcMotor dcom2;
// to do : setup le sens de rotation par défaut

  @Override
  public void runOpMode() {

//map les ports des moteurs
    dcom0 = hardwareMap.get(DcMotor.class, "dcom0");
    dcom1 = hardwareMap.get(DcMotor.class, "dcom1");
    dcom2 = hardwareMap.get(DcMotor.class, "dcom2");

// attend l'initialisation du code (je pense)
    waitForStart();

// initialise la variable triangle
    int sht = 0;

//attend l'activation de l'op mode
    if (opModeIsActive()) {
      while (opModeIsActive()) {

// verifie qu'on se soit pas en mode triangle
        while (sht == 0) {

// reset la vitesse des moteurs a 0
          dcom0.setPower(0);
          dcom1.setPower(0);

// si le stick est a gauche, a met le moteur droit à avancer, et le gauche a reculer
	// il met la vitesse proportionellement a l'inclinaison du stick, et la presssion sur le trigger
          if (gamepad1.left_stick_x < -0.001) {
            dcom0.setPower(gamepad1.right_trigger * gamepad1.left_stick_x);
            dcom1.setPower(-1 * gamepad1.right_trigger * gamepad1.left_stick_x);
          }

// si le stick est a droite; met le moteur droit à reculer, et le gauche a avancer
          if (gamepad1.left_stick_x > 0.001) {
            dcom0.setPower(gamepad1.left_stick_x * gamepad1.right_trigger);
            dcom1.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
          }

// si le stick est neutre;

          if (gamepad1.left_stick_x == 0) {

// si le trigger gauche est presser, il brake
	     if (gamepad1.left_trigger > 0) {
                dcom0.setPower(-(gamepad1.left_trigger));
           	dcom1.setPower(-(gamepad1.left_trigger));

// si le stick est neutre; avance
	     } else {
		dcom0.setPower(gamepad1.right_trigger);
		dcom1.setPower(gamepad1.right_trigger);
             }

          if (gamepad1.y) {
              sht = 1;
              break;
            }

          telemetry.addData("key", 123);
          telemetry.update();
          }

          if (gamepad1.x) {
            dcom2.setPower(1);
          } else {
          dcom2.setPower(0);
          }
          if (gamepad1.y) {
            sht = 0;
          }
      }
    }
  }
}
