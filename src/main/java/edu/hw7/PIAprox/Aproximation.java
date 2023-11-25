package edu.hw7.PIAprox;

import java.security.SecureRandom;

public final class Aproximation {
    private Aproximation() {
    }

    private static Circle circle = new Circle(
        new Dot(0.5, 0.5), 0.5
    );
    private final static double CONST = 4.0;

    public static double singleThreadApproximate(long totalPoints) {
        var random = new SecureRandom();
        Dot randomPoint;

        long pointsInCircleCount = 0;
        for (int i = 0; i < totalPoints; i++) {
            randomPoint = new Dot(
                random.nextDouble(),
                random.nextDouble()
            );

            if (inCircle(randomPoint)) {
                pointsInCircleCount++;
            }
        }

        return calculate(totalPoints, pointsInCircleCount);
    }

    public static double multiThreadApproximate(int threadsNumber, long totalPoints) {
        long pointsInCircle = 0;
        long pointsNumberInThread = totalPoints / threadsNumber;

        var threads = new ApThread[threadsNumber];

        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new ApThread(pointsNumberInThread);
            threads[i].start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }

            pointsInCircle += thread.getPointsInCircle();
        }

        return calculate(totalPoints, pointsInCircle);
    }

    private static boolean inCircle(Dot point2D) {
        return Math.pow(circle.center().x() - point2D.x(), 2.0)
            + Math.pow(circle.center().y() - point2D.y(), 2.0)
            <= Math.pow(circle.rad(), 2.0);
    }

    private static double calculate(long totalPoints, long pointsInCircle) {
        return CONST * ((double) pointsInCircle / totalPoints);
    }
}
