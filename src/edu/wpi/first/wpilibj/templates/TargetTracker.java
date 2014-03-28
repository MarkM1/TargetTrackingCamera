/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * This code allows a motor with a mounted FRC camera
 * controlled by a Jaguar speed controller to track a reflective tape target in
 * an "L" shape, as on the field of the 2014 FRC game.
 *
 * @author (Mark Macerato, Team 3167)
 * @version (March 2, 2014)
 */
public class TargetTracker extends IterativeRobot {

    private DriverStation driverStation = new DriverStation();  //Controls printing to Driver Station LCD
    private Motor targetTracker = new Motor();  //Controls position of motor

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void disabledInit() {

        driverStation.printReadyMessage();

    }

    public void robotInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        driverStation.clear();
        targetTracker.trackTheTarget();
        driverStation.update();

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
