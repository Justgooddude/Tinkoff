package edu.project4.preobr;

import edu.project4.customTypes.Point;
import java.util.function.Function;

public interface PreobrPattern extends Function<Point, Point> {
    default double radius(Point point) {
        return Math.sqrt(point.x() * point.x() + point.y() * point.y());
    }

    default double radiusSquared(Point point) {
        return point.x() * point.x() + point.y() * point.y();
    }

    default double arctan(Point point) {
        return Math.atan(point.y() / point.x());
    }
}
