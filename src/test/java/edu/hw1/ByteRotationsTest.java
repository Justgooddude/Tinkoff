package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteRotationsTest {

    @Test
    void rotateLeft() {
        ByteRotations problem = new ByteRotations();
        int example = 17;
        int shift = 2;
        int result = problem.rotateLeft(example, shift);
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    void rotateRight() {
        ByteRotations problem = new ByteRotations();
        int example = 8;
        int shift = 1;
        int result = problem.rotateRight(example, shift);
        Assertions.assertThat(result).isEqualTo(4);
    }
}
