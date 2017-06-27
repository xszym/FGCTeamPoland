package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class RandomBallsUp extends GlobalRobot {

    public RandomBallsUp(){

    }

    public void randomBallUpLoop(){
        if (gamepad1.a || gamepad2.a) {
            robot.servoRandom.setPosition(0);
        } else {
            robot.servoRandom.setPosition(0.1);
        }
    }
}
