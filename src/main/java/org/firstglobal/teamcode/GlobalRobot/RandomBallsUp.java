package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class RandomBallsUp extends GlobalRobot {

    private boolean isRandom = false;
    private double servoRandomZeroPosition = 0.0;
    private double servoRandomMaxPosition = 0.0;
    private double servoRandomPosition;
    private double range = 0.0;

    public RandomBallsUp(){


    }

    public void randomBallUpLoop(){

        if(isRandom()) {
            if (robot.servoRandom.getPosition() > servoRandomZeroPosition - range) {
                servoRandomPosition = servoRandomMaxPosition;
            } else if (robot.servoRandom.getPosition() < servoRandomMaxPosition + range) {
                servoRandomPosition = servoRandomZeroPosition;
            }
        }
         if (gamepad1.a || gamepad2.a || isRandom) {
             servoRandomPosition = 0;
        } else {
             servoRandomPosition = 0.1;
        }
        robot.servoRandom.setPosition(servoRandomPosition);

    }


    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }
}
