import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import javax.xml.bind.TypeConstraintException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * This program loads and manipulates files saved as a .ppm format
 * Date last modified: 12/9/21
 * @author Tim Perr, Connor Johnson, Johnny Burns, Zach Frikken
 *
 * CS1131 Fall
 * Section LO3F
 */

public class ImageManipulator extends Application implements ImageManipulatorInterface{

    private int imgWidth;  // image width
    private int imgHeight;  // image height
    private WritableImage img;  // image thats being changed/manipulated


    /**
     * Required by application, runs the program
     * @param primaryStage - primary stage, given by run
     * @throws Exception - throws if file is not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        img = loadImage(!getParameters().getRaw().isEmpty() ? getParameters().getRaw().get(0) : "baboon.ppm");

        Button load = new Button("Load");  // load button
        Button save = new Button("Save");  // save button
        Button flip = new Button("Flip");  // flip button
        Button invert = new Button("Invert");  // invert button
        Button grayscale = new Button("Grayscale");  // grayscale button
        Button pixelate = new Button("Pixelate");  // pixelate button

        FlowPane buttons = new FlowPane();  // flowpane containing buttons
        buttons.setVgap(40);
        buttons.setHgap(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(load, save, flip, invert, grayscale, pixelate);
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(buttons);
        borderPane.setCenter(new ImageView(img));

        load.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PPM Images (*.ppm)", "*.ppm"));
            File file = fc.showOpenDialog(primaryStage);
            try {
                img = loadImage(file.getName());
                borderPane.setCenter(new ImageView(img));
                primaryStage.setHeight(img.getHeight() + 100);
                primaryStage.setWidth(img.getWidth());

            } catch (Exception e) {
                // does nothing
            }
        });
        save.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PPM Images (*.ppm)", "*.ppm"));
            File file = fc.showSaveDialog(primaryStage);
            try {
                saveImage(file.getName(), img);
            } catch (Exception e) {
                // does nothing
            }
        });
        flip.setOnAction(event -> {

            img = flipImage(img);
            borderPane.setCenter(new ImageView(img));

        });
        invert.setOnAction(event -> {
            img = invertImage(img);
            borderPane.setCenter(new ImageView(img));

        });
        grayscale.setOnAction(event -> {

            img = grayifyImage(img);
            borderPane.setCenter(new ImageView(img));

        });
        pixelate.setOnAction(event -> {

            img = pixelateImage(img);
            borderPane.setCenter(new ImageView(img));

        });

        Scene scene = new Scene(borderPane);  // primary scene
        primaryStage.setTitle("Program 3 Image Manipulator-inator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Load the specified PPM image file.
     * The image file must be in the PPM P3 format
     * @see <a href="http://netpbm.sourceforge.net/doc/ppm.html">http://netpbm.sourceforge.net/doc/ppm.html</a>
     *
     * @param filename - string of the file needed to load
     * @return the loaded image
     * @throws FileNotFoundException - throws when file is not fount
     */
    @Override
    public WritableImage loadImage(String filename) throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File(filename));  // scanner for the file

        if (!fileScanner.nextLine().equals("P3"))
        {
            throw new TypeConstraintException("File type must be P3");
        }

        fileScanner.nextLine();

        imgWidth = fileScanner.nextInt();
        imgHeight = fileScanner.nextInt();

        fileScanner.nextLine();

        if (fileScanner.nextInt() != 255) {
            throw new TypeConstraintException("Constraint must be 255");
        }


        img = new WritableImage(imgWidth, imgHeight);

        PixelWriter pw = img.getPixelWriter();  // pixelwriter for the image, to write individual pixels on the image

        for (int y = 0; y < imgHeight; y++)
        {
            for (int x = 0; x < imgWidth; x++) {

                int r = fileScanner.nextInt();  // red
                int g = fileScanner.nextInt();  // green
                int b = fileScanner.nextInt();  // blue

                Color c = Color.rgb(r, g, b);  // new color containing previous red green blue
                pw.setColor(x, y, c);
            }
        }




        return img;
    }

    /**
     * Save the specified image to a PPM file.
     * The image file must be in the PPM P3 format
     * @see <a href="http://netpbm.sourceforge.net/doc/ppm.html">http://netpbm.sourceforge.net/doc/ppm.html</a>
     *
     * @param filename - destination save file
     * @param image - image to be saved
     * @throws FileNotFoundException - throws when the file is not found
     */
    @Override
    public void saveImage(String filename, WritableImage image) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);  // file printwriter
        PixelReader pr = image.getPixelReader();  // image pixelreader
        pw.println("P3");
        pw.println("# " + filename);

        imgWidth = (int) image.getWidth();
        imgHeight = (int) image.getHeight();

        pw.println(imgWidth + " " + imgHeight);


        pw.println(255);

        for (int y = 0; y < imgHeight; y++)
        {
            for (int x = 0; x < imgWidth; x++)
            {
                Color c = pr.getColor(x, y);

                pw.println((int) (c.getRed() * 255) + "\n" + (int) (c.getGreen() * 255) + "\n" + (int) (c.getBlue() * 255));

            }
        }

        pw.close();

    }
    /**
     * Invert an image by subtracting each RGB component from its max value
     *
     * @param image - the image to be inverted
     * @return a new inverted image
     */
    @Override
    public WritableImage invertImage( WritableImage image ) {
        WritableImage inverted = new WritableImage((int)image.getWidth(),(int)image.getHeight());  // new image
        PixelReader pixelReader=image.getPixelReader();  // image pixel reader
        PixelWriter pixelWriter=inverted.getPixelWriter();  // new image pixel writer
        for(int x=0;x<(int)inverted.getWidth();x++){
            for(int y=0;y<(int)inverted.getHeight();y++){
                pixelWriter.setColor(x,y,pixelReader.getColor(x,y).invert());
            }
        }
        return inverted;
    }
    /**
     * Convert an image to grayscale using the following formula:
     * intensity = 0.2989*red + 0.5870*green + 0.1140*blue
     * new rgb( intensity, intensity, intensity );
     *
     * @param image - the image to be converted to grayscale
     * @return a new image that displays in shades of gray
     */

    @Override
    public WritableImage grayifyImage(WritableImage image) {
        WritableImage grayify = new WritableImage((int)image.getWidth(),(int)image.getHeight());  // new greyed image
        PixelReader pixelReader=image.getPixelReader();  // image pixel reader
        PixelWriter pixelWriter=grayify.getPixelWriter();  // greyed pixel writer
        for(int x=0;x<(int)grayify.getWidth();x++){
            for(int y=0;y<(int)grayify.getHeight();y++){
                pixelWriter.setColor(x,y,greyscale(pixelReader.getColor(x,y)));
            }
        }
        return grayify;
    }

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
     * @param image - the image to be pixelated
     * @return a new image that is pixelated
     */
    @Override
    public WritableImage pixelateImage(WritableImage image) {
        PixelReader pr = image.getPixelReader();  // image pixel reader
        imgWidth = (int) image.getWidth();
        imgHeight = (int) image.getHeight();

        WritableImage pixelated = new WritableImage(imgWidth, imgHeight);  // new pixelated image
        PixelWriter pw = pixelated.getPixelWriter();  // pixelated image pixel writer

        for (int y = 2; y < imgHeight; y+=5)
        {
            for (int x = 2; x < imgWidth; x+=5)
            {
                for (int yy = y-2; yy <= y+2; yy++)
                {
                    for (int xx = x-2; xx <= x+2; xx++)
                    {
                        pw.setColor(xx, yy, pr.getColor(x, y));
                    }
                }
            }

        }

        return pixelated;
    }

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
     * @param image - the image to be flipped
     * @return a new image that displays upside-down
     */
    @Override
    public WritableImage flipImage(WritableImage image) {
        WritableImage flipped = new WritableImage((int)image.getWidth(),(int)image.getHeight());  // new flipped image
        PixelReader pixelReader=image.getPixelReader();  // original image pixel reader
        PixelWriter pixelWriter=flipped.getPixelWriter();  // flipped pixel writer
        for(int x=0;x<(int)flipped.getWidth();x++){
            for(int y=0;y<(int)flipped.getHeight();y++){
                pixelWriter.setColor(x,y,pixelReader.getColor(x,(int)flipped.getHeight()-1-y));
            }
        }
        return flipped;
    }


    /**
     * Changes a color to its greyscale equivalent
     *
     * @param color - color to be converted to greyscale
     * @return color but greyed
     */
    private Color greyscale(Color color){
        int average= (int) ((color.getRed()*0.2989+color.getGreen()*0.5870+color.getBlue()*0.1140)*255);
        return Color.rgb(average,average,average);

    }
}
