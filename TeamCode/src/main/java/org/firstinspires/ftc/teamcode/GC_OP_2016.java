package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ratpack on 9/28/2016.
 */

//In order for an op mode to appear for selection, it must be registered in com.qualcomm.ftcrobotcontroller.opmodes.FtcOpModeRegister.
/*
    See class com.qualcomm.robotcore.hardware.HardwareMap for using hardware (Motors, Sensors).
    Ex: <init> motorLeft = hardwareMap.dcMotor.get("motor_1");

    See class com.qualcomm.robotcore.eventloop.opmode.OpMode for using controllers. (gamepad1 and gamepad2 are variables in class OpMode)
    Ex: <loop> drive_power = gamepad1.left_stick_y;
 */



public class GC_OP_2016 extends OpMode {
    DcMotor in;
    DcMotor cata;
    DcMotor left;
    DcMotor right;
    TouchSensor touchSensor;

    long time;

    int launches = 0;
    boolean b = false;
    boolean loaded;

    @Override
    public void init() {
        in= hardwareMap.dcMotor.get("in");
        cata=hardwareMap.dcMotor.get("cata");
        right=hardwareMap.dcMotor.get("right");
        left=hardwareMap.dcMotor.get("left");
        touchSensor=hardwareMap.touchSensor.get("touchSensor");

        loaded = false;

        time=-1;

        in.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        left.setDirection(DcMotor.Direction.REVERSE);
        cata.setDirection(DcMotor.Direction.REVERSE);
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {



    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        try {
            long t = System.currentTimeMillis();
            if(time==-1){
                time=t;
            }

            if(t<time+6500) {
                //Auton.

                if (touchSensor.isPressed()) {
                    loaded = true;
                }

                if (t < time + 1000) {//lowers intake
                    in.setPower(0.2);
                }else
                if (t > time + 1000 && t < time + 1900) {//shoots ball
                    in.setPower(0);
                    loaded = false;
                }else
                if (t > time + 1900 && t < time + 2900) { //raises the intake
                    in.setPower(-0.2);
                }else
                if(t>time+2900 && t<time+3900){
                    in.setPower(0.2);
                }else
                if (t > time + 3900 && t < time + 4300) {//shoots next ball
                    loaded = false;
                    in.setPower(0);
                }else
                if (t > time + 4300 && t < time + 6400 ){//change i made just now) {//goes forward (cap ball+park?)

                    left.setPower(-1);
                    right.setPower(-1);
                }else{
                    left.setPower(0);
                    right.setPower(0);
                }

                //catapult
                if (loaded) {
                    cata.setPower(0);
                } else {
                    cata.setPower(1);
                }
            }else {
                //teleOP
                left.setPower(gamepad1.left_stick_y);
                right.setPower(gamepad1.right_stick_y);

                //catapult launch

                if (touchSensor.isPressed() && !gamepad1.x) {
                    loaded = true;
                }

                if (gamepad1.x) {
                    loaded = false;
                }

                //catapult
                if (loaded) {
                    cata.setPower(0);
                } else {
                    cata.setPower(1);
                }

                //intake position
                if (gamepad1.right_trigger > 0.5) {
                    int pos = 0;
                    in.setPower(0.3);

                } else if (gamepad1.left_trigger > 0.5) {
                    int pos = 0;
                    in.setPower(-0.3);
                } else if (gamepad1.b) {
                    int pos = 0;
                    in.setPower(-1);

                    left.setPower(-1);
                    right.setPower(-1);
                } else {
                    int pos = 0;
                    in.setPower(0);

                }
            }

        }catch(Exception e) {}
    }
}

