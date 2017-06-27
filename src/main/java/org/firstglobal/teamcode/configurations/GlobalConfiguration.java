package org.firstglobal.teamcode.configurations;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstglobal.FgCommon.RobotConfiguration;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GlobalConfiguration extends RobotConfiguration {

    //Motors
        /*Drive system*/
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor rearRight;
    public DcMotor rearLeft;

    /*Subsystem*/
    public DcMotor collectMotor;
    public DcMotor hangingMotor;
    public DcMotor openHangingMotor;

    //Sensors
    public LynxI2cColorRangeSensor colorSensorCenter;
    public LynxI2cColorRangeSensor colorSensorBlue;

    //Servo
    public Servo servoBlue;
    public Servo servoOrange;
    public Servo servoMill;
    public Servo servoRandom;


    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        initDriveTrain(hardwareMap);

        initSubsystems(hardwareMap);


    }



    private void initDriveTrain(HardwareMap hardwareMap) {
        //Drive system
            /*Left site*/
        frontLeft = (DcMotor) getHardwareOn("motor0", hardwareMap.dcMotor);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        rearLeft = (DcMotor) getHardwareOn("motor1", hardwareMap.dcMotor);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);

            /*Right site*/
        frontRight = (DcMotor) getHardwareOn("motor2", hardwareMap.dcMotor);

        rearRight = (DcMotor) getHardwareOn("motor3", hardwareMap.dcMotor);

    }

    private void initSubsystems(HardwareMap hardwareMap) {
        //Subsystems

        //Motors
        collectMotor = (DcMotor) getHardwareOn("motor4", hardwareMap.dcMotor);

        hangingMotor = (DcMotor) getHardwareOn("motor5", hardwareMap.dcMotor);

        openHangingMotor = (DcMotor) getHardwareOn("motor6", hardwareMap.dcMotor);

        //Servo
        servoBlue = (Servo) getHardwareOn("servo0", hardwareMap.servo);
        servoBlue.setDirection(Servo.Direction.REVERSE);

        servoMill = (Servo) getHardwareOn("servo3", hardwareMap.servo);
        servoRandom = (Servo) getHardwareOn("servo2", hardwareMap.servo);

        servoOrange = (Servo) getHardwareOn("servo1", hardwareMap.servo);

        //Sensor

        colorSensorCenter = (LynxI2cColorRangeSensor) getHardwareOn("colorC", hardwareMap.colorSensor);

        colorSensorBlue = (LynxI2cColorRangeSensor) getHardwareOn("colorB", hardwareMap.colorSensor);
    }

    public static GlobalConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        GlobalConfiguration config = new GlobalConfiguration();
        config.init(hardwareMap, telemetry);
        return config;

    }
}
