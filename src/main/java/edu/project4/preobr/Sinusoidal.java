package edu.project4.preobr;

import edu.project4.customTypes.Point;

public class Sinusoidal implements PreobrPattern{
  @Override
  public Point apply(Point point){
      double newx = Math.sin(point.x());
      double newy = Math.sin(point.y());
      return new Point(newx,newy);
  }
}
