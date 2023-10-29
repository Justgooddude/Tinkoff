package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BracesTest {

    @Test
    void clusterizetest() {
        Braces braces = new Braces();
        String example = "((()))(())()()(()())";
        ArrayList<String> result= braces.clusterize(example);
        String[] test = {"((()))", "(())", "()", "()", "(()())"};
        String[] resultm =new String[result.size()];
        for (int i = 0; i < result.size(); i++)
            resultm[i] = result.get(i);
        Assertions.assertArrayEquals(resultm,test);
    }
}
