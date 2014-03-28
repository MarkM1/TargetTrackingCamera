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
    
    public void print(String message, int lineNumber) {  //print a given message to the driver station
        
        int line = lineNumber;
        switch(line)
            case(1):
            msg.println(DriverStationLCD.Line.kUser1, 1, message);
            break;
        
            case(2):
            msg.println(DriverStationLCD.Line.kUser1, 2, message);
            break;
            
            case(3):
            msg.println(DriverStationLCD.Line.kUser1, 3, message);
            break;
            
            case(4):
            msg.println(DriverStationLCD.Line.kUser1, 4, message);
            break;
            
            case(5):
            msg.println(DriverStationLCD.Line.kUser1, 5, message);
            break;
            
            case(6):
            msg.println(DriverStationLCD.Line.kUser1, 6, message);
            break;
    }
    
    public void update() {
        
        msg.updateLCD();
    }
    
    public void clear() {
        
        msg.clear();
    }
}
