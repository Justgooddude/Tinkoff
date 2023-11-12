package edu.hw5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void seansDuration() {
        Task1 task= new Task1();
        List<String> enter = new ArrayList<>();
        enter.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        enter.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        String test="3ч 40м";
        assertEquals(task.seansDuration(enter),test);
    }
}
