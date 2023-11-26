package edu.hw7.PIAprox;

import java.security.SecureRandom;

public class ApThread extends Thread {

    private final static double HALFSIDE = 0.5;
    private Circle circle = new Circle(
        new Dot(HALFSIDE, HALFSIDE), HALFSIDE
    );

    private long totalPoints;
    private long pointsInCircle;

    public ApThread(long totalPoints) {
        this.totalPoints = totalPoints;
        this.pointsInCircle = 0;
    }

    public long getPointsInCircle() {
        return pointsInCircle;
    }

    @Override
    public void run() {
        var random = new SecureRandom();
        Dot randomPoint;

        for (int i = 0; i < totalPoints; i++) {
            randomPoint = new Dot(
                random.nextDouble(),
                random.nextDouble()
            );

            if (inCircle(randomPoint)) {
                pointsInCircle++;
            }
        }
    }

    private boolean inCircle(Dot point2D) {
        return Math.pow(circle.center().x() - point2D.x(), 2.0)
            + Math.pow(circle.center().y() - point2D.y(), 2.0)
            <= Math.pow(circle.rad(), 2.0);
    }
}
