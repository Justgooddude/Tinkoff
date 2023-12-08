package edu.hw9.StatCol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private ExecutorService executorService;
    private List<Metric> metrics = Collections.synchronizedList(new ArrayList<>());

    public StatsCollector(int nThreads) {
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    public void push(String metricName, double[] statdata) {
        executorService.execute(
            pushThread(metricName, statdata)
        );
    }

    public List<Metric> statistic() {
        return new ArrayList<>(metrics);
    }
    private Runnable pushThread(String name, double[] values) {
        return () -> {
            double sum = 0.0;
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;

            for (double value : values) {
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
                sum += value;
            }
            metrics.add(new Metric(
                name, sum, sum / values.length, max, min
            ));
        };
    }
}
