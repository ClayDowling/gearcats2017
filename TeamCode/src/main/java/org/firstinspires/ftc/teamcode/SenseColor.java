package org.firstinspires.ftc.teamcode;

/**
 * Created by cdowling on 12/3/17.
 */

public class SenseColor extends RobotAction {

    JewelColor wantedColor;

    public SenseColor(JewelColor wanted) {
        this.wantedColor = wanted;
    }

    @Override
    public void beginState() {
        int blue = bot.colorSensor.blue();
        int red = bot.colorSensor.red();

        int wantedHigh;
        int wantedLow;

        if (wantedColor == JewelColor.BLUE) {
            wantedHigh = blue;
            wantedLow = red;
        } else {
            wantedHigh = red;
            wantedLow = blue;
        }
        if (wantedHigh > wantedLow) {
            bot.actions.add(0, new BothWheelsForwardAction(100));
        } else {
            bot.actions.add(0, new BothWheelsBackwardAction(100));
        }
    }

    @Override
    public void endState() {

    }
}
