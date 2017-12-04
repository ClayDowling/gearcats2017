package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.JewelColor.RED;

/**
 * Created by cdowling on 12/3/17.
 */

@Autonomous(name = "Red Team Autonomous", group = "Iterative Opmode")
public class RedJewelAutoBot extends AutoBot {

    public RedJewelAutoBot() {
        super();
        actions.add(new ExtendColorArmAction(1000));
        actions.add(new SenseColor(RED));
        actions.add(new RetractColorArm(1000));
    }

}
