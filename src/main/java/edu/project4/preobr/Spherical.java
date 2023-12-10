package edu.project4.preobr;

import edu.project4.customTypes.Point;

public class Spherical implements PreobrPattern{
    @Override
    public Point apply(Point point){
        double newx = point.x()/radiusSquared(point);
        double newy = point.y()/radiusSquared(point);
        return new Point(newx,newy);
    }
}
