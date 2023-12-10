package edu.project4.generators;

import edu.project4.customTypes.ImageSize;
import edu.project4.customTypes.MapBorders;
import edu.project4.customTypes.PixelMap;
import edu.project4.preobr.ColoredPreob;
import edu.project4.preobr.PreobrPattern;
import java.util.List;

@FunctionalInterface
public interface Gener {
    PixelMap gener(
        ImageSize imageSize,
        MapBorders mapBorders,
        List<PreobrPattern> preobr,
        List<ColoredPreob>affine,
        int samples,
        int iter
        );
}
