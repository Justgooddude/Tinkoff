package edu.project4.preobr;

import edu.project4.customTypes.Point;

public class Disk implements PreobrPattern {
    @Override
    public Point apply(Point point) {
        double newx = 1 / Math.PI * arctan(point) * Math.sin(Math.PI * radius(point));
        double newy = 1 / Math.PI * arctan(point) * Math.cos(Math.PI * radius(point));
        return new Point(newx, newy);
    }
}
