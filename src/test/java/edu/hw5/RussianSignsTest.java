package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RussianSignsTest {

    @Test
    void valid() {
        RussianSigns signs = new RussianSigns();
        Assertions.assertTrue(signs.valid("А123ВЕ003"));
    }
}
