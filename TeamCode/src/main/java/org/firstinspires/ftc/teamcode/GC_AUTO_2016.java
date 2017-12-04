package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Ratpack on 11/25/2016.
 */
public class GC_AUTO_2016 extends OpMode {

    DcMotor in;
    DcMotor cata;
    DcMotor lefta;
    DcMotor righta;
    TouchSensor touchSensor;

    int left=0;
    int right=0;
    float ticksPerIn=115; //must define
    float r= 8.25f;
    boolean shoot = false;
    boolean loaded = false;

    Thread runThread;

    @Override
    public void init() {
        in= hardwareMap.dcMotor.get("in");
        cata= hardwareMap.dcMotor.get("cata");
        lefta= hardwareMap.dcMotor.get("left");
        righta= hardwareMap.dcMotor.get("right");
        in= hardwareMap.dcMotor.get("in");
        touchSensor= hardwareMap.touchSensor.get("touchSensor");

        in.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        righta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //righta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lefta.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        righta.setDirection(DcMotor.Direction.REVERSE);
        //lefta.setDirection(DcMotor.Direction.REVERSE);
        cata.setDirection(DcMotor.Direction.REVERSE);

        runThread = new Thread(new Runnable() {
            public void run() {
                try {
                    runAuton();
                }catch(Exception e) {}
            }
        });
    }

    public void runAuton() {
        System.out.println("Started Auton");
        righta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lefta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        righta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lefta.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //righta.setPower(1);
        //lefta.setPower(1);
        //walk(50);
        //A
        righta.setPower(1);
        lefta.setPower(1);
        shoot();
        walk(140);
        turn(45);
        activateBeacon();
        turn(90);
        walk(39);
        turn(-90);
        activateBeacon();
        turn(180);
        walk(38);
        turn(-135);
        walk(50);

        /*//B
        shoot();
        walk(140);
        turn(-45);
        activateBeacon();
        turn(-90);
        walk(39);
        turn(90);
        activateBeacon();
        turn(-180);
        walk(38);
        turn(135);
        walk(50);
*/
    }

    public void loop() {
        try {
            if (!runThread.isAlive())
                runThread.start();
        }catch(Exception e) {}

        if(touchSensor.isPressed()&&!shoot){
            loaded=true;
        }

        if(shoot){
            loaded=false;
        }

        if(loaded){
            cata.setPower (0);
        }else{
            cata.setPower(1);
        }
    }

    void walk(float in){
        righta.setPower(1);
        lefta.setPower(1);
        left+=in*ticksPerIn;
        right+=in*ticksPerIn;

        lefta.setTargetPosition(left);
        righta.setTargetPosition(right);
        //righta.setPower(1);
        //lefta.setPower(1);
        try {
            Thread.sleep(50 * (int)in);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        righta.setPower(0);
        lefta.setPower(0);
    }

    void turn(float deg){
        righta.setPower(1);
        lefta.setPower(1);
        left+=deg/360f*2f*3.1415*r*ticksPerIn;
        right-=deg/360f*2f*3.1415*r*ticksPerIn;
        //left += 1440;
        //right -= 1440;

        lefta.setTargetPosition(left);
        righta.setTargetPosition(right);

        try {
            Thread.sleep(20 * Math.abs((int)deg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        righta.setPower(0);
        lefta.setPower(0);
    }

    void activateBeacon (){

    }

    void shoot (){
        shoot = true;
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shoot = false;
    }

}
