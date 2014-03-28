package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Servo;

/**
 * This class rotates the motor in the direction of the target, based on the
 * relative location of the target as determined by TargetLocator
 *
 * @author (Mark Macerato, Team 3167)
 * @version (March 2, 2014)
 */
class Motor {

    private double acceptableRange;  //The distance to the left or right of the target in inches that is acceptable
    private Servo motor = new Servo(1, 1);  //The Servo that controls the rotating motor that aligns with the target

    public void trackTheTarget() {  //Rotates the motor aligning with the target.  If the camera is too far to the left, it roates the motor right, and vice versa

        if (TargetLocator.getRelPositionOfTarget() > acceptableRange) {  //if the target is too far to the right

            motor.set(1.0);

        } else if (TargetLocator.getRelPositionOfTarget() < -acceptableRange) {  //if the target is too far to the left

            motor.set(0.0);

        } else if (TargetLocator.getRelPositionOfTarget() < acceptableRange && TargetLocator.getRelPositionOfTarget() > -acceptableRange) {  //If the camera is aligned

            motor.set(motor.get());

        }

    }
}
