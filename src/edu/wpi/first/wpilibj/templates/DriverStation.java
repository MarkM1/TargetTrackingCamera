package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * This class handles all printing to the Driver Station Screen
 *
 * @author (Mark Macerato, Team 3167)
 * @version (March 2, 2014)
 */
class DriverStation {

    private final DriverStationLCD msg = DriverStationLCD.getInstance();  //The Driver Station
    
    /**
     * Print a message indicating that code is loaded on the robot
     */
    public void printReadyMessage() {  
        
        msg.clear();
        msg.println(DriverStationLCD.Line.kUser1, 1, "The Robot is Ready To Rock and Roll!");
        System.out.println("The Robot is Ready To Rock and Roll!");
        msg.updateLCD();
    }
    
    public void print(String message) {  //print a given message to the driver station
        
        msg.println(DriverStationLCD.Line.kUser1, 1, message);
        
    }
    
    public void update() {
        
        msg.updateLCD();
    }
    
    public void clear() {
        
        msg.clear();
    }
}
