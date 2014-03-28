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

    private double acceptableRange = 0.05;  //The distance to the left or right of the target in inches that is acceptable
    private Servo motor = new Servo(1, 1);  //The Servo that controls the rotating motor that aligns with the target
    private TargetLocator targetLocator = new TargetLocator(); //Will locate the target

    public void trackTheTarget() {  //Rotates the motor aligning with the target.  If the camera is too far to the left, it roates the motor right, and vice versa

        if (targetLocator.findTarget() > acceptableRange) {  //if the target is too far to the right

            motor.set(1.0);
            dirverStation.print("Turning right...", 1);

        } else if (targetLocator.findTarget() < -acceptableRange) {  //if the target is too far to the left

            motor.set(0.0);
            dirverStation.print("Turning left...", 1);
            
        } else if (targetLocator.findTarget() < acceptableRange && targetLocator.findTarget() > -acceptableRange) {  //If the camera is aligned

            motor.set(motor.get());
            dirverStation.print("Aligned!", 1);

        } else if(targetLocator.findTarget() == 0.0) { //If nothing is found
            
            dirverStation.print("No target", 1);

        }
        

    }
}
