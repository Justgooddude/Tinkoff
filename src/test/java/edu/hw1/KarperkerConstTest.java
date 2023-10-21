package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KarperkerConstTest {
    @Test
    void countK() {
        KarperkerConst problem = new KarperkerConst();
        int example = 6621;
        int result = problem.countIter(example,0);
        Assertions.assertEquals(result,5);

    }

    @Test
    void countK2() {
        KarperkerConst problem = new KarperkerConst();
        int example = 1234;
        int result = problem.countIter(example,0);
        Assertions.assertEquals(result,3);

    }
}
