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

    /**
    * Find the x-CoG of the largest detected particle
    * @return x x-CoG
    */
    public double findTarget() throws NIVisionException, AxisCameraException {  

        try {

            criteria.addCriteria(MeasurementType.IMAQ_MT_AREA, 5, 400, false);  //minimum size of a particle

            //Filtering
            ColorImage image;
            image = camera.getImage();  //store the image from the camera
            BinaryImage thresholdImage = image.thresholdRGB(100, 255, 0, 25, 0, 25);  //filter out parts of image that do not possess a significant green component
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 4);  //filter out red objects too small to be the target
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);  //fill in occluded rectangles
            BinaryImage filteredImage = convexHullImage.particleFilter(criteria);  //apply the criteria defined above

            ParticleAnalysisReport[] particles = filteredImage.getOrderedParticleAnalysisReports();  //creates an array of particle analysis reports

            filteredImage.free();  //delete all images used above
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();

            //Scoring
            double rectangularity = (particles[0].boundingRectArea / particles[0].particleArea) * 100; //Check these method calls
            if(rectangularity > 80.0) {
                x = particles[0].center_mass_x_normalized;  //store the CoG of the largets particle
            } else {
                x = 0.0;
                System.out.println("No particles acquired");
            }
            
        } catch (NIVisionException e) {

            e.printStackTrace();

        } catch (AxisCameraException e) {

            e.printStackTrace();

        }

        return x;
    }
}
