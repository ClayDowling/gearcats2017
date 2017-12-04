package org.firstinspires.ftc.teamcode;

/**
 * Created by cdowling on 12/3/17.
 */

public class RetractColorArm extends RobotAction {

    public RetractColorArm(long d) {
        super(d);
    }

    @Override
    public void beginState() {
        bot.colorArmServo.setPosition(0);
    }

    @Override
    public void endState() {

    }
}
