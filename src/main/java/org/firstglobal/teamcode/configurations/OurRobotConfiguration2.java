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
    public DcMotor hangingMotor;
    public DcMotor openHangingMotor;
    public DcMotor randomMotor;
    public LynxI2cColorRangeSensor colorSensorCenter;
   // public LynxI2cColorRangeSensor colorSensorRight;
   // public LynxI2cColorRangeSensor colorSensorLeft;
    public Servo servoB;
    public Servo servoO;
    public Servo servoMill;
    public Servo servoRandom;


    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        //Drive system
        frontLeft = (DcMotor) getHardwareOn("frontLeft", hardwareMap.dcMotor);
        //frontLeft.setDirection(DcMotor.Direction.REVERSE);

        rearLeft = (DcMotor) getHardwareOn("rearLeft", hardwareMap.dcMotor);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = (DcMotor) getHardwareOn("frontRight", hardwareMap.dcMotor);

        rearRight = (DcMotor) getHardwareOn("rearRight", hardwareMap.dcMotor);

        //Collect balls
        collectMotor = (DcMotor) getHardwareOn("collect", hardwareMap.dcMotor);



        randomMotor = (DcMotor) getHardwareOn("randomMotor", hardwareMap.dcMotor);




        //       collectMotor2 = (DcMotor) getHardwareOn("motor7", hardwareMap.dcMotor);

        hangingMotor = (DcMotor) getHardwareOn("hanging", hardwareMap.dcMotor);

        openHangingMotor = (DcMotor) getHardwareOn("openHanging", hardwareMap.dcMotor);

        colorSensorCenter = (LynxI2cColorRangeSensor) getHardwareOn("colorC", hardwareMap.colorSensor);
        //colorSensorRight = (LynxI2cColorRangeSensor) getHardwareOn("colorR", hardwareMap.colorSensor);
        //colorSensorLeft = (LynxI2cColorRangeSensor) getHardwareOn("colorL", hardwareMap.colorSensor);

        servoO = (Servo) getHardwareOn("servoOrange", hardwareMap.servo);

        servoB = (Servo) getHardwareOn("servoBlue", hardwareMap.servo);
        servoB.setDirection(Servo.Direction.REVERSE);

        servoMill = (Servo) getHardwareOn("servoMill", hardwareMap.servo);

        servoRandom = (Servo) getHardwareOn("servoRandom", hardwareMap.servo);


    }

    public static OurRobotConfiguration2 newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        OurRobotConfiguration2 config = new OurRobotConfiguration2();
        config.init(hardwareMap, telemetry);
        return config;

    }
}
