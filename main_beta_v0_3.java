package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

// BETA : why shoud i comment if i'm the only one to read that shit ???

public class ReadConfig {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);
            boolean SSAO = Boolean.parseBoolean(props.getProperty("Ball_launcher_always_spinning"));
            //boolean config2 = Boolean.parseBoolean(props.getProperty("config2"));
            //boolean config3 = Boolean.parseBoolean(props.getProperty("config3"));
        }
    }
}
public class Version {

    public static final String rev_no;

    static {
        Properties props = new Properties();
        String value = "unknown";

        try (InputStream input = Version.class.getClassLoader()
                .getResourceAsStream("version.properties")) {

            props.load(input);
            value = props.getProperty("rev_no");

        } catch (Exception ignored) { }

        rev_no = value;
    }
}

//String rev = Version.rev_no;

@TeleOp(name = "Manual Teleop revision n°" + Version.rev_no)
public class main_beta_v0_3 extends LinearOpMode {
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
    String aux_1_12 = motors.getString("Rotating_shit");
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
          if (SSAO == false);
            if (gamepad1.x) {
              sht = 1;
              break;
            }
          telemetry.addData("key", 123);
          telemetry.update();
        }
        if (SSAO == false) {
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
}




