package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtbashTest {

    @Test
    void atbashtest() {
        Atbash cipher = new Atbash();
        String example =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String result = cipher.atbash(example);
        String test =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        Assertions.assertEquals(result, test);
    }
}
