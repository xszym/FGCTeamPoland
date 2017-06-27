package org.firstglobal.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstglobal.FgCommon.FGOpMode;
import org.firstglobal.teamcode.configurations.OneMotorConfiguration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(group = "OneMotor")
public class OneMotor extends FGOpMode {

    private OneMotorConfiguration robot;

    @Override
    protected void onInit() {

        robot = OneMotorConfiguration.newConfig(hardwareMap, telemetry);

    }


    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();
    }

    @Override


    protected void activeLoop() throws InterruptedException {

        robot.motor0.setPower(gamepad1.left_stick_y);
//        robot.motor1.setPower(gamepad1.left_stick_y);

        telemetry.addData("dystans:   ", robot.colorSensor.getDistance(DistanceUnit.CM));
        telemetry.update();
        idle();

    }
}
