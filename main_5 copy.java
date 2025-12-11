package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;

// BETA : tunables motors names from .json, for easy dumass understanding
// part "file reading"
public JSONObject loadConfig() {
    try {
        InputStream is = hardwareMap.appContext.getAssets().open("config.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String json = new String(buffer, "UTF-8");
        return new JSONObject(json);

    }
}
// end of part

@TeleOp(name = "manual_final_beta__not_final_v0.2.9")
public class main_beta_v0_2_9 extends LinearOpMode {
  private DcMotor RW;
  private DcMotor LW;
  private DcMotor A112;
  private DcMotor A212;
  
  @Override
  public void runOpMode() {

    //part 2 
    JSONObject config = loadConfig();
    JSONObject motors = config.getJSONObject("motors");

    // change the name of the var later
    String LW = motors.getString("Left_wheels");
    String RW = motors.getString("Right_wheels");
    String aux_1_12 = motors.getString("Spinning_shit");
    String aux_2_12 = motors.getString("Ball_sender");

    LW = hardwareMap.get(DcMotor.class, "LW");
    RW = hardwareMap.get(DcMotor.class, "RW");
    A112 = hardwareMap.get(DcMotor.class, "aux_1_12");
    A212 = hardwareMap.get(DcMotor.class, "aux_2_12");

    //end of part 2

    waitForStart();
    int sht = 0;
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        while (sht == 0) {
          RW.setPower(0);
          LW.setPower(0);
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
          if (gamepad1.x) {
            sht = 1;
            break;
          }
          telemetry.addData("key", 123);
          telemetry.update();
        }
        RW.setPower(0);
        LW.setPower(0);
        A112.setPower(0);
        A212.setPower(0);
        while (sht == 1) {
          A112.setPower(1);
          if (gamepad1.x) {
            sht = 0;
            A112.setPower(0);
            break;
          }
        }
      }
    }
  }
}




