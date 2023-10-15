package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problems28Test {

    @Test
    void countdigits1() {
        Problems28 problem = new Problems28();
        int data = 4666;
        int result = problem.countdigits(data);
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    void countdigits2() {
        Problems28 problem = new Problems28();
        int data = 0;
        int result = problem.countdigits(data);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void inNestable1() {
        Problems28 problem = new Problems28();
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {0, 6};
        boolean result = problem.inNestable(a1, a2);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void inNestable2() {
        Problems28 problem = new Problems28();
        int[] a1 = new int[] {9, 9, 8};
        int[] a2 = new int[] {8, 9};
        boolean result = problem.inNestable(a1, a2);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    void fixString() {
        Problems28 problem = new Problems28();
        String example = "hTsii  s aimex dpus rtni.g";
        String result = problem.fixString(example);
        Assertions.assertThat(result).isEqualToIgnoringCase("This is a mixed up string.");
    }

    @Test
    void fixString2() {
        Problems28 problem = new Problems28();
        String example = "badce";
        String result = problem.fixString(example);
        Assertions.assertThat(result).isEqualToIgnoringCase("abcde");
    }

    @Test
    void isPalindromeDescendant() {
        Problems28 problem = new Problems28();
        int example = 13001120;
        boolean result = problem.isPalindromeDescendant(example);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void isPalindromeDescendant2() {
        Problems28 problem = new Problems28();
        int example = 543;
        boolean result = problem.isPalindromeDescendant(example);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    void countK() {
        Problems28 problem = new Problems28();
        int example = 6621;
        int result = problem.countK(example);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    void countK2() {
        Problems28 problem = new Problems28();
        int example = 1234;
        int result = problem.countK(example);
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    void rotateLeft() {
        Problems28 problem = new Problems28();
        int example = 17;
        int shift = 2;
        int result = problem.rotateLeft(example, shift);
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    void rotateRight() {
        Problems28 problem = new Problems28();
        int example = 8;
        int shift = 1;
        int result = problem.rotateRight(example, shift);
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    void knightBoardCapture() {
        Problems28 problem = new Problems28();
        int[][] example = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean result = problem.knightBoardCapture(example);
        Assertions.assertThat(result).isEqualTo(true);
    }
    @Test
    void knightBoardCapture2() {
        Problems28 problem = new Problems28();
        int[][] example = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1}
        };
        boolean result = problem.knightBoardCapture(example);
        Assertions.assertThat(result).isEqualTo(false);
    }
}
