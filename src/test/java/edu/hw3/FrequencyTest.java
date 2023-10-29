package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyTest {

    @Test
    void freqDicttest() {
        Frequency frequency= new Frequency<>();
        List<String>example = new ArrayList<>();
        example.add("код");
        example.add("код");
        example.add("код");
        example.add("bug");
        Map<String,Integer> result = frequency.freqDict(example);
        Map<String,Integer> test = new HashMap<>();
        test.put("код",3);
        test.put("bug",1);
        Assertions.assertEquals(result,test);
    }
}
