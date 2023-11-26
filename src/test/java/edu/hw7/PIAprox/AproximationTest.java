package edu.hw7.PIAprox;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Disabled
class AproximationTest {
    // Отключил,т.к слишком долгое. просто для примера того как считал
    private record Result(
        double averageTime,
        double averagedif
    ) {
    }

    @ParameterizedTest
    @ValueSource(longs = {
        10_000L,
        100_000L,
        1_000_000L,
        10_000_000L
    })
    public void performanceTests(long totalPointsNumber) {
        Result singleThread = singleThreadVersion(totalPointsNumber);

        for (int threadsNumber = 2;
             threadsNumber <= 6;
             threadsNumber += 2
        ) {
            Result multiThread = multiThreadVersion(threadsNumber, totalPointsNumber);
            printResultComparison(singleThread, multiThread, threadsNumber, totalPointsNumber);
        }
    }

    private Result singleThreadVersion(long totalPointsNumber) {
        double totalTime = 0.0;
        double totaldif = 0.0;

        for (int i = 0; i < 10L; ++i) {
            var startTime = System.nanoTime();
            double result = Aproximation
                .singleThreadApproximate(totalPointsNumber);
            var endTime = System.nanoTime();

            double dif = Math.abs(Math.PI - result);

            totalTime += (double) (endTime - startTime) / 1_000_000_000.0;
            totaldif += dif;
        }

        double averageTime = totalTime / 10L;
        double averagedif = totaldif / 10L;

        return new Result(averageTime, averagedif);
    }

    private Result multiThreadVersion(int threadsNumber, long totalPointsNumber) {
        double totalTime = 0.0;
        double totalDelta = 0.0;

        for (int i = 0; i < 10L; ++i) {
            var startTime = System.nanoTime();
            double result = Aproximation
                .multiThreadApproximate(threadsNumber, totalPointsNumber);
            var endTime = System.nanoTime();

            double delta = Math.abs(Math.PI - result);

            totalTime += (double) (endTime - startTime) / 1_000_000_000.0;
            totalDelta += delta;
        }

        double averageTime = totalTime / 10L;
        double averagedif = totalDelta / 10L;

        return new Result(averageTime, averagedif);
    }

    private void printResultComparison(
        Result singleThread, Result multiThread,
        int threadsNumber, long totalPointsNumber
    ) {
        System.out.println(
            "Сравнение для "
                + totalPointsNumber + " точек и "
                + threadsNumber + " потоков ("
                + 10L + " симуляций):"
        );
        System.out.println(
            "Один поток: среднее время в секундах — "
                + singleThread.averageTime()
        );
        System.out.println(
            "Многопотоковое: среднее время в секундах — "
                + multiThread.averageTime()
        );

        double averageTimeDif = Math.abs(
            multiThread.averageTime()
                - singleThread.averageTime()
        );
        double ratio =
            singleThread.averageTime()
                / multiThread.averageTime();

        System.out.printf("Разница: %.5f секунд%n", averageTimeDif);
        System.out.printf("Многопотоовое в %.2fx быстрее чем один поток %n", ratio);

        System.out.printf("Один поток: средняя разница с пи — %.5f%n", singleThread.averagedif());
        System.out.printf("Многопоточность: средняя разница с пи — %.5f%n", multiThread.averagedif());
        System.out.println();
    }
}
