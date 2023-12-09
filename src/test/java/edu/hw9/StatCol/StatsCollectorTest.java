package edu.hw9.StatCol;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatsCollectorTest {
    private StatsCollector collector;
    @Test
    @SuppressWarnings("MagicNumber")
    void statistic() throws InterruptedException {
        collector = new StatsCollector(2);
        Map<String, double[]> stats = Map.of(
            "cons", new double[] {3.0, 4.0, 5.0, 6.0, 7.0},
            "one data", new double[] {0.123456789},
            "zero", new double[] {0.0}
        );
        List<Metric> test = List.of(
            new Metric("cons", 25.0, 5.0, 7.0, 3.0),
            new Metric("one data", 0.123456789, 0.123456789, 0.123456789, 0.123456789),
            new Metric("zero", 0.0, 0.0, 0.0, 0.0)
        );

        // Act
        Thread[] collectorThread = new Thread[stats.size()];
        var latch = new CountDownLatch(stats.size());
        int i = 0;
        for (var entry : stats.entrySet()) {
            collectorThread[i] = new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                collector.push(entry.getKey(), entry.getValue());
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            collectorThread[i].start();
            ++i;
        }

        for (var provider : collectorThread) {
            provider.join();
        }
        List<Metric> result = collector.statistic();
        assertThat(result).hasSize(stats.size());
        for (var metric : result) {
            var expected = test.stream()
                .filter(e -> e.name().equals(metric.name()))
                .findFirst()
                .orElse(null);

            assertThat(metric.name()).isEqualTo(expected.name());
            assertThat(metric.sum()).isBetween(expected.sum() - 10e-10, expected.sum() + 10e-10);
            assertThat(metric.average()).isBetween(expected.average() - 10e-10, expected.average() + 10e-10);
            assertThat(metric.max()).isEqualTo(expected.max());
            assertThat(metric.min()).isEqualTo(expected.min());
        }
    }
}
