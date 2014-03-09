package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * TargetLocator determines the location of the target relative to the camera.
 * An image from the camera is stored on the cRio flash memory, and then
 * filtered to eliminate everything except potential targets. Then, a particle
 * analysis report uses criteria defined in this class to determine all
 * particles on the screen. Finally, the positions of the centers of these
 * particles is printed to the console.
 *
 *
 * @author (Mark Macerato, Team 3167)
 * @version (March 2, 2014)
 */
class TargetLocator {

    AxisCamera camera = AxisCamera.getInstance("10.31.67.11");  //create an instance of the camera
    CriteriaCollection criteria = new CriteriaCollection();  //criteria for what constitutes a particle
    double x;

    public double findTarget() throws NIVisionException, AxisCameraException {  //determines the x-positions of the centers of mass of all particles

        try {

            criteria.addCriteria(MeasurementType.IMAQ_MT_AREA, 5, 400, false);  //minimum size of a particle

            ColorImage image;
            image = camera.getImage();  //store the image from the camera
            BinaryImage thresholdImage = image.thresholdRGB(0, 0, 67, 255, 0, 111);  //filter out parts of image that do not possess a significant green component
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 4);  //filter out green objects too small to be the target
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);  //fill in occluded rectangles
            BinaryImage filteredImage = convexHullImage.particleFilter(criteria);  //apply the criteria defined above

            ParticleAnalysisReport[] reportArray = filteredImage.getOrderedParticleAnalysisReports();  //creates an array of particle analysis reports

            filteredImage.free();  //delete all images used above
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();

//          double x1 = reportArray[1].center_mass_x_normalized; //return center of mass of one bar
//          double x2 = reportArray[2].center_mass_x_normalized; //return center of mass of the other
            x = reportArray[1].center_mass_x;  //take the average of both

        } catch (NIVisionException e) {

            e.printStackTrace();

        } catch (AxisCameraException e) {

            e.printStackTrace();

        }

        return x;
    }
}
