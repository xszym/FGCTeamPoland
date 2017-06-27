package org.firstglobal.teamcode.GlobalRobot;

import org.firstglobal.teamcode.opmodes.GlobalRobot;

/**
 * Created by dell on 2017-06-27.
 */

public class Hanging extends GlobalRobot {

    public Hanging(){

    }

    public void hangingLoop(){

        if (gamepad1.dpad_down || gamepad2.dpad_down) {
            //SKLADANIE

            robot.hangingMotor.setPower(-1);
            robot.openHangingMotor.setPower(0.2);
        } else if (gamepad1.dpad_up || gamepad2.dpad_up) {
            //ROZKLADANIE

            robot.hangingMotor.setPower(1);
            robot.openHangingMotor.setPower(-0.35);

        } else {
            robot.hangingMotor.setPower(0);
            robot.openHangingMotor.setPower(0);
        }
    }
}
