package org.firstinspires.ftc.teamcode.playarea;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name="Rahul Test FTC Dashboard", group="Linear OpMode")
public class RahulTestFtcDashboard extends LinearOpMode {
    public static int FRONT_LEFT_MOTOR_RUN_TO_POSITION = 2000;

    public static int FRONT_LEFT_MOTOR_SLEEP_TIME_AT_POSITION = 3000;

    public static double FRONT_LEFT_MOTOR_POWER = 0.5;

    DcMotorEx frontLeftMotor; // Declare OpMode member for the Odometry Computer

    @Override 
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        frontLeftMotor = hardwareMap.get(DcMotorEx.class,"frontLeft");
        telemetry.addData("Status", "Initializing Completed");
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        ElapsedTime runtime = new ElapsedTime();
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            frontLeftMotor.setTargetPosition(FRONT_LEFT_MOTOR_RUN_TO_POSITION);
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setPower(FRONT_LEFT_MOTOR_POWER);
            while(frontLeftMotor.isBusy())
            {
                telemetry.addData("Current Position", "Forward at %d at %f", frontLeftMotor.getCurrentPosition(), runtime.milliseconds());
                telemetry.update();
            }

            telemetry.addData("Status", "Sleeping after extending to %d for %d", frontLeftMotor.getCurrentPosition(), FRONT_LEFT_MOTOR_SLEEP_TIME_AT_POSITION);
            telemetry.update();
            sleep(FRONT_LEFT_MOTOR_SLEEP_TIME_AT_POSITION);

            frontLeftMotor.setTargetPosition(0);
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setPower(FRONT_LEFT_MOTOR_POWER);
            while(frontLeftMotor.isBusy())
            {
                telemetry.addData("Current Position", "Rewinding at %d at %f", frontLeftMotor.getCurrentPosition(), runtime.milliseconds());
                telemetry.update();
            }

            telemetry.addData("Status", "Sleeping after rewinding to %d for %d", frontLeftMotor.getCurrentPosition(), FRONT_LEFT_MOTOR_SLEEP_TIME_AT_POSITION);
            telemetry.update();
            sleep(FRONT_LEFT_MOTOR_SLEEP_TIME_AT_POSITION);
        }
    }
}