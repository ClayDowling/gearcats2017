package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.android.dex.EncodedValueReader;

/**
 * Created by cdowling on 10/13/17.
 */

@TeleOp
public class MyFirstJavaOpMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorTest;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private EncodedValueReader encoder;

    @Override
    public void runOpMode() throws InterruptedException {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
        DistanceSensor ds = hardwareMap.get(DistanceSensor.class, "colormebeautiful");
        ColorSensor cs = hardwareMap.get(ColorSensor.class, "colormebeautiful");
        motorTest = hardwareMap.get(DcMotor.class, "right");
//        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
//        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
//        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorTest.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        motorTest.setPower(1.0);

        double distance = ds.getDistance(DistanceUnit.INCH);
        int argb = cs.argb();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Motor Power", String.format("%4.2f", motorTest.getPower()));

            telemetry.update();


        }
    }
}
