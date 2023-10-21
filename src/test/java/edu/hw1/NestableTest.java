package edu.hw1;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NestableTest {
    @Test
    void inNestable1() {
        Nestable problem = new Nestable();
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {0, 6};
        boolean result = problem.inNestable(a1, a2);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void inNestable2() {
        Nestable problem = new Nestable();
        int[] a1 = new int[] {9, 9, 8};
        int[] a2 = new int[] {8, 9};
        boolean result = problem.inNestable(a1, a2);
        Assertions.assertThat(result).isFalse();
    }

}
