package edu.project4.generators;

import edu.project4.customTypes.Color;
import edu.project4.customTypes.ImageSize;
import edu.project4.customTypes.MapBorders;
import edu.project4.customTypes.Pixel;
import edu.project4.customTypes.PixelMap;
import edu.project4.customTypes.Point;
import edu.project4.preobr.ColoredPreob;
import edu.project4.preobr.PreobrPattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThread implements Gener {
    private final static int PRE_STEPS = 25;

    @Override
    public PixelMap gener(
        ImageSize imageSize,
        MapBorders borders,
        List<PreobrPattern> variations,
        List<ColoredPreob> affine,
        int samples,
        int iterations
    ) {
        Random rand = ThreadLocalRandom.current();
        double newX;
        double newY;
        Map<Point, Pixel> flame = new HashMap<>();
        long tran = 0;

        for (int i = 0; i < samples; i++) {
            newX = rand.nextDouble(borders.xMin(), borders.xMax());
            newY = rand.nextDouble(borders.yMin(), borders.xMax());

            for (int step = -PRE_STEPS; step < iterations; step++) {
                ColoredPreob chosenAffine = affine.get(rand.nextInt(affine.size()));
                PreobrPattern transformation = variations.get((int) (tran % variations.size()));
                tran++;

                Point newPoint = transformation.apply(
                    chosenAffine.preob().apply(new Point(newX, newY))
                );

                newX = newPoint.x();
                newY = newPoint.y();

                if (checkPixel(step, newPoint, borders)) {
                    int x = resize(imageSize.width(), borders.xMin(), borders.xMax(), newPoint.x());
                    int y = resize(imageSize.height(), borders.yMin(), borders.yMax(), newPoint.y());

                    if (x < imageSize.width() && y < imageSize.height()) {
                        if (!flame.containsKey(new Point(x, y))) {
                            flame.put(new Point(x, y), new Pixel(chosenAffine.color(), 1));
                        } else {
                            Pixel old = flame.get(new Point(x, y));
                            flame.put(
                                new Point(x, y),
                                new Pixel(mergePixels(old.color(), chosenAffine.color()), old.counter() + 1)
                            );
                        }
                    }
                }
            }
        }
        return new PixelMap(
            flame,
            imageSize.width(),
            imageSize.height()
        );

    }

    private boolean checkPixel(int step, Point point, MapBorders borders) {
        return step >= 0
            && point.x() >= borders.xMin() && point.x() <= borders.xMax()
            && point.y() >= borders.yMin() && point.y() <= borders.yMax();
    }

    private int resize(int size, double min, double max, double point) {
        return size - (int) Math.ceil(
            (max - point) / (max - min) * size
        );
    }

    private Color mergePixels(Color first, Color second) {
        return new Color(
            (first.r() + second.r()) / 2,
            (first.g() + second.g()) / 2,
            (first.b() + second.b()) / 2
        );
    }

}
