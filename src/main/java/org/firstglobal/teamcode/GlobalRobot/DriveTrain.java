package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class DriveTrain extends GlobalRobot {



    public void driveLoop() {

        double leftStickY = gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;

        robot.frontLeft.setPower(leftStickY - rightStickX);
        robot.rearLeft.setPower(leftStickY - rightStickX);

        robot.frontRight.setPower(leftStickY + rightStickX);
        robot.rearRight.setPower(leftStickY + rightStickX);

        telemetry.addData("left ", leftStickY);
        telemetry.addData("right ", rightStickX);
    }

}
