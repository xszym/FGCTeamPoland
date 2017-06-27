package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */


public class DropOrange extends GlobalRobot {

    double servoOrangePosition;
    double servoOrangeOpenPosition = 0.6;
    double servoOrangeZeroPosition = 0.3;
    double servoOrangeReversePosition = 0.1;

    double range = 0.05;



    private boolean randomOrangeBalls = false;

    public void dropOrangeLoop(){

        if (gamepad1.b || isRandomOrangeBalls()) {

            if (robot.servoOrange.getPosition() > servoOrangeZeroPosition - range) {
                servoOrangePosition = servoOrangeReversePosition;
            } else if (robot.servoOrange.getPosition() < servoOrangeReversePosition + range) {
                servoOrangePosition = servoOrangeZeroPosition;
            }

        } else {
            servoOrangePosition = servoOrangeZeroPosition;
        }

        //ORANGE
        boolean leftTrigger = gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0;

        if ((gamepad1.y && leftTrigger) || (gamepad2.y && leftTrigger)) {
            servoOrangePosition = servoOrangeReversePosition;

        } else if (gamepad1.y || gamepad2.y) {
            servoOrangePosition = servoOrangeOpenPosition;
        }

        robot.servoOrange.setPosition(servoOrangePosition);
    }

    public boolean isRandomOrangeBalls() {
        return randomOrangeBalls;
    }

    public void setRandomOrangeBalls(boolean randomOrangeBalls) {
        this.randomOrangeBalls = randomOrangeBalls;
    }
}
