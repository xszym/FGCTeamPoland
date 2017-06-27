package org.firstglobal.teamcode.configurations;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstglobal.FgCommon.RobotConfiguration;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class OurRobotConfiguration2 extends RobotConfiguration {
    //motors
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor rearRight;
    public DcMotor rearLeft;
    public DcMotor collectMotor;
    //    public DcMotor collectMotor2;
    public DcMotor hangingMotor;
    public DcMotor openHangingMotor;
    public LynxI2cColorRangeSensor colorSensorCenter;
    public Servo servoB;
    public Servo servoO;
    public Servo servoMill;
    public Servo servoRandom;


    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        //Drive system
        frontLeft = (DcMotor) getHardwareOn("motor0", hardwareMap.dcMotor);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        rearLeft = (DcMotor) getHardwareOn("motor1", hardwareMap.dcMotor);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = (DcMotor) getHardwareOn("motor2", hardwareMap.dcMotor);

        rearRight = (DcMotor) getHardwareOn("motor3", hardwareMap.dcMotor);

        //Collect balls
        collectMotor = (DcMotor) getHardwareOn("motor4", hardwareMap.dcMotor);

        //       collectMotor2 = (DcMotor) getHardwareOn("motor7", hardwareMap.dcMotor);

        hangingMotor = (DcMotor) getHardwareOn("motor5", hardwareMap.dcMotor);

        openHangingMotor = (DcMotor) getHardwareOn("motor6", hardwareMap.dcMotor);

        colorSensorCenter = (LynxI2cColorRangeSensor) getHardwareOn("colorC", hardwareMap.colorSensor);

        servoO = (Servo) getHardwareOn("servo1", hardwareMap.servo);

        servoB = (Servo) getHardwareOn("servo0", hardwareMap.servo);
        servoB.setDirection(Servo.Direction.REVERSE);

        servoMill = (Servo) getHardwareOn("servo3", hardwareMap.servo);

        servoRandom = (Servo) getHardwareOn("servo2", hardwareMap.servo);


    }

    public static OurRobotConfiguration2 newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        OurRobotConfiguration2 config = new OurRobotConfiguration2();
        config.init(hardwareMap, telemetry);
        return config;

    }
}
