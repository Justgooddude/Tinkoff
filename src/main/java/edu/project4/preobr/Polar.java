package edu.project4.preobr;

import edu.project4.customTypes.Point;

public class Polar implements PreobrPattern{
    @Override
    public Point apply(Point point){
        double newx = arctan(point)/Math.PI;
        double newy = radius(point) - 1;
        return new Point(newx,newy);
    }
}
