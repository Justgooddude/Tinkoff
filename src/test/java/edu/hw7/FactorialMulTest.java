package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FactorialMulTest {
    @Test
    public void test() {
        int n = 20;
        long expected = 2432902008176640000L;
        long result = FactorialMul.factorial(n);
        assertThat(result).isEqualTo(expected);
    }

}
