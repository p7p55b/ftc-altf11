package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;

@TeleOp(name = "manual_final_v0.1.7")
public class main_5 extends LinearOpMode {
  private DcMotor dcom0;
  private DcMotor dcom1;
  private DcMotor dcom2;
  private DcMotor dcom3;
  @Override
  public void runOpMode() {
    dcom0 = hardwareMap.get(DcMotor.class, "dcom0");
    dcom1 = hardwareMap.get(DcMotor.class, "dcom1");
    dcom2 = hardwareMap.get(DcMotor.class, "dcom2");
    dcom3 = hardwareMap.get(DcMotor.class, "dcom3");
    waitForStart();
    int sht = 0;
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        while (sht == 0) {
          dcom0.setPower(0);
          dcom1.setPower(0);
          if (gamepad1.left_stick_x < -0.001) { 
            dcom0.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
            dcom1.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
          }
          if (gamepad1.left_stick_x > 0.001) {
            dcom0.setPower(-(gamepad1.left_stick_x * gamepad1.right_trigger));
            dcom1.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
          }
          if (gamepad1.left_stick_x == 0) {
              if (gamepad1.right_trigger > 0) {
                dcom0.setPower(-(gamepad1.right_trigger));
                dcom1.setPower(gamepad1.right_trigger);
            }
            else {
              dcom0.setPower(gamepad1.left_trigger);
              dcom1.setPower(-(gamepad1.left_trigger));
            }
          }
          if (gamepad1.x) {
            sht = 1;
            break;
          }
          telemetry.addData("key", 123);
          telemetry.update();
        }
        dcom0.setPower(0);
        dcom1.setPower(0);
        dcom2.setPower(0);
        dcom3.setPower(0);
        while (sht == 1) {
          dcom2.setPower(1);
          if (gamepad1.x) {
            sht = 0;
            dcom2.setPower(0);
            break;
          }
        }
      }
    }
  }
}




