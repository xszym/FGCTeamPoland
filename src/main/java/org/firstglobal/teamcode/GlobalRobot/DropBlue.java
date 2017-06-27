package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class DropBlue extends GlobalRobot {

    double servoBlueOpenPosition = 0.65;
    double servoBlueZeroPosition = 0.38;
    double servoBlueReversePosition = 0.1;

    double range = 0.05;

    private boolean randomBlueBalls;
    private double servoBluePosition;
    public DropBlue(){

    }

    public void dropBlueLoop() {
        if (gamepad1.b || isRandomBlueBalls()) {



            if (robot.servoBlue.getPosition() > servoBlueZeroPosition - range) {
                servoBluePosition = servoBlueReversePosition;
            } else if (robot.servoBlue.getPosition() < servoBlueReversePosition + range) {
                servoBluePosition = servoBlueZeroPosition;
            }


        } else {
            servoBluePosition = servoBlueZeroPosition;

        }

        //ORANGE
        boolean leftTrigger = gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0;


        //BLUE
        if ((gamepad1.x && leftTrigger) || (gamepad2.x && leftTrigger)) {
            servoBluePosition = servoBlueReversePosition;

        } else if (gamepad1.x || gamepad2.x) {

            servoBluePosition = servoBlueOpenPosition;
        }

        robot.servoBlue.setPosition(servoBluePosition);
    }

    public boolean isRandomBlueBalls() {
        return randomBlueBalls;
    }

    public void setRandomBlueBalls(boolean randomBlueBalls) {
        this.randomBlueBalls = randomBlueBalls;
    }
}
