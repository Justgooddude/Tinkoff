package edu.project4.preobr;

import edu.project4.customTypes.Point;

public class Heart implements PreobrPattern{
    @Override
    public Point apply(Point point){
        double newx = radius(point)*Math.sin(radius(point)*arctan(point));
        double newy = -radius(point)*Math.cos(radius(point)*arctan(point));
        return new Point(newx,newy);
    }
}
