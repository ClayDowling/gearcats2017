package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by cdowling on 12/3/17.
 */

public class BothWheelsBackwardAction extends RobotAction {

    public BothWheelsBackwardAction(long d) {
        super(d);
    }

    @Override
    public void beginState() {
        bot.leftMotor.setDirection(REVERSE);
        bot.rightMotor.setDirection(REVERSE);
        bot.leftMotor.setPower(0.75);
        bot.rightMotor.setPower(0.75);
    }

    @Override
    public void endState() {
        bot.leftMotor.setPower(0);
        bot.rightMotor.setPower(0);
    }
}
