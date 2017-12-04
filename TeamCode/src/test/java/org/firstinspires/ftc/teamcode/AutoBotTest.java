package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.junit.Before;
import org.junit.Test;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cdowling on 11/18/17.
 */
public class AutoBotTest {

    AutoBot bot;
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo colorArmServo;
    ElapsedTime stateTimer;

    @Before
    public void setUp() {
        leftMotor = mock(DcMotor.class);
        rightMotor = mock(DcMotor.class);
        colorArmServo = mock(Servo.class);
        stateTimer = mock(ElapsedTime.class);
        bot = new AutoBot();
        bot.stateTimer = stateTimer;
        bot.leftMotor = leftMotor;
        bot.rightMotor = rightMotor;
        bot.colorArmServo = colorArmServo;
    }

    @Test
    public void loop_whenCurrentActionEmpty_makesFirstActionCurrentAction() {
        RobotAction firstAction = new LeftWheelForwardAction(500L);
        bot.actions.add(firstAction);
        bot.currentAction = null;

        bot.loop();

        assertEquals(firstAction, bot.currentAction);
    }

    @Test
    public void loop_whenFirstActionIsSelected_stateTimerIsReset() {
        RobotAction firstAction = new LeftWheelForwardAction(500L);
        bot.actions.add(firstAction);
        bot.currentAction = null;

        bot.loop();

        verify(stateTimer).reset();
    }

    @Test
    public void loop_whenFirstActionIsSelected_firstActionIsRemovedFromActionList() {
        RobotAction firstAction = new LeftWheelForwardAction(500L);
        bot.actions.add(firstAction);
        bot.currentAction = null;

        bot.loop();

        assertThat("Actions list is not empty", bot.actions.isEmpty(), is(true));
        assertThat("Actions list contains firstAction", bot.actions.contains(firstAction), is(false));
    }

    @Test
    public void loop_whenStateTimerTimeLessThanActionDuration_nextActionIsNotPickedAndTimerNotReset() {
        RobotAction firstAction = new LeftWheelForwardAction(500L);
        RobotAction secondAction = new ExtendColorArmAction(100L);

        bot.actions.add(secondAction);
        bot.currentAction = firstAction;

        when(stateTimer.time(MILLISECONDS)).thenReturn(500L);
        bot.loop();

        assertEquals(firstAction, bot.currentAction);
        verify(stateTimer, never()).reset();
    }

    @Test
    public void loop_whenStateTimerTimeGreaterThanActionDuration_nextActionIsPickedTimerResetNewActionRemovedFromList() {
        RobotAction firstAction = new BothWheelsForwardAction(500L);
        RobotAction secondAction = new LeftWheelForwardAction(100);
        RobotAction thirdAction = new ExtendColorArmAction(750L);

        bot.actions.add(secondAction);
        bot.actions.add(thirdAction);
        bot.currentAction = firstAction;

        when(stateTimer.time(MILLISECONDS)).thenReturn(501L);
        bot.loop();

        assertEquals(secondAction, bot.currentAction);
        verify(stateTimer).reset();
        assertThat(bot.actions.size(), is(1));
        assertThat(bot.actions.contains(secondAction), is(false));
        assertThat(bot.actions.contains(thirdAction), is(true));
    }

    @Test
    public void loop_whenCurrentActionBecomesLeftWheelForward_leftMotorDirectionIsForward() {
        RobotAction firstAction = new LeftWheelForwardAction(500);

        bot.actions.add(firstAction);
        bot.currentAction = null;

        bot.loop();

        verify(leftMotor).setDirection(FORWARD);
    }

    @Test
    public void loop_whenCurrentActionBecomesLeftWheelForward_leftMotorSpeedSetToThreeQuarterSpeed() {
        RobotAction firstAction = new LeftWheelForwardAction(500);

        bot.actions.add(firstAction);
        bot.currentAction = null;

        bot.loop();

        verify(leftMotor).setPower(0.75);
    }

    @Test
    public void loop_whenCurrentActionStopsBeingLeftWheelForward_leftMotorSpeedSetToZero() {
        RobotAction secondAction = new ExtendColorArmAction(10L);
        RobotAction firstAction = new LeftWheelForwardAction(500);
        firstAction.bot = bot;

        bot.actions.add(secondAction);
        bot.currentAction = firstAction;
        when(stateTimer.time(MILLISECONDS)).thenReturn(501L);

        bot.loop();

        assertThat(bot.currentAction, is(secondAction));
        verify(leftMotor).setPower(0.0);
    }

    @Test
    public void loop_whenCurrentStopsBeingExtendColorServoArm_servoPositionIsNotUnset() {
        RobotAction firstAction = new ExtendColorArmAction(100L);
        RobotAction secondAction = new LeftWheelForwardAction(500);

        bot.actions.add(secondAction);
        bot.currentAction = firstAction;
        when(stateTimer.time(MILLISECONDS)).thenReturn(101L);

        bot.loop();

        verify(colorArmServo, never()).setPosition(anyDouble());
    }

}