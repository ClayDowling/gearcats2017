package org.firstinspires.ftc.teamcode;

/**
 * Created by cdowling on 11/7/17.
 */

public abstract class RobotAction {
    public long duration;
    public AutoBot bot;

    public RobotAction() {
        duration = 0;
    }

    public RobotAction(long d) {
        duration = d;
    }

    public abstract void beginState();

    public abstract void endState();

}
