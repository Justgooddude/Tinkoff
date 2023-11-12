package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstrTest {

    @Test
    void valid() {
        Substr sub = new Substr();
        Assertions.assertTrue(sub.valid("Ivan","So I have an example"));
    }
}
