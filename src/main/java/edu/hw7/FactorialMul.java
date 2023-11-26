package edu.hw7;

import java.util.stream.LongStream;

public class FactorialMul {

    private FactorialMul() {
    }

    private static final int BOARDERNUM = 20;

    public static long factorial(int num) {
        if (num < 0 || num > BOARDERNUM) {
            throw new IllegalArgumentException();
        }
        if (num == 0) {
            return 1L;
        }
        return LongStream.rangeClosed(1, num)
            .parallel()
            .reduce(1L, (long a, long b) -> a * b);
    }
}
