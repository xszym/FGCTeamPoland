package org.firstglobal.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstglobal.FgCommon.FGOpMode;
import org.firstglobal.teamcode.configurations.GlobalConfiguration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(group = "GLOBAL ROBOT")

public class GlobalRobot extends FGOpMode {

    public GlobalConfiguration robot;
    private double servoMillCenter = 0.50;

    @Override
    protected void onInit() {

        robot = GlobalConfiguration.newConfig(hardwareMap, telemetry);
        robot.servoMill.setPosition(servoMillCenter);
        robot.servoOrange.setPosition(0.3);
        robot.servoBlue.setPosition(0.38);
    }


    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();

    }

    @Override


    protected void activeLoop() throws InterruptedException {


        drive();

        collect();

        hanging();

        dropBalls();

        randomBallsUp();


        double distanceL = robot.colorSensorCenter.getDistance(DistanceUnit.CM);

        int state = 1;

        if (state == 1) {
            sortBalls(distanceL);
            state = 2;
        } else if (state == 2) {
            state = 1;
        }


        telemetry.addData("Cm:   ", robot.colorSensorCenter.getDistance(DistanceUnit.CM));
        telemetry.addData("red", robot.colorSensorCenter.red());
        telemetry.addData("blue", robot.colorSensorCenter.blue());
        telemetry.addData("green", robot.colorSensorCenter.green());
        telemetry.addData("alpha", robot.colorSensorCenter.alpha());
        telemetry.addData("   ", "");
        telemetry.addData("Sensor:   ", "Lewy");
        telemetry.addData("Cm:   ", robot.colorSensorBlue.getDistance(DistanceUnit.CM));
        telemetry.addData("red", robot.colorSensorBlue.red());
        telemetry.addData("blue", robot.colorSensorBlue.blue());
        telemetry.addData("green", robot.colorSensorBlue.green());
        telemetry.addData("alpha", robot.colorSensorBlue.alpha());
        telemetry.update();

        idle();

    }

    private void randomBallsUp() {
        if (gamepad1.a || gamepad2.a) {
            robot.servoRandom.setPosition(0);
        } else {
            robot.servoRandom.setPosition(0.1);
        }
    }

    private void sortBalls(double distanceL) {


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

    private void dropBalls() {


        double servoOrangeOpenPosition = 0.6;
        double servoOrangeZeroPosition = 0.3;
        double servoOrangeReversePosition = 0.1;

        double servoBlueOpenPosition = 0.65;
        double servoBlueZeroPosition = 0.38;
        double servoBlueReversePosition = 0.1;

        if (gamepad1.b) {

            double range = 0.05;

            if (robot.servoBlue.getPosition() > servoBlueZeroPosition - range) {
                robot.servoBlue.setPosition(servoBlueReversePosition);
            } else if (robot.servoBlue.getPosition() < servoBlueReversePosition + range) {
                robot.servoBlue.setPosition(servoBlueZeroPosition);
            }

            if (robot.servoOrange.getPosition() > servoOrangeZeroPosition - range) {
                robot.servoOrange.setPosition(servoOrangeReversePosition);
            } else if (robot.servoOrange.getPosition() < servoOrangeReversePosition + range) {
                robot.servoOrange.setPosition(servoOrangeZeroPosition);
            }

        } else {
            robot.servoBlue.setPosition(servoBlueZeroPosition);
            robot.servoOrange.setPosition(servoOrangeZeroPosition);
        }

        //ORANGE
        boolean leftTrigger = gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0;

        if ((gamepad1.y && leftTrigger) || (gamepad2.y && leftTrigger)) {
            robot.servoOrange.setPosition(servoOrangeReversePosition);

        } else if (gamepad1.y || gamepad2.y) {
            robot.servoOrange.setPosition(servoOrangeOpenPosition);
        }

        //BLUE
        if ((gamepad1.x && leftTrigger) || (gamepad2.x && leftTrigger)) {
            robot.servoBlue.setPosition(servoBlueReversePosition);

        } else if (gamepad1.x || gamepad2.x) {
            robot.servoBlue.setPosition(servoBlueOpenPosition);
        }

    }

    private void hanging() {

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

    private void collect() {

        double collectPower = 0;

        if (gamepad2.right_trigger != 0) {
            collectPower = gamepad2.right_trigger;
        } else if (gamepad1.right_trigger != 0) {
            collectPower = gamepad1.right_trigger;
        }

        robot.collectMotor.setPower(-collectPower);

    }

    private void drive() {

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


