package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Alphaet01Test {

    Alphaet01 task=new Alphaet01();
    @Test
    void third0() {
        Assertions.assertFalse(task.third0("210"));
    }

    @Test
    void starteqEnd() {
        assertTrue(task.starteqEnd("101010101001011"));
    }

    @Test
    void length() {
        assertFalse(task.length("1010"));
    }
}
