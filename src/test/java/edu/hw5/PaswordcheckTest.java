package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaswordcheckTest {

    @Test
    void check() {
        Paswordcheck pas=new Paswordcheck();
        Assertions.assertTrue(pas.check("dae@da"));
    }
}
