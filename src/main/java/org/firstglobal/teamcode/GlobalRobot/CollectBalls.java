package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class CollectBalls extends GlobalRobot {
    public CollectBalls(){

    }

    public void collectBallsLoop(){

        double collectPower = 0;

        if (gamepad2.right_trigger != 0) {
            collectPower = gamepad2.right_trigger;
        } else if (gamepad1.right_trigger != 0) {
            collectPower = gamepad1.right_trigger;
        }

        robot.collectMotor.setPower(-collectPower);
    }
}
