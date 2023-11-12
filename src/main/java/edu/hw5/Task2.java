package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public List<LocalDate> findAllFridays(int year) {
        List<LocalDate> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            LocalDate day = LocalDate.of(year, i, 13);
            if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
                result.add(day);
            }
        }
        return result;
    }

    public LocalDate findnextFtiday(LocalDate start) {
        LocalDate curent = start;
        while (true) {
            LocalDate next = curent.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (next.getDayOfMonth() == 13) {
                return next;
            }
            curent = next;
        }
    }
}
