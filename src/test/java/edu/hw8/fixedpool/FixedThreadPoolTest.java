package edu.hw8.fixedpool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {
    private FixedThreadPool fixedThreadPool;

    private static final int THREADS_NUMBER = 4;
    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L,
        10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L,
        3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L
    };
    private volatile long[] results;

    @Test
    void testFibonacciParallel() {
        fixedThreadPool = FixedThreadPool.getFixedPool(THREADS_NUMBER);
        fixedThreadPool.start();
        int n = 39;
        results = new long[n + 1];
        var latch = new CountDownLatch(n + 1);
        for (int i = 0; i <= n; i++) {
            int index = i;
            fixedThreadPool.execute(() -> {
                results[index] = fibonacci(index);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }
        for (int i = 0; i <= n; ++i) {
            assertThat(results[i]).isEqualTo(FIBONACCI_NUMBERS[i]);
        }
        fixedThreadPool.close();
    }

    private long fibonacci(int n) {
        if (n < 0 || n > 40) {
            throw new IllegalArgumentException("out borders");
        }
        if (n <= 1) {
            return n;
        }
        if (results[n] == 0) {
            results[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return results[n];
    }

}
