package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.junit.Before;
import org.junit.Test;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cdowling on 11/29/17.
 */
public class LeftWheelForwardActionTest {

    AutoBot bot;
    DcMotor leftMotor;

    RobotAction action;

    @Before
    public void setUp() {
        leftMotor = mock(DcMotor.class);
        bot = new AutoBot();
        bot.leftMotor = leftMotor;
        action = new LeftWheelForwardAction(27);
        action.bot = bot;
    }

    @Test
    public void constructor_setsDuration() {
        assertThat(action.duration, is(27L));
    }

    @Test
    public void beginState_setsDirection() {
        action.beginState();
        verify(leftMotor).setDirection(FORWARD);
    }

    @Test
    public void beginState_setsPower() {
        action.beginState();
        verify(leftMotor).setPower(0.75);
    }

    @Test
    public void endState_setPower() {
        action.endState();
        verify(leftMotor).setPower(0.0);
    }
}