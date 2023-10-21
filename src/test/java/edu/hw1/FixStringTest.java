package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FixStringTest {
    void fixString() {
        FixString problem = new FixString();
        String example = "hTsii  s aimex dpus rtni.g";
        String result = problem.fixString(example);
        Assertions.assertThat(result).isEqualToIgnoringCase("This is a mixed up string.");
    }

    @Test
    void fixString2() {
        FixString problem = new FixString();
        String example = "badce";
        String result = problem.fixString(example);
        Assertions.assertThat(result).isEqualToIgnoringCase("abcde");
    }


}
