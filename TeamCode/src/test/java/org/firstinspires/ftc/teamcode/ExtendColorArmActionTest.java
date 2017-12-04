package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cdowling on 12/3/17.
 */
public class ExtendColorArmActionTest {

    AutoBot bot;
    Servo colorArmServo;

    RobotAction action;

    @Before
    public void setUp() {
        colorArmServo = mock(Servo.class);
        bot = new AutoBot();
        bot.colorArmServo = colorArmServo;
        action = new ExtendColorArmAction(23);
        action.bot = bot;
    }

    @Test
    public void beginState_extendToFullPosition() {
        action.beginState();
        verify(colorArmServo).setPosition(1.0);
    }
}