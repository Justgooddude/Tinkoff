package edu.project4.customTypes;

import java.util.Map;

public record PixelMap(Map<Point, Pixel> map, int width, int height) {
}
