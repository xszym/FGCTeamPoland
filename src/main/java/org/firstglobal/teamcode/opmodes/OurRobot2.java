package org.firstglobal.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstglobal.FgCommon.FGOpMode;
import org.firstglobal.teamcode.configurations.OurRobotConfiguration2;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(group = "OurRobot")

public class OurRobot2 extends FGOpMode {

    private OurRobotConfiguration2 robot;
    private boolean wasBlue = false;
    private boolean wasRed = false;
    private double servoMillCenter = 0.50;
    private int openHangingMotorStartPosition;

    @Override
    protected void onInit() {

        robot = OurRobotConfiguration2.newConfig(hardwareMap, telemetry);
        robot.servoMill.setPosition(servoMillCenter);
        robot.servoO.setPosition(0.3);
        robot.servoB.setPosition(0.38);
        openHangingMotorStartPosition = robot.openHangingMotor.getCurrentPosition();
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

        // robot.collectMotor2.setPower(-gamepad1.left_trigger);

        double distanceL = robot.colorSensorCenter.getDistance(DistanceUnit.CM);

        int state = 1;

        if (state == 1) {
            sortBalls(distanceL);
            state = 2;
        } else if (state == 2) {
            state = 1;
        }


        telemetry.addData("Cm:   ", distanceL);
        telemetry.addData("red", robot.colorSensorCenter.red());
        telemetry.addData("blue", robot.colorSensorCenter.blue());
        telemetry.addData("green", robot.colorSensorCenter.green());
        telemetry.addData("alpha", robot.colorSensorCenter.alpha());
        telemetry.update();

        idle();

    }

    private void randomBallsUp() {
        if (gamepad2.a || gamepad1.a) {
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
                if (robot.colorSensorCenter.blue() > robot.colorSensorCenter.red() ||
                        robot.colorSensorCenter.green() > robot.colorSensorCenter.red())

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

            if (robot.servoB.getPosition() > servoBlueZeroPosition - 0.05) {
                robot.servoB.setPosition(servoBlueReversePosition);
            } else if (robot.servoB.getPosition() < servoBlueReversePosition + 0.05) {
                robot.servoB.setPosition(servoBlueZeroPosition);
            }

            if (robot.servoO.getPosition() > servoOrangeZeroPosition - 0.05) {
                robot.servoO.setPosition(servoOrangeReversePosition);
            } else if (robot.servoO.getPosition() < servoOrangeReversePosition + 0.05) {
                robot.servoO.setPosition(servoOrangeZeroPosition);
            }

        } else {
            robot.servoB.setPosition(servoBlueZeroPosition);
            robot.servoO.setPosition(servoOrangeZeroPosition);
        }

        //ORANGE
        boolean leftTrigger = gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0;

        if ((gamepad1.y && leftTrigger) || (gamepad2.y && leftTrigger)) {
            robot.servoO.setPosition(servoOrangeReversePosition);

        } else if (gamepad1.y || gamepad2.y) {
            robot.servoO.setPosition(servoOrangeOpenPosition);
        }

        //BLUE
        if ((gamepad1.x && leftTrigger) || (gamepad2.x && leftTrigger)) {
            robot.servoB.setPosition(servoBlueReversePosition);

        } else if (gamepad1.x || gamepad2.x) {
            robot.servoB.setPosition(servoBlueOpenPosition);
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

//        if(Math.abs(rightStickX) > 0.5){
//
//            //Prawa
//            if(rightStickX>0.5){
//                robot.rearLeft.setPower(leftStickY + rightStickX);
//                robot.frontRight.setPower(leftStickY - rightStickX);
//            } else if(rightStickX < -0.5){
//                robot.rearRight.setPower(leftStickY - rightStickX);
//                robot.frontLeft.setPower(leftStickY + rightStickX);
//            }
//
//
//
//        } else {
        robot.frontLeft.setPower(leftStickY - rightStickX);
        robot.rearLeft.setPower(leftStickY - rightStickX);

        robot.frontRight.setPower(leftStickY + rightStickX);
        robot.rearRight.setPower(leftStickY + rightStickX);
//        }
        telemetry.addData("left ", leftStickY);
        telemetry.addData("right ", rightStickX);


    }


}


