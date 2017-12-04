package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.junit.Before;
import org.junit.Test;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cdowling on 12/3/17.
 */
public class BothWheelsForwardActionTest {

    AutoBot bot;
    DcMotor leftMotor;
    DcMotor rightMotor;

    RobotAction action;

    @Before
    public void setUp() {
        leftMotor = mock(DcMotor.class);
        rightMotor = mock(DcMotor.class);
        bot = new AutoBot();
        bot.leftMotor = leftMotor;
        bot.rightMotor = rightMotor;
        action = new BothWheelsForwardAction(27);
        action.bot = bot;
    }

    @Test
    public void beginState_setsBothWheelsForwardAtPower() {
        action.beginState();
        verify(leftMotor).setDirection(FORWARD);
        verify(rightMotor).setDirection(FORWARD);
        verify(leftMotor).setPower(0.75);
        verify(rightMotor).setPower(0.75);
    }

    @Test
    public void endState_setPowerTo0() {
        action.endState();
        verify(leftMotor).setPower(0);
        verify(rightMotor).setPower(0);
    }

}