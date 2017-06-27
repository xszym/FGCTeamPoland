package org.firstglobal.FgCommon;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;


/**
 * Operation to assist with operating a servo from a gamepad button
 */
public class GamePadServo  extends OpModeComponent {

    // use the Y and A buttons for up and down and the  X and B buttons for left and right

    public  static enum Control {
        Y_A,
        X_B
    }

    private Control currentControl;

    // amount to change the servo position by
    private double servoDelta = 0.01;
    private ServoComponent servoComponent;
    private CRServo crServo;
    private Gamepad gamepad;

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     * @param opMode
     * @param gamepad Gamepad
     * @param servo Servo
     * @param control  use the Y and A buttons for up and down and the  X and B buttons for left and right
     */
    public GamePadServo(FGOpMode opMode, Gamepad gamepad, Servo servo, Control control, double initialPosition) {

        this(opMode,gamepad, servo,control,initialPosition,false);

    }

    /**
     * Constructor for operation
     * @param opMode
     * @param gamepad Gamepad
     * @param servo Servo
     * @param control  use the Y and A buttons for up and down and the  X and B buttons for left and right
     * @param initialPosition must set the initial position of the servo before working with it
     * @param reverseOrientation  true if the servo is install in the reverse orientation
     */
    public GamePadServo(FGOpMode opMode, Gamepad gamepad, Servo servo, Control control, double initialPosition,boolean reverseOrientation) {

        super(opMode);
        this.servoComponent = new ServoComponent(opMode,  servo,   initialPosition , reverseOrientation);
        this.gamepad = gamepad;
        this.currentControl = control;

    }

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     * @param opMode
     * @param gamepad Gamepad
     * @param crServo CRServo
     * @param control  use the Y and A buttons for up and down and the  X and B buttons for left and right
     */
    public GamePadServo(FGOpMode opMode, Gamepad gamepad, CRServo crServo, Control control ) {

        super(opMode);
        this.crServo = crServo;
        this.gamepad = gamepad;
        this.currentControl = control;

    }

    /**
     * Update motors with latest gamepad state
     */
    public void update() {

        boolean rightVal =  currentControl == Control.Y_A  ?  gamepad.y : gamepad.b;
        boolean leftVal =   currentControl == Control.Y_A  ?  gamepad.a : gamepad.x;

        getOpMode().telemetry.addData("rightVal", rightVal); ;
        getOpMode().telemetry.addData("leftVal", leftVal);

        if (servoComponent != null) {
            // update the position of the servo
            if (rightVal) {
                servoComponent.incrementServoTargetPosition(servoDelta);
            }

            if (leftVal) {
                servoComponent.incrementServoTargetPosition(-servoDelta);
            }
        }
        else {
            // update the position of the servo
            if (rightVal) {
                crServo.setPower(1);
            }
            else if (leftVal) {
                crServo.setPower(-1);
            }
            else {
                crServo.setPower(0);
            }
        }

    }


}
