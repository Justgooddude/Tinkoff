package edu.project4.preobr;

import edu.project4.customTypes.Point;
import java.util.Random;

public class AffinePreobr implements PreobrPattern {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    @Override
    public Point apply(Point point) {
        double newx = a * point.x() + b * point.y() + c;
        double newy = d * point.x() + e * point.y() + f;

        return new Point(newx, newy);
    }

    public AffinePreobr(
        double a, double b, double c,
        double d, double e, double f
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public static AffinePreobr randomPreob() {
        Random rand = new Random();
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        do {
            do {
                a = rand.nextDouble(-1, 1);
                d = rand.nextDouble(a * a, 1);

                if (rand.nextBoolean()) {
                    d = -d;
                }
            } while ((a * a + d * d) > 1);
            do {
                b = rand.nextDouble(-1, 1);
                e = rand.nextDouble(b * b, 1);

                if (rand.nextBoolean()) {
                    e = -e;
                }
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e)
            > (1 + (a * e - d * b) * (a * e - d * b)));

        c = rand.nextDouble(-2, 2);
        f = rand.nextDouble(-2, 2);

        return new AffinePreobr(a, b, c, d, e, f);
    }
}
