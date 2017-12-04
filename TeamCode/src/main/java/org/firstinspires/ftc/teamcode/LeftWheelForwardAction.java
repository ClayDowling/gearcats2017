package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by cdowling on 11/26/17.
 */

public class LeftWheelForwardAction extends RobotAction {

    LeftWheelForwardAction(long d) {
        super(d);
    }

    @Override
    public void beginState() {
        bot.leftMotor.setPower(0.75);
        bot.leftMotor.setDirection(FORWARD);

    }

    @Override
    public void endState() {
        bot.leftMotor.setPower(0.0);
    }
}
