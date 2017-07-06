package org.firstglobal.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstglobal.FgCommon.FGOpMode;
import org.firstglobal.teamcode.configurations.OurRobotConfiguration2;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(group = "OurRobot3")

public class OurRobot2 extends FGOpMode {


    private OurRobotConfiguration2 robot;
    private double servoMillCenter = 0.50;
    private boolean isRandomBalls = false;
    private boolean isRandomBlue = false;
    private boolean isRandomOrange = false;
    private boolean isSort = true;

    private int oldRandomMotorPosition;

    @Override
    protected void onInit() {

        robot = OurRobotConfiguration2.newConfig(hardwareMap, telemetry);
        robot.servoMill.setPosition(servoMillCenter);
        robot.servoO.setPosition(0.3);
        robot.servoB.setPosition(0.38);
        oldRandomMotorPosition = robot.randomMotor.getCurrentPosition();
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

        try {
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
        } catch (Exception e) {

        }
     //   telemetry.addData("color L status", robot.colorSensorLeft.status());
        telemetry.addData("color C status", robot.colorSensorCenter.status());

        // robot.colorSensorLeft.resetDeviceConfigurationForOpMode();
         robot.colorSensorCenter.resetDeviceConfigurationForOpMode();

       // telemetry.addData("color L status", robot.colorSensorLeft.status());
        telemetry.addData("color C status", robot.colorSensorCenter.status());

        telemetry.update();

        idle();

    }

    private void randomBallsUp() {
        int curretRandomMotorPosition = robot.randomMotor.getCurrentPosition();
        double roznica = curretRandomMotorPosition - oldRandomMotorPosition;
        if (isRandomBalls()) {
            double speedRandomMotorSpeed = 0.3;

            if (Math.abs(roznica) < 50) {
                speedRandomMotorSpeed = 1;
            }

//            try {
//                if (robot.colorSensorLeft.getDistance(DistanceUnit.CM) < 70) {
//                    speedRandomMotorSpeed = speedRandomMotorSpeed * -1;
//                    telemetry.addData("robot.colorSensorLeft distance", robot.colorSensorLeft.getDistance(DistanceUnit.CM));
//                }
//            } catch (Exception e) {
//
//        }


            robot.randomMotor.setPower(-speedRandomMotorSpeed);


        } else {
            robot.randomMotor.setPower(0);
        }

        telemetry.addData("motor random position:   ", robot.randomMotor.getCurrentPosition());
        telemetry.addData("roznica:   ", roznica);
        oldRandomMotorPosition = robot.randomMotor.getCurrentPosition();
    }


    private void sortBalls(double distanceL) {


        if (gamepad2.a || gamepad1.a) {
            isSort = true;
        } else if (gamepad2.start || gamepad1.start) {
            isSort = false;
        }


        if (!isSort) {
            millServoPosition(servoMillCenter);
            setRandomBalls(false);

            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                millServoPosition(servoMillCenter + 0.15);
            } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
                millServoPosition(servoMillCenter - 0.1);
            }
        } else {
            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                millServoPosition(servoMillCenter + 0.15);
            } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
                millServoPosition(servoMillCenter - 0.1);
            } else if (distanceL < 8) {

                if (robot.colorSensorCenter.blue() > robot.colorSensorCenter.red() + 5) {
                    millServoPosition(servoMillCenter + 0.15);
                    setRandomBlue(true);
                } else if (robot.colorSensorCenter.red() > robot.colorSensorCenter.blue() + 5) {
                    millServoPosition(servoMillCenter - 0.1);
                    setRandomOrange(true);
                }

            } else {

                millServoPosition(servoMillCenter);
                setRandomBalls(true);

                if (distanceL < 8) {
                    setRandomBalls(false);
                }

            }

        }
    }

    public void millServoPosition(double position) {
        robot.servoMill.setPosition(position);
    }

    private void dropBalls() {


        double servoOrangeOpenPosition = 0.65;
        double servoOrangeZeroPosition = 0.35;
        double servoOrangeReversePosition = 0.23;

        double servoBlueOpenPosition = 0.80;
        double servoBlueZeroPosition = 0.45;
        double servoBlueReversePosition = 0.25;

        if (gamepad2.b || isRandomOrange() || isRandomBlue()) {
            if (gamepad2.b || isRandomBlue()) {
                if (robot.servoB.getPosition() > servoBlueZeroPosition - 0.05) {
                    robot.servoB.setPosition(servoBlueReversePosition);
                    setRandomBlue(false);
                } else if (robot.servoB.getPosition() < servoBlueReversePosition + 0.05) {
                    robot.servoB.setPosition(servoBlueZeroPosition);
                }
            }

            if (gamepad2.b || isRandomOrange()) {
                if (robot.servoO.getPosition() > servoOrangeZeroPosition - 0.05) {
                    robot.servoO.setPosition(servoOrangeReversePosition);
                    setRandomOrange(false);
                } else if (robot.servoO.getPosition() < servoOrangeReversePosition + 0.05) {
                    robot.servoO.setPosition(servoOrangeZeroPosition);
                }
            }
        } else {
            robot.servoB.setPosition(servoBlueZeroPosition);
            robot.servoO.setPosition(servoOrangeZeroPosition);
            robot.servoRandom.setPosition(0);
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
            robot.servoRandom.setPosition(0.3);
        }

    }

    private void hanging() {

        if (gamepad1.dpad_down || gamepad2.dpad_down) {
            //SKLADANIE
            robot.hangingMotor.setPower(1);
            robot.openHangingMotor.setPower(-0.2);
        } else if (gamepad1.dpad_up || gamepad2.dpad_up) {
            //ROZKLADANIE

            robot.hangingMotor.setPower(-1);
            robot.openHangingMotor.setPower(0.4);


        } else {
            robot.hangingMotor.setPower(0);
            robot.openHangingMotor.setPower(0);
        }

    }

    private void collect() {
        double collectPower = 0;
        if (gamepad2.right_trigger != 0) {
            collectPower = gamepad2.right_trigger;
        }
//        else if (gamepad1.right_trigger != 0) {
//            collectPower = gamepad1.right_trigger;
//        }
        robot.collectMotor.setPower(-collectPower);

    }

    private void drive() {

        double leftStickY = gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightTrigger = gamepad1.right_trigger;


        rightTrigger = rightTrigger + 1;
        telemetry.addData("right trigger", rightTrigger);

        robot.frontLeft.setPower(((leftStickY - rightStickX) * rightTrigger) / 2);
        robot.rearLeft.setPower(((leftStickY - rightStickX) * rightTrigger) / 2);

        robot.frontRight.setPower(((leftStickY + rightStickX) * rightTrigger) / 2);

        robot.rearRight.setPower(((leftStickY + rightStickX) * rightTrigger) / 2);

    }


    public boolean isRandomBalls() {
        return isRandomBalls;
    }

    public void setRandomBalls(boolean randomBalls) {
        isRandomBalls = randomBalls;
    }

    public boolean isRandomBlue() {
        return isRandomBlue;
    }

    public void setRandomBlue(boolean randomBlue) {
        isRandomBlue = randomBlue;
    }

    public boolean isRandomOrange() {
        return isRandomOrange;
    }

    public void setRandomOrange(boolean randomOrange) {
        isRandomOrange = randomOrange;
    }
}


