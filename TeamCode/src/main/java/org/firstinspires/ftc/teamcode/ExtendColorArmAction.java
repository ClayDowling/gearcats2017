package org.firstinspires.ftc.teamcode;

/**
 * Created by cdowling on 11/26/17.
 */

public class ExtendColorArmAction extends RobotAction {

    ExtendColorArmAction(long d) {
        super(d);
    }

    @Override
    public void beginState() {
        bot.colorArmServo.setPosition(1.0);
    }

    @Override
    public void endState() {

    }
}
