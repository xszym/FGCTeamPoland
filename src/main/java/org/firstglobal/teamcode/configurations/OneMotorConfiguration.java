package org.firstglobal.teamcode.configurations;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstglobal.FgCommon.RobotConfiguration;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by dell on 2017-05-24.
 */

public class OneMotorConfiguration extends RobotConfiguration {
    public DcMotor motor0;
//    public DcMotor motor1;
public LynxI2cColorRangeSensor colorSensor;

    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        motor0 = (DcMotor) getHardwareOn("motor0", hardwareMap.dcMotor);
//        motor1 = (DcMotor) getHardwareOn("motor1", hardwareMap.dcMotor);
//        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = (LynxI2cColorRangeSensor) getHardwareOn("color", hardwareMap.colorSensor);


    }

    public static OneMotorConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        OneMotorConfiguration config = new OneMotorConfiguration();
        config.init(hardwareMap, telemetry);
        return config;

    }
}
