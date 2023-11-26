package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CounterMulTest {
    @Test
    public void test() {
        var count = new CounterMul(0);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() - 1;
        int iterationsPerThread = 1000;
        int expected = numberOfThreads * iterationsPerThread;
        var latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            new Thread(() -> {
                for (int j = 0; j < iterationsPerThread; ++j) {
                    count.inc();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        assertThat(count.get()).isEqualTo(expected);
    }

}
