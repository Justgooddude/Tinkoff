package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountdigitsTest {

    @Test
    void countdigits1() {
        Countdigits problem = new Countdigits();
        int data = 4666;
        int result = problem.countdigits(data);
        Assertions.assertEquals(result,4);
    }

    @Test
    void countdigits2() {
        Countdigits problem = new Countdigits();
        int data = 0;
        int result = problem.countdigits(data);
        Assertions.assertEquals(result,1);

    }


}
