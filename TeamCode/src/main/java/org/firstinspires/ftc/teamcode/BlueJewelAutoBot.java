package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.JewelColor.BLUE;

/**
 * Created by cdowling on 12/3/17.
 */

@Autonomous(name = "Blue Team Autonomous", group = "Iterative Opmode")
public class BlueJewelAutoBot extends AutoBot {

    public BlueJewelAutoBot() {
        super();
        actions.add(new ExtendColorArmAction(1000));
        actions.add(new SenseColor(BLUE));
        actions.add(new RetractColorArm(1000));
    }
}

