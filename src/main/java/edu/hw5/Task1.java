package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Task1 {
    public String seansDuration(List<String> times) {
        long totalspended = 0;
        int count = 0;
        for (String time : times) {
            String time1 = time.replace(", ", ":");
            String time2 = time.replace("-", ":");
            time = time2.replace(" ", "");
            String[] data = time.split(":");
            LocalDateTime enter = LocalDateTime.of(
                Integer.valueOf(data[0]),
                Integer.valueOf(data[1]),
                Integer.valueOf(data[2]),
                Integer.valueOf(data[3]),
                Integer.valueOf(data[4]),
                0
            );
            LocalDateTime exit = LocalDateTime.of(
                Integer.valueOf(data[5]),
                Integer.valueOf(data[6]),
                Integer.valueOf(data[7]),
                Integer.valueOf(data[8]),
                Integer.valueOf(data[9]),
                0
            );
            totalspended += Duration.between(enter, exit).toMinutes();
            count++;
        }
        long midltime = totalspended / count;
        String result = new String();
        result = String.valueOf(midltime / 60) + "ч ";
        result += String.valueOf(midltime % 60) + "м";
        return result;
    }
}
