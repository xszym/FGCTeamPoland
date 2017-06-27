package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by dell on 2017-06-27.
 */


public class SegregateBalls extends GlobalRobot {

    private double servoMillCenter = 0.50;

    public SegregateBalls() {

    }

    public void segregateLoop() {

        double distanceL = robot.colorSensorCenter.getDistance(DistanceUnit.CM);

        if (gamepad1.left_bumper || gamepad2.left_bumper || gamepad1.right_bumper || gamepad2.right_bumper) {

            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                millServoPosition(servoMillCenter + 0.15);
            } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
                millServoPosition(servoMillCenter - 0.1);
            }

        } else {

            if (distanceL < 20) {
                if (robot.colorSensorCenter.blue() > robot.colorSensorCenter.red())

                {
                    millServoPosition(servoMillCenter + 0.2);
                } else {
                    millServoPosition(servoMillCenter - 0.15);
                }
            } else {

                millServoPosition(servoMillCenter);
            }
        }
    }


    public void millServoPosition(double position) {
        robot.servoMill.setPosition(position);
    }

}
