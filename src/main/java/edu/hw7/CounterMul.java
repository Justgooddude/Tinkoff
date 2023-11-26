package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterMul {
    private AtomicInteger count;

    public CounterMul(int start) {
        count = new AtomicInteger(start);
    }

    public int get() {
        return count.get();
    }

    public void inc() {
        count.incrementAndGet();
    }

}
