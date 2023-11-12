package edu.hw5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {
    Task2 task = new Task2();
    @Test
    void findAllFridays() {
        List<LocalDate> fridays= new ArrayList<>();
        fridays.add(LocalDate.of(2024,9,13));
        fridays.add(LocalDate.of(2024,12,13));
        assertEquals(task.findAllFridays(2024),fridays);
    }

    @Test
    void findnextFtiday() {
        LocalDate enter=LocalDate.of(2024,8,27);
        assertEquals(task.findnextFtiday(enter),LocalDate.of(2024,9,13));
    }
}
