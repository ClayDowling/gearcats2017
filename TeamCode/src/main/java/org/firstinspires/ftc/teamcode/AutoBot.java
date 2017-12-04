package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.MILLISECONDS;

/**
 * Created by cdowling on 11/7/17.
 */

public class AutoBot extends OpMode {

    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public Servo colorArmServo;
    public ColorSensor colorSensor;
    List<RobotAction> actions;
    ElapsedTime stateTimer;
    RobotAction currentAction = null;

    public AutoBot() {
        actions = new ArrayList<>();
        stateTimer = new ElapsedTime(MILLISECONDS);
    }

    @Override
    public void init() {
        // hardware mapping goes here - soham

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    private void endCurrentAction() {
        if (null != currentAction && null != currentAction.bot) {
            currentAction.endState();
        }
    }

    private void startCurrentAction() {
        currentAction.beginState();
    }

    @Override
    public void loop() {

        if (currentAction == null || stateTimer.time(TimeUnit.MILLISECONDS) > currentAction.duration) {

            endCurrentAction();

            currentAction = actions.get(0);
            actions.remove(0);
            stateTimer.reset();
            currentAction.bot = this;

            startCurrentAction();
        }

    }

}
