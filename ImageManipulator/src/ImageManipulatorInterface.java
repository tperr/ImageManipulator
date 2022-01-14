// BE SURE TO COMMENT YOUR CODE
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritableImage;
import java.io.FileNotFoundException;

/**
 * Public API for ImageManipulator.
 *
 * You must implement this interface in your Program3 class.
 *
 * @author Leo Ureel
 */
public interface ImageManipulatorInterface {

   /**
    * Load the specified PPM image file.
    * The image file must be in the PPM P3 format
    * @link http://netpbm.sourceforge.net/doc/ppm.html
    *
    * Don't forget to add a load button to the application!
    *
    * @param filename
    * @return WritableImage
    * @throws FileNotFoundException
    */
   public WritableImage loadImage(String filename) throws FileNotFoundException, Exception;

   /**
    * Save the specified image to a PPM file.
    * The image file must be in the PPM P3 format
    * @link http://netpbm.sourceforge.net/doc/ppm.html
    *
    * Don't forget to add a save button to the application!
    *
    * @param filename
    * @param image
    * @throws FileNotFoundException
    */
   public void saveImage(String filename, WritableImage image) throws FileNotFoundException;

   /**
    * Invert an image by subtracting each RGB component from its max value
    *
    * For example:
    *
    * @param image - the image to be inverted, do not modify!
    * @return a new inverted image
    */
   public WritableImage invertImage(WritableImage image);

   /**
    * Convert an image to grayscale using the following formula:
    * intensity = 0.2989*red + 0.5870*green + 0.1140*blue
    * new rgb( intensity, intensity, intensity );
    *
    * @param image - the image to be converted to grayscale, do not modify!
    * @return a new image that displays in shades of gray
    */
   public WritableImage grayifyImage(WritableImage image);

    /**
     * Pixelates the image by dividing it into 5x5 regions, then assigning
     * all pixels in the region the same color as the central pixel.
     *
     * For example:
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     * [0,0,0] [5,5,5] [5,5,5] [5,5,5] [0,0,0]
     * [0,0,0] [5,5,5] [1,2,3] [5,5,5] [0,0,0]
     * [0,0,0] [5,5,5] [5,5,5] [5,5,5] [0,0,0]
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     *
     * is pixelated to
     *
     * [1,2,3] [1,2,3] [1,2,3] [1,2,3] [1,2,3]
     * [1,2,3] [1,2,3] [1,2,3] [1,2,3] [1,2,3]
     * [1,2,3] [1,2,3] [1,2,3] [1,2,3] [1,2,3]
     * [1,2,3] [1,2,3] [1,2,3] [1,2,3] [1,2,3]
     * [1,2,3] [1,2,3] [1,2,3] [1,2,3] [1,2,3]
     *
     * @param image - the image to be converted to grayscale, do not modify!
     * @return a new image that displays in shades of gray
     */
    public WritableImage pixelateImage(WritableImage image);

    /**
     * Flips the image vertically.
     *
     * For example:
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     * [0,0,0] [5,5,5] [1,4,7] [5,5,5] [0,0,0]
     * [0,0,0] [1,2,3] [2,5,8] [1,2,3] [0,0,0]
     * [0,0,0] [4,4,4] [3,6,9] [4,4,4] [0,0,0]
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     *
     * is flipped to
     *
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     * [0,0,0] [4,4,4] [3,6,9] [4,4,4] [0,0,0]
     * [0,0,0] [1,2,3] [2,5,8] [1,2,3] [0,0,0]
     * [0,0,0] [5,5,5] [1,4,7] [5,5,5] [0,0,0]
     * [0,0,0] [0,0,0] [0,0,0] [0,0,0] [0,0,0]
     *
     * @param image - the image to be flipped, do not modify!
     * @return a new image that displays upside-down (but not rotated!)
     */
    public WritableImage flipImage(WritableImage image);
}
