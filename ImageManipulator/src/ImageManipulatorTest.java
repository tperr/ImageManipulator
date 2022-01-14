import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.swing.plaf.synth.SynthDesktopIconUI;

public class ImageManipulatorTest {
    //    String path = "/opt/submit/CanvasTA/1158458/submit/6196910/data/";
    String path = ""; // Assume Java working folder

    public class FileFormatException extends Exception {
        public static final long serialVersionUID = 42L;
    }

    @Test
    public void implementsInterfaceTest() {
        ImageManipulator lab = new ImageManipulator();
        if (!(lab instanceof ImageManipulatorInterface)) {
            fail("Class:ImageManipulator does not implement Interface:ImageManipulatorInterface");
        }
    }

    @Test
    public void pixelateTest5x5() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "test1.ppm";
        String filename2 = "test1sol.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.pixelateImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("pixelateImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void invertTest5x5() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "test2.ppm";
        String filename2 = "test2sol.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.invertImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("invertImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void grayifyTest5x5() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "test3.ppm";
        String filename2 = "test3sol.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.grayifyImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("grayifyImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void pixelateTest() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "baboon.ppm";
        String filename2 = "baboon_pixelated.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.pixelateImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("pixelateImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void invertTest() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "baboon.ppm";
        String filename2 = "baboon_inverted.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.invertImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("invertImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void grayifyTest() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "baboon.ppm";
        String filename2 = "baboon_gray.ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = lab.grayifyImage(source);
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("grayifyImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void loadTest() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "baboon.ppm";
        String filename2 = "baboon.ppm";
        WritableImage source = null;
        try {
            source = lab.loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage result = source;
        WritableImage solution = null;
        try {
            solution = loadImage(path + filename2);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                    filename2));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( \"%s\" ): FileFormatException",
                    filename2));
        }
        if (solution == null) {
            fail(String.format("loadImage( %s ): Returned null", filename2));
        }
        if (!compareImages(result, solution)) {
            fail(String.format("invertImage( %s ): result:%s != solution:%s",
                    filename, stringifyImage(result),
                    stringifyImage(solution)));
        }
    }

    @Test
    public void saveTest() {
        ImageManipulator lab = new ImageManipulator();
        String filename = "baboon.ppm";
        String filename2 = "SAVETESTbaboon." + System.nanoTime() + ".ppm";
        WritableImage source = null;
        try {
            source = loadImage(path + filename);
        } catch (FileNotFoundException e) {
            fail(String.format("loadImage( %s ): FileNotFoundException",
                    path + filename));
        } catch (FileFormatException e) {
            fail(String.format("loadImage( %s ): FileFormatException",
                    path + filename));
        }
        if (source == null) {
            fail(String.format("loadImage( %s ): Returned null",
                    path + filename));
        }
        WritableImage solution = source;
        File saveFile = new File(path + filename2);
        try {
            try {
                System.out.println("");
                lab.saveImage(path + filename2, source);
            } catch (Exception e) {
                e.printStackTrace ();
                fail("saveImage(" + filename2 + ") failed: " + e.getMessage());
            }
            WritableImage result = null;
            try {
                result = loadImage(path + filename2);
            } catch (FileNotFoundException e) {
                fail(String.format("loadImage( \"%s\" ): FileNotFoundException",
                        filename2));
            } catch (FileFormatException e) {
                saveFile.delete();
                e.printStackTrace();
                fail(String.format("loadImage( \"%s\" ): FileFormatException",
                        filename2));
            }
            if (solution == null) {
                saveFile.delete();
                fail(String.format("loadImage( %s ): Returned null", filename2));
            }
            if (!compareImages(result, solution)) {
                saveFile.delete();
                fail(String.format("saveImage( %s ): result:%s != solution:%s",
                        filename, stringifyImage(result),
                        stringifyImage(solution)));
            }
        } finally {
            saveFile.delete();
        }
    }

    public boolean compareImages(WritableImage i1, WritableImage i2) {
        PixelReader p1 = i1.getPixelReader();
        PixelReader p2 = i2.getPixelReader();
        if (i1.getWidth() != i2.getWidth()
                || i1.getHeight() != i2.getHeight()) {
            return false;
        }
        for (int x = 0; x < i1.getWidth(); x++) {
            for (int y = 0; y < i1.getHeight(); y++) {
                int c1 = p1.getArgb(x,y); //  .getRGB(x, y);
                int c2 = p2.getArgb(x,y); // .getRGB(x, y);
                if (c1 != c2) {
                    return false;
                }
            }
        }
        return true;
    }

    public String stringifyImage(WritableImage i1) {
        return String.format("<IMAGE:%s w=%s h=%s>", i1.hashCode(), i1.getWidth(), i1.getHeight());
    }

    public WritableImage loadImage(String filename)
            throws FileNotFoundException,
            FileFormatException {
        Scanner scanner = new Scanner(new File(filename));
        WritableImage image = null;
        try {
            String magicNumber = scanner.next(); // Should be P3
            if (!magicNumber.equals("P3")) {
                throw new FileFormatException();
            }
            scanner.nextLine();
            while (scanner.hasNext() && !scanner.hasNextInt()) {
                String foo = scanner.nextLine();
                if (!(foo.trim().isEmpty() || foo.startsWith("#"))) {
                    throw new FileFormatException();
                }
            }
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            while (scanner.hasNext() && !scanner.hasNextInt()) {
                String foo = scanner.nextLine();
                if (!(foo.trim().isEmpty() || foo.startsWith("#"))) {
                    throw new FileFormatException();
                }
            }
            int colorScale = scanner.nextInt(); // Should be 255
            if (colorScale != 255) {
                throw new FileFormatException();
            }
            image = new WritableImage(width, height);
            PixelWriter pxlwtr = image.getPixelWriter();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    while (scanner.hasNext() && !scanner.hasNextInt()) {
                        String foo = scanner.nextLine();
                        if (!(foo.trim().isEmpty() || foo.startsWith("#"))) {
                            throw new FileFormatException();
                        }
                    }


                    int red = scanner.nextInt();
                    int green = scanner.nextInt();
                    int blue = scanner.nextInt();
                    Color c = Color.rgb(red, green, blue);
                    pxlwtr.setColor(x, y, c);

                }
            }
        } catch (FileFormatException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileFormatException();
        }

        return image;
    }
}

