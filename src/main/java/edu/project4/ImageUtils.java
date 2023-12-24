package edu.project4;

import edu.project4.customTypes.FractalImage;
import edu.project4.customTypes.ImageType;
import edu.project4.customTypes.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageType format) throws IOException {
        ImageIO.write(createImage(image), format.name().toLowerCase(), filename.toFile());
    }

    private static BufferedImage createImage(FractalImage image) {
        var bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        int[] rgbData = new int[image.width() * image.height()];

        for (int row = 0; row < image.height(); ++row) {
            for (int col = 0; col < image.width(); ++col) {
                Pixel pixel = image.getPixel(col, row);
                rgbData[row * image.width() + col] =
                    new Color(pixel.color().r(), pixel.color().g(), pixel.color().b()).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, image.width(), image.height(), rgbData, 0, image.width());
        return bufferedImage;
    }
}
