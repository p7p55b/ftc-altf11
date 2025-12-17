package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@TeleOp(name = "Manual Teleop revision n°0.0.4")
public class main_beta_v0_3 extends LinearOpMode {
  private DcMotor RW;
  private DcMotor LW;
  private DcMotor A112;
  private DcMotor A212;
  
  Boolean cal_1 = false;
  Boolean SSAO = true;
  double sspw = 0;
  double incrtoiftp = 0.005;
  double mtrspd = 0.765;
  

  @Override
  public void runOpMode() {

    LW = hardwareMap.get(DcMotor.class, "dcom0");
    RW = hardwareMap.get(DcMotor.class, "dcom1");
    A112 = hardwareMap.get(DcMotor.class, "dcom2");
    A212 = hardwareMap.get(DcMotor.class, "dcom3");

    waitForStart();
    int sht = 0;
    if (opModeIsActive()) {

      while (opModeIsActive()) {
        while (true) {
          RW.setPower(0);
          LW.setPower(0);
          
          if (SSAO == true) {
            if (cal_1 == true) {
              if (gamepad1.right_bumper) {
                sspw = sspw + incrtoiftp ;
              }
              if (gamepad1.left_bumper) {
                sspw = sspw - incrtoiftp  ;
              }
              A112.setPower(sspw);
            }
            if (cal_1 == false) {
              A112.setPower(mtrspd);
            }
          }
          
          if (gamepad1.left_stick_x < -0.001) { 
            RW.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
            LW.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
          }
          if (gamepad1.left_stick_x > 0.001) {
            RW.setPower(-(gamepad1.left_stick_x * gamepad1.right_trigger));
            LW.setPower(-(gamepad1.right_trigger * gamepad1.left_stick_x));
          }
          if (gamepad1.left_stick_x == 0) {
              if (gamepad1.right_trigger > 0) {
                RW.setPower(-(gamepad1.right_trigger));
                LW.setPower(gamepad1.right_trigger);
            }
            else {
              RW.setPower(gamepad1.left_trigger);
              LW.setPower(-(gamepad1.left_trigger));
            }
          }
          
          if (SSAO == true) {
            if (gamepad1.a) {
              A212.setPower(-1);
            }
            else {
            A212.setPower(0);
            }
          }
          
          if (SSAO == false);
            if (gamepad1.x) {
              break;
            }
            
          telemetry.addData("gloubloublou=", incrtoiftp);
          telemetry.addData("sensibilité moteur", sspw);
          telemetry.addData("key", 123);
          telemetry.update();
        }
        if (SSAO == false) {
          RW.setPower(0);
          LW.setPower(0);
          A112.setPower(0);
          A212.setPower(0);
          while (true) {
            A112.setPower(1);
            if (gamepad1.x) {
              A112.setPower(0);
              break;
            }
          } 
        }
      }
    }
  }
}




