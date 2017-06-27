package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by dell on 2017-06-27.
 */


public class SegregateBalls extends GlobalRobot {

    private DropOrange dropOrange;
    private DropBlue dropBlue;
    private 

    public SegregateBalls() {
        dropOrange = new DropOrange();
        dropBlue = new DropBlue();
    }

    public void segregateLoop() {

        double distanceL = robot.colorSensorCenter.getDistance(DistanceUnit.CM);


        if (gamepad1.left_bumper || gamepad2.left_bumper || gamepad1.right_bumper || gamepad2.right_bumper) {

            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                millServoPosition(GlobalRobot.SERVOMILLCENTER + 0.15);
            } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
                millServoPosition(GlobalRobot.SERVOMILLCENTER - 0.1);
            }

        } else {

            if (distanceL < 20) {
                if (robot.colorSensorCenter.blue() > robot.colorSensorCenter.red())
                {
                    millServoPosition(GlobalRobot.SERVOMILLCENTER + 0.2);
                    dropBlue.setRandomBlueBalls(true);
                } else {
                    millServoPosition(GlobalRobot.SERVOMILLCENTER - 0.15);
                    dropOrange.setRandomOrangeBalls(true);
                }
            } else {

                dropBlue.setRandomBlueBalls(false);
                dropOrange.setRandomOrangeBalls(false);
                millServoPosition(GlobalRobot.SERVOMILLCENTER);
            }
        }
    }


    private void millServoPosition(double position) {
        robot.servoMill.setPosition(position);
    }

}
