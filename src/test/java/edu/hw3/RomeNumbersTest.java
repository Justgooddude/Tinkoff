package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomeNumbersTest {

    @Test
    void convertToRomantest() {
        RomeNumbers romeNumbers = new RomeNumbers();
        int example = 16;
        String result = romeNumbers.convertToRoman(example);
        String test = "XVI";
        Assertions.assertEquals(result, test);
    }
}
