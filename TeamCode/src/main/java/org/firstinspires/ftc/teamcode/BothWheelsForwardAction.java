package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by cdowling on 11/26/17.
 */

public class BothWheelsForwardAction extends RobotAction {

    public BothWheelsForwardAction(long d) {
        super(d);
    }

    @Override
    public void beginState() {
        bot.leftMotor.setDirection(FORWARD);
        bot.rightMotor.setDirection(FORWARD);
        bot.leftMotor.setPower(0.75);
        bot.rightMotor.setPower(0.75);
    }

    @Override
    public void endState() {
        bot.leftMotor.setPower(0);
        bot.rightMotor.setPower(0);
    }
}
