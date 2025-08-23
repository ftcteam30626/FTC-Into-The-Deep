package org.firstinspires.ftc.teamcode.playarea;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.tuning.roadrunner.GoBildaPinpointDriver;

@Autonomous(name="Rahul Test Odometry Setup", group="Linear OpMode")
public class RahulTestOdometrySetup extends LinearOpMode {

    GoBildaPinpointDriver odometry; // Declare OpMode member for the Odometry Computer

    @Override
    public void runOpMode() {

        odometry = hardwareMap.get(GoBildaPinpointDriver.class,"odometry");
        odometry.setOffsets(-84.0, -168.0, DistanceUnit.MM);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odometry.resetPosAndIMU();

        telemetry.addData("Status", "Initialized Robot");
        telemetry.addData("Status", odometry.getDeviceStatus());
        telemetry.addData("Device Id", odometry.getDeviceID());
        telemetry.addData("Device Name", odometry.getDeviceName());
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", odometry.getDeviceStatus());
            telemetry.addData("X offset", odometry.getXOffset(DistanceUnit.MM));
            telemetry.addData("Y offset", odometry.getYOffset(DistanceUnit.MM));
            telemetry.addData("Device Version Number:", odometry.getDeviceVersion());
            telemetry.addData("Heading Scalar", odometry.getYawScalar());
            telemetry.update();
        }
    }
}