package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.junit.Before;
import org.junit.Test;

import static org.firstinspires.ftc.teamcode.JewelColor.RED;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cdowling on 12/3/17.
 */
public class SenseColorTest {

    RobotAction action;
    ColorSensor colorSensor;
    AutoBot bot;

    @Before
    public void setUp() {
        action = new SenseColor(JewelColor.BLUE);
        colorSensor = mock(ColorSensor.class);
        bot = new AutoBot();
        bot.colorSensor = colorSensor;
        action.bot = bot;
    }

    @Test
    public void beginState_whenBlueIsStrongerThanRed_pushBothWheelsForwardOntoActionStack() {
        when(colorSensor.blue()).thenReturn(127);
        when(colorSensor.red()).thenReturn(100);
        action.beginState();
        assertThat(bot.actions.size(), is(1));
        RobotAction nextAction = bot.actions.get(0);
        assertThat(nextAction, instanceOf(BothWheelsForwardAction.class));
    }

    @Test
    public void beginState_whenRedIsStrongerThanBlue_pushBothWheelsBackwardOntoActionStack() {
        when(colorSensor.blue()).thenReturn(90);
        when(colorSensor.red()).thenReturn(130);
        action.beginState();
        assertThat(bot.actions.size(), is(1));
        RobotAction nextAction = bot.actions.get(0);
        assertThat(nextAction, instanceOf(BothWheelsBackwardAction.class));
    }

    @Test
    public void beginState_whenBlueIsStrongerThanRedAndRedWanted_pushBothWheelsBackwardOntoActionStack() {
        action = new SenseColor(RED);
        action.bot = bot;
        when(colorSensor.blue()).thenReturn(127);
        when(colorSensor.red()).thenReturn(100);
        action.beginState();
        assertThat(bot.actions.size(), is(1));
        RobotAction nextAction = bot.actions.get(0);
        assertThat(nextAction, instanceOf(BothWheelsBackwardAction.class));
    }

    @Test
    public void beginState_whenRedIsStrongerThanBlueAndRedWanted_pushBothWheelsForwardOntoActionStack() {
        action = new SenseColor(RED);
        action.bot = bot;
        when(colorSensor.blue()).thenReturn(90);
        when(colorSensor.red()).thenReturn(130);
        action.beginState();
        assertThat(bot.actions.size(), is(1));
        RobotAction nextAction = bot.actions.get(0);
        assertThat(nextAction, instanceOf(BothWheelsForwardAction.class));
    }

    @Test
    public void beginState_whenThereIsOneItemInActionList_pushBothWheelsForwardIsNextAction() {
        when(colorSensor.blue()).thenReturn(127);
        when(colorSensor.red()).thenReturn(100);
        bot.actions.add(new RetractColorArm(1000));
        action.beginState();
        assertThat(bot.actions.size(), is(2));
        RobotAction nextAction = bot.actions.get(0);
        assertThat(nextAction, instanceOf(BothWheelsForwardAction.class));
    }

}