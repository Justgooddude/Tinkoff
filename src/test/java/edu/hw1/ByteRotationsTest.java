package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class ByteRotationsTest {

    @Test
    void rotateLeft() {
        ByteRotations problem = new ByteRotations();
        int example = 17;
        int shift = 2;
        int result = problem.rotateLeft(example, shift);
        Assertions.assertEquals(result,6);
    }

    @Test
    void rotateRight() {
        ByteRotations problem = new ByteRotations();
        int example = 8;
        int shift = 1;
        int result = problem.rotateRight(example, shift);
        Assertions.assertEquals(result,4);
    }
}
