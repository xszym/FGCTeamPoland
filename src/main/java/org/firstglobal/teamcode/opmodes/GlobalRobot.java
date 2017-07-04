package org.firstglobal.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstglobal.FgCommon.FGOpMode;
import org.firstglobal.teamcode.GlobalRobot.CollectBalls;
import org.firstglobal.teamcode.GlobalRobot.DriveTrain;
import org.firstglobal.teamcode.GlobalRobot.DropBlue;
import org.firstglobal.teamcode.GlobalRobot.DropOrange;
import org.firstglobal.teamcode.GlobalRobot.Hanging;
import org.firstglobal.teamcode.GlobalRobot.RandomBallsUp;
import org.firstglobal.teamcode.GlobalRobot.SegregateBalls;
import org.firstglobal.teamcode.configurations.GlobalConfiguration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(group = "GLOBAL ROBOT")

public class GlobalRobot extends FGOpMode {

    public static final double SERVOMILLCENTER = 0.5;
    public GlobalConfiguration robot;

    private CollectBalls collectBalls;
    private DriveTrain driveTrain;
    private DropBlue dropBlue;
    private DropOrange dropOrange;
    private Hanging hanging;
    private RandomBallsUp randomBallsUp;
    private SegregateBalls segregateBalls;


    @Override
    protected void onInit() {

        robot = GlobalConfiguration.newConfig(hardwareMap, telemetry);

        robot.servoMill.setPosition(SERVOMILLCENTER);
        robot.servoOrange.setPosition(0.3);
        robot.servoBlue.setPosition(0.38);

        collectBalls  = new CollectBalls();
        driveTrain = new DriveTrain();
        dropBlue = new DropBlue();
        dropOrange = new DropOrange();
        hanging = new Hanging();
        randomBallsUp = new RandomBallsUp();
        segregateBalls = new SegregateBalls();

    }


    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();

    }

    @Override


    protected void activeLoop() throws InterruptedException {

        driveTrain.driveLoop();

        collectBalls.collectBallsLoop();

        hanging.hangingLoop();

        dropOrange.dropOrangeLoop();

        dropBlue.dropBlueLoop();

        randomBallsUp.randomBallUpLoop();



        int state = 1;

        if (state == 1) {
            segregateBalls.segregateLoop();
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

        telemetry.update();

        idle();

    }

}


